/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import ecdlplay.gui.GameView;

/**
 *
 * @author julio
 */
public class GameEngine implements Runnable {

    private boolean running;
    private States state;
    private GameView view;
    private GameConfig config;
    private Thread thread;
    private Board board;
    private Player[] players;
    private int turn;
    private int numDice;
    // Times
    private static final int SPLASH_DELAY = 500;
    private static final int TIME_SPLASH = 5000;
    private static final int TIME_SPLASH_FADE = 1000;
    private static final int TIME_DICES = 50;
    private static final int TIME_DICES_FINISH = 1000;
    private static final int TIME_ANSWER_RESULT = 1500;
    private static final int TIME_MOVING_PLAYER = 40;
    private static final int TIME_BREAK = 3000;
    private static final int MAX_PROCESS = 20;
    private long lastProcessTime;
    private long pauseTime;
    private long timeFinish;
    private long timeDices;

    public GameEngine(GameView canvas) {
        this.view = canvas;
        this.config = new GameConfig();

        initialize();
    }

    private void initialize() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        long time;

        this.running = true;
        this.state = States.PRE_SPLASH;

        while (running) {
            process();

            repaint();

            time = (System.currentTimeMillis() - pauseTime);
            if ((time - lastProcessTime) < MAX_PROCESS) {
                sleep(MAX_PROCESS - (int) (time - lastProcessTime));
            } else {
                sleep(5);
            }
        }
    }

    private void process() {
        switch (state) {
            case PRE_SPLASH:
                processPreSplash();
                break;
            case SPLASH:
                processSplash();
                break;
            case GAME_DICES:
                processDices();
                break;
            case GAME_ANSWER_FAIL:
            case GAME_ANSWER_OK:
                processAnswerResult();
                break;
            case GAME_MOVING:
                processMovingPlayer();
                break;
            case GAME_BRAKE:
                processPlayerBrake();
                break;
        }
    }

    private void processPreSplash() {
        if (lastProcessTime >= timeFinish) {
            timeFinish = System.currentTimeMillis() + TIME_SPLASH;
            state = States.SPLASH;
        }
    }

    private void processSplash() {
        if (lastProcessTime >= timeFinish) {
            loadData();
            this.state = States.MAIN_MENU;
        }
    }

    private void processDices() {
        numDice = Dice.throwDice();
        this.view.showDices(numDice);
        this.state = States.GAME_MOVING;
    }

    private void processAnswerResult() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void processMovingPlayer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void processPlayerBrake() {
        if (lastProcessTime >= timeFinish) {
            nextTurn();
        }
    }

    /**
     * Refresca la vista según el estado en el que se encuentre el juego
     */
    private void repaint() {
        switch (this.state) {
            case SPLASH:
                this.view.showSplash();
                break;
            case MAIN_MENU:
                this.view.showMenu(this.config);
                break;
            case GAME:
                this.view.showBoard(this.config);
                break;
            case GAME_ANSWER:
                this.view.showQuestion(this.board.getQuestion(players[turn]));
                break;
            case GAME_ANSWER_OK:
                this.view.showAnswer(true);
                break;
            case GAME_ANSWER_FAIL:
                this.view.showAnswer(false);
                break;
            case GAME_WIN:
                this.view.showWinner(this.players[turn]);
                break;
        }
    }

    /**
     * Para el hilo de ejecución el tiempo indicado
     *
     * @param time
     */
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }

    /**
     * Este método se encargará de cargar los datos necesarios para el juego, es
     * decir, los módulos, las preguntas y las respuestas
     */
    private void loadData() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void nextTurn() {
        this.turn = (turn + 1) % players.length;
        
        if (players[turn].isBraked()){
            players[turn].setBraked(false);
            nextTurn();
        }
        else{
            
        }
    }
}
