/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import ecdlplay.data.*;
import ecdlplay.gui.CanvasBase;
import ecdlplay.gui.Component;
import ecdlplay.gui.FloatImage;
import ecdlplay.gui.GameCanvasConstants;
import ecdlplay.utils.Utils;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Morpheo
 */
public class GameEngine extends JPanel implements Runnable {

    private int state;
    // GameCanvas
    private CanvasBase canvas;
    // Thread
    private Thread thread;
    private boolean running;
    //Time
    private long lastProcessTime;
    private long pauseTime;
    private long timeFinish;
    private long timeDices;
    // Setup
    private int module;
    private int numPlayers;
        
    private Board board;

    
    // Players
    private Player players[];
    private int turn;
    // Game Data
    private GameData gd;
    // Game Logic
    private int numAnswer = -1;
    private int numDice = 1;
    private Point playerRoute[];
    private int playerRouteSquares[];
    private int posPlayerRoute, maxPlayerRoute;
    public int playerRouteOffsetX, playerRouteOffsetY;

    public GameEngine() {
        
        // Init Default Config
        module = GameEngineConstants.DEFAULT_MODULE;
        numPlayers = 2;

        // Next State
        timeFinish = System.currentTimeMillis() + GameEngineConstants.SPLASH_DELAY;
        state = GameEngineConstants.STATE_PRE_SPLASH;

        
        
        // Initialize Thread and Events
        initialize();
    }

