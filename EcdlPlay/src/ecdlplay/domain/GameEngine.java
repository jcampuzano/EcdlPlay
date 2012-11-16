/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.domain;

import ecdlplay.data.*;
import ecdlplay.gui.Component;
import ecdlplay.gui.GameCanvas;
import ecdlplay.utils.Utils;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;

/**
 *
 * @author Morpheo
 */
public class GameEngine extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1217426776713013200L;

	// Thread Gap
    public static final int MAX_PROCESS = 20;

    // States
    private int state;
    public static final int STATE_PRE_SPLASH         = 0;
    public static final int STATE_SPLASH             = 1;
    public static final int STATE_MAIN_MENU          = 10;
    public static final int STATE_OPTIONS_MENU       = 20;
    public static final int STATE_GAME               = 30;
    public static final int STATE_GAME_ANSWER        = 31;
    public static final int STATE_GAME_ANSWER_OK     = 32;
    public static final int STATE_GAME_ANSWER_FAIL   = 33;
    public static final int STATE_GAME_DICES         = 34;
    public static final int STATE_GAME_MOVING        = 35;
    public static final int STATE_GAME_BRAKE         = 36;
    public static final int STATE_GAME_WIN           = 37;
    public static final int STATE_LOADING            = 99;

    // Times
    public static final int SPLASH_DELAY       = 500;
    public static final int TIME_SPLASH        = 5000;
    public static final int TIME_SPLASH_FADE   = 1000;
    public static final int TIME_DICES         = 50;
    public static final int TIME_DICES_FINISH  = 1000;
    public static final int TIME_ANSWER_RESULT = 1500;
    public static final int TIME_MOVING_PLAYER = 40;
    public static final int TIME_BREAK         = 3000;

    // Languages
    public static final int LANGUAGE_SPANISH = 0;
    public static final int LANGUAGE_ENGLISH = 1;

    // GameCanvas
    private GameCanvas gc;

    // Thread
    private Thread thread;
    private boolean running;
    //Time
    private long lastProcessTime;
    private long pauseTime;
    private long timeFinish;
    private long timeDices;

    // Setup
    private int language;
    private int numPlayers;

    // Board
    private Board board;
    public static final int MAX_SQUARES       = 70;
    public static final int LIMIT_DIFFICULT   = 23;
    public static final int DIFFICULT_EASY    = 1;
    public static final int DIFFICULT_NORMAL  = 2;
    public static final int DIFFICULT_HARD    = 3;
    // Players
    private Player players[];
    private int turn;
    // Game Data
    private GameData gd;
    // Game Logic
    private int numAnswer = -1;
    private int numDice   = 1;
    private Point playerRoute[];
    private int playerRouteSquares[];
    private int posPlayerRoute, maxPlayerRoute;
    public int playerRouteOffsetX, playerRouteOffsetY;

    public GameEngine()
    {
        // Create GameCanvas
        gc = new GameCanvas(this);

        // Load background
        gc.loadResource(GameCanvas.RES_SPLASH);
        gc.loadResource(GameCanvas.RES_MAIN_MENU_BACKGROUND);

        // Init Default Config
        language   = LANGUAGE_SPANISH;
        numPlayers = 2;

        // Next State
        timeFinish = System.currentTimeMillis() + SPLASH_DELAY;
        state = STATE_PRE_SPLASH;

        // Initialize Thread and Events
        initialize();
    }

    private void initialize()
    {
        // Add Mouse Pressed
        addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent evt)
            {
                panelMousePressed(evt);
            }

            @Override
            public void mouseReleased(MouseEvent evt)
            {
                panelMouseReleased(evt);
            }
        });

        // Add Mouse Pressed
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent evt)
            {
                panelMouseMoved(evt);
            }
        });


        // Create Thread
        thread = new Thread(this);
        // Start
        thread.start();
    }

    public int getTurn()
    {
        return turn;
    }

    public int getState()
    {
        return state;
    }
    
    @SuppressWarnings("unchecked")
	private void prepareBoard()
    {
        int difficult, maxQuestionsPerSquare;
        ArrayList<ArrayList<Question>> questions;

        // Create Board
        board = new Board(MAX_SQUARES);
        // Shuffle questions
        gd.shuffleQuestions();
        // Get Questions
        questions = new ArrayList<ArrayList<Question>>();
        questions.add(gd.getQuestions(DIFFICULT_EASY, language));
        questions.add(gd.getQuestions(DIFFICULT_NORMAL, language));
        questions.add(gd.getQuestions(DIFFICULT_HARD, language));
	// Calculate Questions per Square
	maxQuestionsPerSquare = Math.min(questions.get(0).size() / LIMIT_DIFFICULT, Math.min(questions.get(1).size() / LIMIT_DIFFICULT, questions.get(2).size() / (MAX_SQUARES - LIMIT_DIFFICULT * 2)));
        // Iterate Squares
        for(int i = 0; i < MAX_SQUARES; i++)
        {
            // Get difficult
            if (i < LIMIT_DIFFICULT){
                difficult = 0;
            }
            else if (i < (LIMIT_DIFFICULT << 1)){
                difficult = 1;
            }
            else{
                difficult = 2;
            }

	    // Questions for the square
	    Question questionsSquare[] = new Question[maxQuestionsPerSquare];
	    // Iterate
	    for(int j = 0; j < maxQuestionsPerSquare; j++)
	    {
		// Actual Size
		int size = questions.get(difficult).size();
		// Get Random element
		Question q = questions.get(difficult).get(Utils.randomInt(size));
		// Remove It
		questions.get(difficult).remove(q);
		// Save Question
		questionsSquare[j] = q;
	    }

            // Add Question to Square
            board.setQuestions(i, questionsSquare);
        }
    }

    private void preparePlayers()
    {
        // Create Array
        players = new Player[numPlayers];
        // Initialize
        for(int i = 0; i < players.length; i++)
        {
            // Create Player
            players[i] = new Player(i, 0);
        }

        // Create Route Array
        //playerRoute = new Point[6];
        //playerRouteSquares = new int[6];
        //TEST:
        playerRoute = new Point[80];
        playerRouteSquares = new int[80];
    }

    private void movePlayerToStright(int numSquare)
    {
        int x, y;

        // Prepare Route to move the player
        x = GameCanvas.SQUARES_COORD[(numSquare << 1) + 0];
        y = GameCanvas.SQUARES_COORD[(numSquare << 1) + 1];
        // Save Point
        playerRoute[0] = new Point(x, y);
        playerRouteSquares[0] = numSquare;

        // Initialize Flags
        posPlayerRoute = 0;
        maxPlayerRoute = 1;
        // Set Flag to Player
        players[turn].setStraight(true);
        // Move Player
        movePlayerNextRoute();
        // Change State
        state = STATE_GAME_MOVING;
    }

    private void checkSquare()
    {
        int numSquare = players[turn].getNumSquare() + 1;

        // Player come from straight movement
        if (players[turn].isStraight())
        {
            // Remvoe flag
            players[turn].setStraight(false);
            // Change Turn
            nextTurn();
        } else
        {
            // Check Square
            switch(numSquare)
            {
                // Pozo
                case 8:
                case 27:
                case 66:
                    // Only 2 or more players
                    if (numPlayers > 1)
                    {
                        // Set Brake
                        players[turn].setBraked(true);
                        // Set Timing
                        timeFinish = lastProcessTime + TIME_BREAK;
                        // Change State
                        state = STATE_GAME_BRAKE;
                    } else
                        nextTurn();
                    break;
                // Escalera
                case 16:
                    movePlayerToStright(50 - 1);
                    break;
                case 50:
                    movePlayerToStright(16 - 1);
                    break;
                case 31:
                    movePlayerToStright(61 - 1);
                    break;
                case 61:
                    movePlayerToStright(31 - 1);
                    break;
                case 70:
                    gameWin();
                    break;
                default:
                    // Change Turn in common square
                    nextTurn();
            }
        }
    }

    private void checkAnswer()
    {
        // Get Actual Question
        Question actual = board.getSquare(players[turn].getNumSquare()).getQuestion();

        // Hide Components
        gc.setComponentsVisible(false);

        // Check Answer
        if (actual.getRespuesta(numAnswer).isCorrecta())
        {
	    // Advance next Question in Square
	    board.getSquare(players[turn].getNumSquare()).nextQuestion();
	    // Show OK
            state = STATE_GAME_ANSWER_OK;
        } else
        {
            state = STATE_GAME_ANSWER_FAIL;
        }

        // Set Timing
        timeFinish = lastProcessTime + TIME_ANSWER_RESULT;
    }

    private void nextTurn()
    {
        // Change Turn
        turn = (turn + 1) % numPlayers;

        if (players[turn].isBraked())
        {
            // Remove brake and pass turn
            players[turn].setBraked(false);
            // Next Player
            nextTurn();
        } else
        {
            // Remove Actual Answer
            numAnswer = -1;
            updateAnswersButtons();
            // Show Components
            gc.setComponentsVisible(true);

            // Change State
            state = STATE_GAME_ANSWER;
        }
    }

    private void gameWin()
    {
        //toCuriosity();
    }

    private void movePlayerNextRoute()
    {
        int nextX, nextY;
        int numSquare;
        int x, y;

        // Get Actual Square
        numSquare = players[turn].getNumSquare();
        // Get Coords
        x     = GameCanvas.SQUARES_COORD[(numSquare << 1) + 0];
        y     = GameCanvas.SQUARES_COORD[(numSquare << 1) + 1];
        nextX = playerRoute[posPlayerRoute].x;
        nextY = playerRoute[posPlayerRoute].y;
        // Calculate Offsets
        playerRouteOffsetX = x - nextX;
        playerRouteOffsetY = y - nextY;
        // Move Player Next Square
        players[turn].setNumSquare(playerRouteSquares[posPlayerRoute]);
        // Update Flag
        posPlayerRoute++;
    }

    private void stopDices()
    {
        //TEST
        //if (test == 0)
        //{
        //   numDice = 7;
        //    test = 1;
        //}
        // Check Max Dice
        if (players[turn].getNumSquare() + numDice >= 70)
            numDice = 70 - players[turn].getNumSquare() - 1;
        // Save Length Route
        maxPlayerRoute = numDice;
        // Prepare Route to move the player
        preparePlayerRoute();
        // Initialize Flags
        posPlayerRoute = 0;
        // Move Player
        movePlayerNextRoute();
        // Change State
        state = STATE_GAME_MOVING;
    }

    private void startDices()
    {
        // Reset TimeDices
        timeDices  = 0;
        // Set timing
        timeFinish = lastProcessTime + TIME_DICES_FINISH;
        // Change to Dices
        state = STATE_GAME_DICES;
    }

    private void preparePlayerRoute()
    {
        int numSquare;
        int x, y;

        for(int i = 1; i <= maxPlayerRoute; i++)
        {
            // Calculate Square
            numSquare = players[turn].getNumSquare() + i;

            if (numSquare < board.getNumSquares())
            {
                // Get Coords
                x = GameCanvas.SQUARES_COORD[(numSquare << 1) + 0];
                y = GameCanvas.SQUARES_COORD[(numSquare << 1) + 1];
                // Save Point
                playerRoute[i - 1] = new Point(x, y);
                // Save Square
                playerRouteSquares[i - 1] = numSquare;
            }
        }
    }

    private void quitAnswerResult()
    {
        switch(state)
        {
            case STATE_GAME_ANSWER_OK:
                // The actual player throws the dice
                startDices();
                break;
            case STATE_GAME_ANSWER_FAIL:
                // Fail, the actual player loose turn
                nextTurn();
                break;
        }
    }

    private void toGame()
    {
        // Remove Components
        gc.removeAllComponents();
        // Load Questions
        gd = GameDataLoader.getLoader().getGameData();
        // Prepare Board
        prepareBoard();
        // Prepare Players
        preparePlayers();
        // Initialize Game
        turn = 0;
        // Load New Resources
        gc.loadResource(GameCanvas.RES_GAME_BACKGROUND);
        gc.loadResource(GameCanvas.RES_GAME_BUTTONS);
        gc.loadResource(GameCanvas.RES_GAME_QUESTION_FONT);
        gc.loadResource(GameCanvas.RES_GAME_ANSWER_BOX);
        gc.loadResource(GameCanvas.RES_GAME_DICE);
        gc.loadResource(GameCanvas.RES_GAME_PLAYERS);
        gc.loadResource(GameCanvas.RES_GAME_PLAYERS_TURN);
        // Show Components
        gc.setComponentsVisible(true);

        // Next state
        state = STATE_GAME_ANSWER;
    }

    private void toOptionsMenu()
    {
        // Remove Components
        gc.removeAllComponents();
        // Load New Resources
        gc.loadResource(GameCanvas.RES_OPTIONS_MENU_BACKGROUND);
        gc.loadResource(GameCanvas.RES_OPTIONS_MENU_BUTTONS);

        // Configure Buttons
        updateLanguageButtons();
        updatePlayersButtons();

        // Next state
        state = STATE_OPTIONS_MENU;
    }

    private void toMainMenu()
    {
        // Remove Components
        gc.removeAllComponents();
        // Load New Resources
        gc.loadResource(GameCanvas.RES_MAIN_MENU_BACKGROUND);
        gc.loadResource(GameCanvas.RES_MAIN_MENU_BUTTONS);
        // Show Components
        gc.setComponentsVisible(true);

        // Next state
        state = STATE_MAIN_MENU;
    }
    
    private void openHelp()
    {
    	try
    	{
	    	// Create File
	    	File file = new File(GameCanvas.RES_PATH + "/Rules.pdf");
	    	// Get Desktop
	    	Desktop desktop = Desktop.getDesktop();
	    	// Open File
	    	desktop.open(file);
    	} catch(Exception e) {}
    }

    private void updateAnswersButtons()
    {
        // Release All Buttons
        gc.getComponent(GameCanvas.BUTTON_ANSWER1).release();
        gc.getComponent(GameCanvas.BUTTON_ANSWER2).release();
        gc.getComponent(GameCanvas.BUTTON_ANSWER3).release();

        // Press Buton Selected
        if (numAnswer != -1)
            gc.getComponent(GameCanvas.BUTTON_ANSWER1 + numAnswer).press();
    }

    private void updatePlayersButtons()
    {
        // Release All Buttons
        gc.getComponent(GameCanvas.BUTTON_1PLAYER).release();
        gc.getComponent(GameCanvas.BUTTON_2PLAYER).release();
        gc.getComponent(GameCanvas.BUTTON_3PLAYER).release();
        gc.getComponent(GameCanvas.BUTTON_4PLAYER).release();

        // Press Buton Selected
        gc.getComponent(GameCanvas.BUTTON_1PLAYER + numPlayers - 1).press();
    }

    private void updateLanguageButtons()
    {
        // Release All Buttons
        gc.getComponent(GameCanvas.BUTTON_SPANISH).release();
        gc.getComponent(GameCanvas.BUTTON_ENGLISH).release();

        if (language == LANGUAGE_SPANISH)
        {
            // Select Button
            gc.getComponent(GameCanvas.BUTTON_SPANISH).press();
            // Update Theme
            gc.setTheme("sp");
        }
        else
        {
            // Select Button
            gc.getComponent(GameCanvas.BUTTON_ENGLISH).press();
            // Update Theme
            gc.setTheme("en");
        }
    }

    private void panelMouseMoved(MouseEvent evt)
    {
        // Get Component
       Component c = gc.getComponent(evt.getX(), evt.getY());

       // Default Cursor
       Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);

       if (c != null)
           // Get Cursor
           cursor = new Cursor(c.getCursor());

       // Set Cursor
       setCursor(cursor);
    }

    private void panelMousePressed(MouseEvent evt)
    {
        gc.mousePressed(evt.getX(), evt.getY());
    }

    private void panelMouseReleased(MouseEvent evt)
    {
        // Mouse Released
        gc.mouseReleased();
        
        // Get Clicked
       Component c = gc.getComponent(evt.getX(), evt.getY());

       if (c != null)
       {
           // Get ID
           int id = c.getId();

           // Process Click
           processComponentClick(id);
       } else
       {
           processClick();
       }
    }

    private void processPlayerBrake()
    {
        if (lastProcessTime >= timeFinish)
        {
            // Change Turn
            nextTurn();
        }
    }

    private void processMovingPlayer()
    {
        // Reach next square?
        if (playerRouteOffsetX == 0 && playerRouteOffsetY == 0)
        {
            // Final Moving
            if (posPlayerRoute == maxPlayerRoute)
            {
                // Check Square
                checkSquare();
            } else
                movePlayerNextRoute();
        } else
        {
            if (lastProcessTime >= timeFinish)
            {
                // Update Movement
                playerRouteOffsetX = (playerRouteOffsetX * 2) / 3;
                playerRouteOffsetY = (playerRouteOffsetY * 2) / 3;
                
                // Update Timinig
                timeFinish = lastProcessTime + TIME_MOVING_PLAYER;
            }
        }
    }

    private void processAnswerResult()
    {
        // Check timing
        if (lastProcessTime >= timeFinish)
            quitAnswerResult();
    }

    private void processDices()
    {
        // Restrict fps
        if (lastProcessTime >= timeDices)
        {
            // Calculate
            numDice = Utils.randomInt(6) + 1;
            // Set next time
            timeDices = System.currentTimeMillis() + TIME_DICES;
        }

        // Check Finish
        if (lastProcessTime >= timeFinish)
            stopDices();
    }

    private void processSplash()
    {
        if (lastProcessTime >= timeFinish)
            toMainMenu();
    }

    private void processPreSplash()
    {
        if (lastProcessTime >= timeFinish)
        {
        	timeFinish = System.currentTimeMillis() + TIME_SPLASH;
        	state = STATE_SPLASH;
        }
    }

    private void processClick()
    {
        switch(state)
        {
            case STATE_GAME_ANSWER_OK:
            case STATE_GAME_ANSWER_FAIL:
                quitAnswerResult();
                break;
            case STATE_GAME_DICES:
                stopDices();
                break;
//            case STATE_CURIOSITY:
//                toMainMenu();
//                break;
        }
    }

    private void processComponentClick(int id)
    {
       switch(id)
       {
       		case GameCanvas.BUTTON_HELP:
       			openHelp();
       			break;
           case GameCanvas.BUTTON_EXIT:
               System.exit(0);
               break;
           case GameCanvas.BUTTON_OPTIONS:
               toOptionsMenu();
               break;
           case GameCanvas.BUTTON_BACK:
               toMainMenu();
               break;
           case GameCanvas.BUTTON_SPANISH:
               language = LANGUAGE_SPANISH;
               updateLanguageButtons();
               break;
           case GameCanvas.BUTTON_ENGLISH:
               language = LANGUAGE_ENGLISH;
               updateLanguageButtons();
               break;
           case GameCanvas.BUTTON_1PLAYER:
           case GameCanvas.BUTTON_2PLAYER:
           case GameCanvas.BUTTON_3PLAYER:
           case GameCanvas.BUTTON_4PLAYER:
               numPlayers = id - GameCanvas.BUTTON_1PLAYER + 1;
               updatePlayersButtons();
               break;
           case GameCanvas.BUTTON_START:
               toGame();
               break;
           case GameCanvas.BUTTON_ANSWER1:
           case GameCanvas.BUTTON_ANSWER2:
           case GameCanvas.BUTTON_ANSWER3:
               numAnswer = id - GameCanvas.BUTTON_ANSWER1;
               updateAnswersButtons();

               checkAnswer();
               break;
       }
    }

    private void process()
    {
        switch(state)
        {
        	case STATE_PRE_SPLASH:
        		processPreSplash();
        		break;
            case STATE_SPLASH:
                processSplash();
                break;
            case STATE_GAME_DICES:
                processDices();
                break;
            case STATE_GAME_ANSWER_FAIL:
            case STATE_GAME_ANSWER_OK:
                processAnswerResult();
                break;
            case STATE_GAME_MOVING:
                processMovingPlayer();
                break;
            case STATE_GAME_BRAKE:
                processPlayerBrake();
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        switch(state)
        {
        	case STATE_PRE_SPLASH:
        		gc.paintBlack(g);
        		break;
            case STATE_SPLASH:
                gc.paintSplash(g, timeFinish - System.currentTimeMillis());
                break;
            case STATE_MAIN_MENU:
            case STATE_OPTIONS_MENU:
                gc.paintMainMenu(g);
                break;
            case STATE_GAME:
            case STATE_GAME_ANSWER:
                gc.paintGame(g, players);
                gc.paintQuestion(g, board.getSquare(players[turn].getNumSquare()).getQuestion().getTexto());
                gc.paintAnswers(g, board.getSquare(players[turn].getNumSquare()).getQuestion().getRespuestas());
                break;
            case STATE_GAME_ANSWER_OK:
                gc.paintGame(g, players);
                gc.paintQuestion(g, Texts.getText(language, Texts.TEXT_RIGHT));
                break;
            case STATE_GAME_ANSWER_FAIL:
                gc.paintGame(g, players);
                gc.paintQuestion(g, Texts.getText(language, Texts.TEXT_FAIL));
                break;
            case STATE_GAME_BRAKE:
                gc.paintGame(g, players);
                gc.paintQuestion(g, Texts.getText(language, Texts.TEXT_BRAKES, String.valueOf(turn + 1)));
                break;
            case STATE_GAME_DICES:
            case STATE_GAME_MOVING:
                gc.paintGame(g, players);
                gc.paintDice(g, numDice);
                break;
        }
    }

    private void sleep(int ms)
    {
        try { Thread.sleep(ms); } catch(Exception e) { }
    }

    public void run()
    {
	long time;

	running = true;

	while(running)
	{
            //Actual Time
            lastProcessTime = System.currentTimeMillis() - pauseTime;

            //Process the World
            process();
            //Repaint the Screen
            repaint();

            //Sleep the world
            time = (System.currentTimeMillis() - pauseTime);
            if ((time - lastProcessTime) < MAX_PROCESS) //45 ms per frame (15-20 fps)
                sleep(MAX_PROCESS - (int)(time - lastProcessTime));
            else
                sleep(5);
        }
    }
}
