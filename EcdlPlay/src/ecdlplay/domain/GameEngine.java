/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import ecdlplay.data.*;
import ecdlplay.domain.entities.Answer;
import ecdlplay.domain.entities.Board;
import ecdlplay.domain.entities.Module;
import ecdlplay.domain.entities.Player;
import ecdlplay.domain.entities.Question;
import ecdlplay.gui.FloatImage;
import ecdlplay.gui.canvas.CanvasBase;
import ecdlplay.gui.canvas.GameCanvasConstants;
import ecdlplay.gui.components.Component;
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
 * Clase principal de la aplicación. Se encarga de toda la lógica de la aplicación como la 
 * máquina de estados del menú y juego, lógica del juego y comunicación con las clases 
 * canvas para el pintado de los componentes.
 *
 * @author julio
 */
public class GameEngine extends JPanel implements Runnable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7671356298662065694L;
	/**
	 * Indica el estado actual de la máquina de estados
	 */
	private int state;
    // GameCanvas
	/**
	 * Instancia actual de la clase canvas que se utilizará para pintar la pantalla
	 */
    private CanvasBase canvas;
    // Thread
    /**
	 * Instancia al objeto Thread que se encarga de repintar la pantalla cada poco tiempo.
	 */
    private Thread thread;
    /**
	 * Indica si el hilo de repintado tiene que parar (running = false) o no
	 */
    private boolean running;
    //Time
    /**
	 * Indica la última “posición del mundo” procesada por el hilo de repintado
	 */
    private long lastProcessTime;
    
    /**
	 * Variable que se suele utilizar para marcar un tiempo en que la aplicación 
	 * tiene que hacer algo, como por ejemplo saltar el Splash después de unos segundos.
	 */
    private long timeFinish;
    /**
	 * Tiempo en el que los dados tienen que dejar de estar moviéndose
	 */
    private long timeDices;
    // Setup
    /**
	 * Identificador del módulo sobre el que se realizarán las preguntas del juego. Este valor se modifica en el menú de opciones
	 */
    private int module;
    /**
	 * Número de jugadores que participarán en el juego. Su valor se modifica en el menú de opciones.
	 */
    private int numPlayers;
        
    /**
	 * Instancia del tablero, que se inicializa cada vez que se comienza un nuevo juego
	 */
    private Board board;

    
    // Players
    /**
	 * Array de los jugadores jugando la partida. Se utiliza para almacenar su estado durante la partida
	 */
    private Player players[];
    /**
	 * Indica el turno actual, quién es el jugador jugando en cada momento
	 */
    private int turn;
    // Game Data
    /**
	 * Instancia de la clase GameData de la que se obtienen los datos de la aplicación
	 */
    private GameData gd;
    // Game Logic
    /**
	 * Respuesta seleccionada por el jugador
	 */
    private int numAnswer = -1;
    /**
	 * Identificador de la respuesta que ha elegido el usuario
	 */
    private int numDice = 1;
    /**
	 * Valor devuelto por el dado en una tirada
	 */
    private Point playerRoute[];
    /**
	 * Array de puntos de pantalla para generar la ruta que tiene que seguir la animación de avanzar casillas de un jugador
	 */
    private int playerRouteSquares[];
    /**
	 * Indica en qué posición de PlayerRoute estamos en cada momento
	 */
    private int posPlayerRoute;
    
    /**
     * Indica en qué posición de PlayerRoute estamos en cada momento
     */
    private int maxPlayerRoute;
    /**
	 * Posición X del punto actual de la ruta
	 */
    public int playerRouteOffsetX;
    /**
     * Posición Y del punto actual de la ruta
     */
    public int playerRouteOffsetY;

    /**
     * Constructor de todo el motor del juego
     */
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

    /**
     * Se encarga de inicializar el motor gráfico. Inicializa el DoubleBuffer, 
     * establece los diferentes Listeners para escuchar la interacción por parte del usuario y 
     * crea el hilo de repintado.
     */
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

    /**
     * Devuelve el turno del jugador que está jugando en un momento determinado
     * @return
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Devuelve el estado actual de la máquina de estados
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     * Inicializa el tablero, asignando las preguntas a cada una de las casillas
     */
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
                // Shuffle Answers
                q.shuflleAnswers();
                // Save Question
                questionsSquare[j] = q;
            }

            // Add Question to Square
            board.setQuestions(i, questionsSquare);
        }
    }

    /**
     * Inicializa el array de jugadores así como el array de rutas y de casillas 
     */
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

    /**
     * Avanza la pieza del jugador hasta la casilla indicada por el parámetro
     * @param numSquare
     */
    private void movePlayerToStraight(int numSquare) {
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

    /**
     * Comprueba la casilla en la que ha caido el jugador actual, para comprobar si es 
     * una casilla de frenada, de escalera, de fin o una casilla normal
     */
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
                case 44:
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
                    movePlayerToStraight(50 - 1);
                    break;
                case 50:
                    movePlayerToStraight(16 - 1);
                    break;
                case 31:
                    movePlayerToStraight(61 - 1);
                    break;
                case 61:
                    movePlayerToStraight(31 - 1);
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

    /**
     * Comprueba si la respuesta indicada por el jugador es correcta
     */
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

    /**
     * Cambia el turno al siguiente jugador, mostrando la pregunta y las respuestas
     */
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

    /**
     * Cambia el estado indicando que un jugador ha llegado a la casilla final
     */
    private void gameWin() {
        // Remove Components
        canvas.removeAllComponents();    
        
        FloatImage.getInstance().setVisible(false);
        
        // New State
        state = GameEngineConstants.STATE_GAME_WIN;
    }

    /**
     * Mueve la pieza del jugador a la siguiente casilla en la ruta
     */
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

    /**
     * Para el lanzamiento de dados y cambia al estado correspondiente 
     */
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

    /**
     * Comienza la animación de lanzar dados
     */
    private void startDices() {
        // Reset TimeDices
        timeDices = 0;
        // Set timing
        timeFinish = lastProcessTime + GameEngineConstants.TIME_DICES_FINISH;
        // Change to Dices
        state = GameEngineConstants.STATE_GAME_DICES;
    }

    /**
     * Inicializa la ruta que seguirá la ficha del jugador según el número de casillas
     * que tenga que avanzar
     */
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

    /**
     * Cuando se contesta a la pregunta correctamente el jugador actual tirará los dados, 
     * por el contrario si la pregunta se ha contestado incorrectamente el jugador 
     * cambiará de turno sin avanzar casillas
     */
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

    /**
     * Una vez que se selecciona el Menú Start se llamará a esta función que se
     * encarga de borrar todos los componentes actuales (botones), de cargar las preguntas, de
     * preparar el tablero, los jugadores y, finalmente, de inicializar el juego
     */
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

    /**
     * Cuando se selecciona el menú opciones se ejecuta este método que
     * se encarga de borrar todos los antiguos componentes del menú anterior y preparar todos
     * los gráficos del menú de opciones.
     */
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

    /**
     * Se encarga de mostrar el menú principal cargando todos los recursos
     * gráficos que sean necesarios
     */
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

    /**
     * Abre el PDF asociado a la ayuda del juego
     */
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

    /**
     * Actualiza los botones de las tres posibles respuestas marcando el 
     * correspondiente elegido si procede
     */
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

    /**
     * Actualiza los gráficos de los botones de los jugadores
     */
    private void updatePlayersButtons() {
        // Release All Buttons
        canvas.getComponent(GameCanvasConstants.BUTTON_1PLAYER).release();
        canvas.getComponent(GameCanvasConstants.BUTTON_2PLAYER).release();
        canvas.getComponent(GameCanvasConstants.BUTTON_3PLAYER).release();
        canvas.getComponent(GameCanvasConstants.BUTTON_4PLAYER).release();

        // Press Buton Selected
        canvas.getComponent(GameCanvasConstants.BUTTON_1PLAYER + numPlayers - 1).press();
    }

    /**
     * Actualiza los gráficos de los botones de los módulos
     */
    private void updateMenuButtons() {
        
        // Release All Buttons
        int firstButton = GameCanvasConstants.BUTTON_FIRST_MODULE;
        int numModules = GameDataLoader.getLoader().getGameData().getModules().size();
        for (int i = 0; i < numModules; i++){
            canvas.getComponent(firstButton + i).release();
        }
        
        canvas.getComponent(firstButton + module - 1).press();
    }

    /**
     * Se lanza cada vez que el usuario mueve el ratón por la ventana.
     * @param evt
     */
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

    /**
     * Se lanza cada vez que el usuario presiona un botón del ratón.
     * @param evt
     */
    private void panelMousePressed(MouseEvent evt) {
        canvas.mousePressed(evt.getX(), evt.getY());
    }

    /**
     * Se lanza cada vez que el usuario libera un botón del ratón.
     * @param evt
     */
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

    /**
     * Cuando un jugador cae en una casilla de freno el turno pasará al siguiente jugador.
     */
    private void processPlayerBrake() {
        if (lastProcessTime >= timeFinish) {
            // Change Turn
            nextTurn();
        }
    }

    /**
     * Se encarga del movimiento del jugador por las casillas del tablero
     */
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

    /**
     * Hace de temporizador para mostrar el mensaje de Respuesta Correcta/Incorrecta durante unos segundos
     */
    private void processAnswerResult() {
        // Check timing
        if (lastProcessTime >= timeFinish) {
            quitAnswerResult();
        }
    }

    /**
     * Se encarga del lanzamiento del los dados
     */
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

    /**
     * Hace de temporizador para mostrar el splash de bienvenida durante unos segundos.
     */
    private void processSplash() {
        if (lastProcessTime >= timeFinish) {
            toMainMenu();
        }
    }

    /**
     * Hace de temporizador para mostrar la pantalla de información de la aplicación 
     * durante unos segundos
     */
    private void processPreSplash() {
        if (lastProcessTime >= timeFinish) {
            timeFinish = System.currentTimeMillis() + GameEngineConstants.TIME_SPLASH;
            state = GameEngineConstants.STATE_SPLASH;
            
            canvas = CanvasBase.getCanvas(this);
            canvas.loadResources();
        }
    }

    /**
     * Procesa el click del ratón teniendo en cuenta en el estado en que se encuentra 
     * la máquina de estados.
     */
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

    /**
     * Procesa el click de ratón en algún componente gráfico de pantalla como un botón un Checkbox.
     * @param id
     */
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

    /**
     * Proceso principal que se encarga de llamar a los sub procesos de gestión dependiendo 
     * del estado actual de la máquina de estados.
     */
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

    /**
     * Se llamada cada vez que hay que Java necesita repintar. Llama a RenderGame en el doble buffer y vuelca el buffer a pantalla.
     */
    @Override
    public void paintComponent(Graphics g) {
        canvas.paint(g);
    }

    /**
     * duerme el hilo principal tantos milisegundos como indicados por parámetro.
     * @param ms
     */
    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    /**
     * Método de la interface Runnable. Es el contenido del código del hilo de repintado.
     */
    @Override
    public void run() {
        long time;

        running = true;

        while (running) {
            //Actual Time
            lastProcessTime = System.currentTimeMillis();

            //Process the World
            process();
            //Repaint the Screen
            repaint();

            //Sleep the world
            time = System.currentTimeMillis();
            if ((time - lastProcessTime) < GameEngineConstants.MAX_PROCESS) { //45 ms per frame (15-20 fps)
                sleep(GameEngineConstants.MAX_PROCESS - (int) (time - lastProcessTime));
            } else {
                sleep(5);
            }
        }
    }

    /**
     * Obtiene los jugadores actuales
     * @return
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Obtiene el valor devuelto por el dado
     * @return
     */
    public int getNumDice() {
        return numDice;
    }

    /**
     * Obtiene la pregunta de la casilla actual
     * @return
     */
    public Question getQuestion() {
        return board.getSquare(players[turn].getNumSquare()).getQuestion();
    }

    /**
     * Obtiene las respuestas de la pregunta de la casilla actual
     * @return
     */
    public ArrayList<Answer> getAnswers() {
        return getQuestion().getRespuestas();
    }

    /**
     * Obtiene la información del módulo sobre la que se van a hacer las preguntas
     * @return
     */
    public Module getModule() {
        for(Module moduleAux : GameDataLoader.getLoader().getGameData().getModules()){
            if (moduleAux.getId() == this.module){
                return moduleAux;
            }
        }
        return null;
    }
}