    private void initialize() {
        // Create GameCanvas
        canvas = CanvasBase.getCanvas(this);
        canvas.loadResources();
        
        // Add Mouse Pressed
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                panelMousePressed(evt);
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                panelMouseReleased(evt);
            }
        });

        // Add Mouse Pressed
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent evt) {
                panelMouseMoved(evt);
            }
        });


        // Create Thread
        thread = new Thread(this);
        // Start
        thread.start();
    }

    public int getTurn() {
        return turn;
    }

    public int getState() {
        return state;
    }

    private void prepareBoard() {
        int difficult, maxQuestionsPerSquare;
        ArrayList<ArrayList<Question>> questions;

        // Create Board
        board = new Board(GameEngineConstants.MAX_SQUARES);
        // Shuffle questions
        gd.shuffleQuestions();
        // Get Questions
        questions = new ArrayList<ArrayList<Question>>();
        questions.add(gd.getQuestions(GameEngineConstants.DIFFICULT_EASY, module));
        questions.add(gd.getQuestions(GameEngineConstants.DIFFICULT_NORMAL, module));
        questions.add(gd.getQuestions(GameEngineConstants.DIFFICULT_HARD, module));
        // Calculate Questions per Square
        maxQuestionsPerSquare = Math.min(
                questions.get(0).size() / GameEngineConstants.LIMIT_DIFFICULT, 
                Math.min(questions.get(1).size() / GameEngineConstants.LIMIT_DIFFICULT, 
                questions.get(2).size() / (GameEngineConstants.MAX_SQUARES - GameEngineConstants.LIMIT_DIFFICULT * 2)));
        // Iterate Squares
        for (int i = 0; i < GameEngineConstants.MAX_SQUARES; i++) {
            // Get difficult
            if (i < GameEngineConstants.LIMIT_DIFFICULT) {
                difficult = 0;
            } else if (i < (GameEngineConstants.LIMIT_DIFFICULT << 1)) {
                difficult = 1;
            } else {
                difficult = 2;
            }

            // Questions for the square
            Question questionsSquare[] = new Question[maxQuestionsPerSquare];
            // Iterate
            for (int j = 0; j < maxQuestionsPerSquare; j++) {
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

    private void preparePlayers() {
        // Create Array
        players = new Player[numPlayers];
        // Initialize
        for (int i = 0; i < players.length; i++) {
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

    private void movePlayerToStright(int numSquare) {
        int x, y;

        // Prepare Route to move the player
        x = GameCanvasConstants.SQUARES_COORD[(numSquare << 1) + 0];
        y = GameCanvasConstants.SQUARES_COORD[(numSquare << 1) + 1];
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
        state = GameEngineConstants.STATE_GAME_MOVING;
    }

    private void checkSquare() {
        int numSquare = players[turn].getNumSquare() + 1;

        // Player come from straight movement
        if (players[turn].isStraight()) {
            // Remvoe flag
            players[turn].setStraight(false);
            // Change Turn
            nextTurn();
        } else {
            // Check Square
            switch (numSquare) {
                // Pozo
                case 8:
                case 27:
                case 66:
                    // Only 2 or more players
                    if (numPlayers > 1) {
                        // Set Brake
                        players[turn].setBraked(true);
                        // Set Timing
                        timeFinish = lastProcessTime + GameEngineConstants.TIME_BREAK;
                        // Change State
                        state = GameEngineConstants.STATE_GAME_BRAKE;
                    } else {
                        nextTurn();
                    }
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

    private void checkAnswer() {
        // Get Actual Question
        Question actual = board.getSquare(players[turn].getNumSquare()).getQuestion();

        // Hide Components
        canvas.setComponentsVisible(false);

        // Check Answer
        if (actual.getRespuesta(numAnswer).isCorrecta()) {
            // Advance next Question in Square
            board.getSquare(players[turn].getNumSquare()).nextQuestion();
            // Show OK
            state = GameEngineConstants.STATE_GAME_ANSWER_OK;
        } else {
            state = GameEngineConstants.STATE_GAME_ANSWER_FAIL;
        }

        // Set Timing
        timeFinish = lastProcessTime + GameEngineConstants.TIME_ANSWER_RESULT;
    }

    private void nextTurn() {
        // Change Turn
        turn = (turn + 1) % numPlayers;

        if (players[turn].isBraked()) {
            // Remove brake and pass turn
            players[turn].setBraked(false);
            // Next Player
            nextTurn();
        } else {
            // Remove Actual Answer
            numAnswer = -1;
            updateAnswersButtons();
            // Show Components
            canvas.setComponentsVisible(true);

            // Change State
            state = GameEngineConstants.STATE_GAME_ANSWER;
        }
    }

    private void gameWin() {
        // Remove Components
        canvas.removeAllComponents();    
        
        FloatImage.getInstance().setVisible(false);
        
        // New State
        state = GameEngineConstants.STATE_GAME_WIN;
    }

    private void movePlayerNextRoute() {
        int nextX, nextY;
        int numSquare;
        int x, y;

        // Get Actual Square
        numSquare = players[turn].getNumSquare();
        // Get Coords
        x = GameCanvasConstants.SQUARES_COORD[(numSquare << 1) + 0];
        y = GameCanvasConstants.SQUARES_COORD[(numSquare << 1) + 1];
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

    private void stopDices() {
        //TEST
        //if (test == 0)
        //{
        //   numDice = 7;
        //    test = 1;
        //}
        // Check Max Dice
        if (players[turn].getNumSquare() + numDice >= 70) {
            numDice = 70 - players[turn].getNumSquare() - 1;
        }
        // Save Length Route
        maxPlayerRoute = numDice;
        // Prepare Route to move the player
        preparePlayerRoute();
        // Initialize Flags
        posPlayerRoute = 0;
        // Move Player
        movePlayerNextRoute();
        // Change State
        state = GameEngineConstants.STATE_GAME_MOVING;
    }

    private void startDices() {
        // Reset TimeDices
        timeDices = 0;
        // Set timing
        timeFinish = lastProcessTime + GameEngineConstants.TIME_DICES_FINISH;
        // Change to Dices
        state = GameEngineConstants.STATE_GAME_DICES;
    }

    private void preparePlayerRoute() {
        int numSquare;
        int x, y;

        for (int i = 1; i <= maxPlayerRoute; i++) {
            // Calculate Square
            numSquare = players[turn].getNumSquare() + i;

            if (numSquare < board.getNumSquares()) {
                // Get Coords
                x = GameCanvasConstants.SQUARES_COORD[(numSquare << 1) + 0];
                y = GameCanvasConstants.SQUARES_COORD[(numSquare << 1) + 1];
                // Save Point
                playerRoute[i - 1] = new Point(x, y);
                // Save Square
                playerRouteSquares[i - 1] = numSquare;
            }
        }
    }

    private void quitAnswerResult() {
        switch (state) {
            case GameEngineConstants.STATE_GAME_ANSWER_OK:
                // The actual player throws the dice
                startDices();
                break;
            case GameEngineConstants.STATE_GAME_ANSWER_FAIL:
                // Fail, the actual player loose turn
                nextTurn();
                break;
        }
    }

    private void toGame() {
        // Remove Components
        canvas.removeAllComponents();
        
        // Next state
        state = GameEngineConstants.STATE_GAME_ANSWER;
        
        // Load Questions
        gd = GameDataLoader.getLoader().getGameData();
        // Prepare Board
        prepareBoard();
        // Prepare Players
        preparePlayers();
        // Initialize Game
        turn = 0;
        // Load New Resources
        canvas = CanvasBase.getCanvas(this);
        canvas.loadResources();
        // Show Components
        canvas.setComponentsVisible(true);

    }

    private void toOptionsMenu() {
        // Remove Components
        canvas.removeAllComponents();
        
        // Next state
        state = GameEngineConstants.STATE_OPTIONS_MENU;
        
        // Load New Resources
        canvas = CanvasBase.getCanvas(this);
        canvas.loadResources();
        
        // Show Components
        canvas.setComponentsVisible(true);
        
        // Configure Buttons
        updateMenuButtons();
        updatePlayersButtons();

    }

    private void toMainMenu() {        
        
        // Remove Components
        canvas.removeAllComponents();
        
        // Next state
        state = GameEngineConstants.STATE_MAIN_MENU;
        
        // Load New Resources
        canvas = CanvasBase.getCanvas(this);
        canvas.loadResources();
        
        // Show Components
        canvas.setComponentsVisible(true);

    }

    private void openHelp() {
        try {
            // Create File
            File file = new File(GameCanvasConstants.RES_PATH + "/Rules.pdf");
            // Get Desktop
            Desktop desktop = Desktop.getDesktop();
            // Open File
            desktop.open(file);
        } catch (Exception e) {
        }
    }

    private void updateAnswersButtons() {
        // Release All Buttons
        canvas.getComponent(GameCanvasConstants.BUTTON_ANSWER1).release();
        canvas.getComponent(GameCanvasConstants.BUTTON_ANSWER2).release();
        canvas.getComponent(GameCanvasConstants.BUTTON_ANSWER3).release();

        // Press Buton Selected
        if (numAnswer != -1) {
            canvas.getComponent(GameCanvasConstants.BUTTON_ANSWER1 + numAnswer).press();
        }
    }

    private void updatePlayersButtons() {
        // Release All Buttons
        canvas.getComponent(GameCanvasConstants.BUTTON_1PLAYER).release();
        canvas.getComponent(GameCanvasConstants.BUTTON_2PLAYER).release();
        canvas.getComponent(GameCanvasConstants.BUTTON_3PLAYER).release();
        canvas.getComponent(GameCanvasConstants.BUTTON_4PLAYER).release();

        // Press Buton Selected
        canvas.getComponent(GameCanvasConstants.BUTTON_1PLAYER + numPlayers - 1).press();
    }

    private void updateMenuButtons() {
        
        // Release All Buttons
        int firstButton = GameCanvasConstants.BUTTON_FIRST_MODULE;
        int numModules = GameDataLoader.getLoader().getGameData().getModules().size();
        for (int i = 0; i < numModules; i++){
            canvas.getComponent(firstButton + i).release();
        }
        
        canvas.getComponent(firstButton + module - 1).press();
    }

    private void panelMouseMoved(MouseEvent evt) {
        // Get Component
        Component c = canvas.getComponent(evt.getX(), evt.getY());

        // Default Cursor
        Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);

        if (c != null) // Get Cursor
        {
            cursor = new Cursor(c.getCursor());
        }

        // Set Cursor
        setCursor(cursor);
    }

    private void panelMousePressed(MouseEvent evt) {
        canvas.mousePressed(evt.getX(), evt.getY());
    }

    private void panelMouseReleased(MouseEvent evt) {
        // Mouse Released
        canvas.mouseReleased();

        // Get Clicked
        Component c = canvas.getComponent(evt.getX(), evt.getY());

        if (c != null) {
            // Get ID
            int id = c.getId();

            // Process Click
            processComponentClick(id);
        } else {
            processClick();
        }
    }

    private void processPlayerBrake() {
        if (lastProcessTime >= timeFinish) {
            // Change Turn
            nextTurn();
        }
    }

    private void processMovingPlayer() {
        // Reach next square?
        if (playerRouteOffsetX == 0 && playerRouteOffsetY == 0) {
            // Final Moving
            if (posPlayerRoute == maxPlayerRoute) {
                // Check Square
                checkSquare();
            } else {
                movePlayerNextRoute();
            }
        } else {
            if (lastProcessTime >= timeFinish) {
                // Update Movement
                playerRouteOffsetX = (playerRouteOffsetX * 2) / 3;
                playerRouteOffsetY = (playerRouteOffsetY * 2) / 3;

                // Update Timinig
                timeFinish = lastProcessTime + GameEngineConstants.TIME_MOVING_PLAYER;
            }
        }
    }

    private void processAnswerResult() {
        // Check timing
        if (lastProcessTime >= timeFinish) {
            quitAnswerResult();
        }
    }

    private void processDices() {
        // Restrict fps
        if (lastProcessTime >= timeDices) {
            // Calculate
            numDice = Utils.randomInt(6) + 1;
            // Set next time
            timeDices = System.currentTimeMillis() + GameEngineConstants.TIME_DICES;
        }

        // Check Finish
        if (lastProcessTime >= timeFinish) {
            stopDices();
        }
    }

    private void processSplash() {
        if (lastProcessTime >= timeFinish) {
            toMainMenu();
        }
    }

    private void processPreSplash() {
        if (lastProcessTime >= timeFinish) {
            timeFinish = System.currentTimeMillis() + GameEngineConstants.TIME_SPLASH;
            state = GameEngineConstants.STATE_SPLASH;
            
            canvas = CanvasBase.getCanvas(this);
            canvas.loadResources();
        }
    }

    private void processClick() {
        switch (state) {
            case GameEngineConstants.STATE_GAME_ANSWER_OK:
            case GameEngineConstants.STATE_GAME_ANSWER_FAIL:
                quitAnswerResult();
                break;
            case GameEngineConstants.STATE_GAME_DICES:
                stopDices();
                break;
            case GameEngineConstants.STATE_GAME_WIN:
                toMainMenu();
                break;
        }
    }

    private void processComponentClick(int id) {
        switch (id) {
            case GameCanvasConstants.BUTTON_HELP:
                openHelp();
                break;
            case GameCanvasConstants.BUTTON_EXIT:
                System.exit(0);
                break;
            case GameCanvasConstants.BUTTON_OPTIONS:
                toOptionsMenu();
                break;
            case GameCanvasConstants.BUTTON_BACK:
                toMainMenu();
                break;            
            case GameCanvasConstants.BUTTON_1PLAYER:
            case GameCanvasConstants.BUTTON_2PLAYER:
            case GameCanvasConstants.BUTTON_3PLAYER:
            case GameCanvasConstants.BUTTON_4PLAYER:
                numPlayers = id - GameCanvasConstants.BUTTON_1PLAYER + 1;
                updatePlayersButtons();
                break;
            case GameCanvasConstants.BUTTON_START:
                toGame();
                break;
            case GameCanvasConstants.BUTTON_ANSWER1:
            case GameCanvasConstants.BUTTON_ANSWER2:
            case GameCanvasConstants.BUTTON_ANSWER3:
                FloatImage.getInstance().setVisible(false);
                numAnswer = id - GameCanvasConstants.BUTTON_ANSWER1;
                updateAnswersButtons();

                checkAnswer();
                break;
            case GameCanvasConstants.BUTTON_SHOW_IMAGE:
                FloatImage.getInstance().setVisible(true);
                break;
            default:
                if (id >= GameCanvasConstants.BUTTON_FIRST_MODULE)
                {
                    module = id - GameCanvasConstants.BUTTON_FIRST_MODULE + 1;
                    updateMenuButtons();
                }
        }
    }

    private void process() {
        switch (state) {
            case GameEngineConstants.STATE_PRE_SPLASH:
                processPreSplash();
                break;
            case GameEngineConstants.STATE_SPLASH:
                processSplash();
                break;
            case GameEngineConstants.STATE_GAME_DICES:
                processDices();
                break;
            case GameEngineConstants.STATE_GAME_ANSWER_FAIL:
            case GameEngineConstants.STATE_GAME_ANSWER_OK:
                processAnswerResult();
                break;
            case GameEngineConstants.STATE_GAME_MOVING:
                processMovingPlayer();
                break;
            case GameEngineConstants.STATE_GAME_BRAKE:
                processPlayerBrake();
                break;
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        canvas.paint(g);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        long time;

        running = true;

        while (running) {
            //Actual Time
            lastProcessTime = System.currentTimeMillis() - pauseTime;

            //Process the World
            process();
            //Repaint the Screen
            repaint();

            //Sleep the world
            time = (System.currentTimeMillis() - pauseTime);
            if ((time - lastProcessTime) < GameEngineConstants.MAX_PROCESS) { //45 ms per frame (15-20 fps)
                sleep(GameEngineConstants.MAX_PROCESS - (int) (time - lastProcessTime));
            } else {
                sleep(5);
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getNumDice() {
        return numDice;
    }

    public Question getQuestion() {
        return board.getSquare(players[turn].getNumSquare()).getQuestion();
    }

    public ArrayList<Answer> getAnswers() {
        return board.getSquare(players[turn].getNumSquare()).getQuestion().getRespuestas();
    }

    public Module getModule() {
        for(Module moduleAux : GameDataLoader.getLoader().getGameData().getModules()){
            if (moduleAux.getId() == this.module){
                return moduleAux;
            }
        }
        return null;
    }
}
