/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

/**
 *
 * @author julio
 */
public class GameEngineConstants {
    private static final long serialVersionUID = 1217426776713013200L;
    // Thread Gap
    public static final int MAX_PROCESS = 20;
    
    // States
    public static final int STATE_PRE_SPLASH = 0;
    public static final int STATE_SPLASH = 1;
    public static final int STATE_MAIN_MENU = 10;
    public static final int STATE_OPTIONS_MENU = 20;
    public static final int STATE_GAME = 30;
    public static final int STATE_GAME_ANSWER = 31;
    public static final int STATE_GAME_ANSWER_OK = 32;
    public static final int STATE_GAME_ANSWER_FAIL = 33;
    public static final int STATE_GAME_DICES = 34;
    public static final int STATE_GAME_MOVING = 35;
    public static final int STATE_GAME_BRAKE = 36;
    public static final int STATE_GAME_WIN = 37;
    public static final int STATE_LOADING = 99;
    
    // Times
    public static final int SPLASH_DELAY = 500;
    public static final int TIME_SPLASH = 5000;
    public static final int TIME_SPLASH_FADE = 1000;
    public static final int TIME_DICES = 50;
    public static final int TIME_DICES_FINISH = 1000;
    public static final int TIME_ANSWER_RESULT = 1500;
    public static final int TIME_MOVING_PLAYER = 40;
    public static final int TIME_BREAK = 3000;
    
    // Languages
    public static final int DEFAULT_MODULE = 1;
    
    // Board
    public static final int MAX_SQUARES = 70;
    public static final int LIMIT_DIFFICULT = 23;
    public static final int DIFFICULT_EASY = 1;
    public static final int DIFFICULT_NORMAL = 2;
    public static final int DIFFICULT_HARD = 3;
}
