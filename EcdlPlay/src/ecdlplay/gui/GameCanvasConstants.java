/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

/**
 *
 * @author julio
 */
public class GameCanvasConstants {
    // Path Res
    public static final String RES_PATH = "res/";
    // Game Size
    public static final int SCREEN_WIDTH = 946;
    public static final int SCREEN_HEIGHT = 694;
    // Main Menu
    public static final int MAIN_MENU_BUTTON_START_X = 290;
    public static final int MAIN_MENU_BUTTON_START_Y = 584;
    public static final int MAIN_MENU_BUTTON_OPTIONS_X = 443;
    public static final int MAIN_MENU_BUTTON_OPTIONS_Y = 582;
    public static final int MAIN_MENU_BUTTON_EXIT_X = 597;
    public static final int MAIN_MENU_BUTTON_EXIT_Y = 578;
    public static final int MAIN_MENU_BUTTON_HELP_X = 10;
    public static final int MAIN_MENU_BUTTON_HELP_Y = 10;
    // Options Menu
    public static final int OPTIONS_MENU_BUTTON_BACK_X = 429;
    public static final int OPTIONS_MENU_BUTTON_BACK_Y = 578;
    
    public static final int OPTIONS_MENU_MODULE_X = 168;
    public static final int OPTIONS_MENU_MODULE_Y = 110;
    public static final int OPTIONS_MENU_MODULE_BUTTONS_X = 125;
    public static final int OPTIONS_MENU_MODULE_BUTTONS_Y = 195;
    public static final int OPTIONS_MENU_MODULE_BOX_X = 203;
    public static final int OPTIONS_MENU_MODULE_BOX_Y = 302;
    public static final int OPTIONS_MENU_MODULE_BOX_WIDTH = 203;
    public static final int OPTIONS_MENU_MODULE_BOX_HEIGHT = 30;
    
    public static final int OPTIONS_MENU_PLAYERS_X = 522;
    public static final int OPTIONS_MENU_PLAYERS_Y = 110;
    public static final int OPTIONS_MENU_1PLAYER_X = 490;
    public static final int OPTIONS_MENU_1PLAYER_Y = 212;
    public static final int OPTIONS_MENU_2PLAYER_X = 562;
    public static final int OPTIONS_MENU_2PLAYER_Y = 212;
    public static final int OPTIONS_MENU_3PLAYER_X = 635;
    public static final int OPTIONS_MENU_3PLAYER_Y = 212;
    public static final int OPTIONS_MENU_4PLAYER_X = 707;
    public static final int OPTIONS_MENU_4PLAYER_Y = 212;
    // Game
    public static final int GAME_BUTTON_BACK_X = 850;
    public static final int GAME_BUTTON_BACK_Y = 487;
    public static final int GAME_BUTTON_SHOW_IMAGE_X = 50;
    public static final int GAME_BUTTON_SHOW_IMAGE_Y = 10;
    // Board
    public static final int SQUARES_COORD[] = {
        //     1,        2,        3,        4,        5,        6,        7,        8,        9,       10,
        415, 520, 460, 520, 506, 520, 551, 520, 596, 520, 641, 520, 689, 518, 732, 503, 760, 471, 767, 426,
        //    11,       12,       13,       14,       15,       16,       17,       18,       19,       20,
        767, 380, 767, 335, 767, 289, 767, 244, 765, 196, 741, 158, 705, 131, 664, 126, 619, 126, 574, 126,
        //    21,       22,       23,       24,       25,       26,       27,       28,       29,       30,
        529, 126, 484, 126, 438, 126, 394, 126, 348, 126, 303, 126, 259, 126, 213, 132, 180, 162, 175, 204,
        //    31,       32,       33,       34,       35,       36,       37,       38,       39,       40,
        175, 249, 175, 295, 175, 340, 175, 385, 185, 431, 217, 462, 256, 467, 301, 467, 346, 467, 391, 467,
        //    41,       42,       43,       44,       45,       46,       47,       48,       49,       50,
        436, 467, 481, 467, 526, 467, 571, 467, 614, 456, 656, 436, 687, 405, 701, 363, 704, 322, 704, 277,
        //    51,       52,       53,       54,       55,       56,       57,       58,       59,       60,
        696, 230, 667, 198, 623, 189, 582, 188, 537, 188, 492, 188, 447, 188, 402, 188, 357, 188, 310, 200,
        //    61,       62,       63,       64,       65,       66,       67,       68,       69,       70,
        269, 221, 240, 253, 235, 293, 235, 339, 255, 382, 297, 398, 335, 401, 380, 401, 425, 401, 471, 401,};
    // Question
    public static final int QUESTION_X = 286;
    public static final int QUESTION_Y = 228;
    public static final int QUESTION_WIDTH = 386;
    public static final int QUESTION_HEIGHT = 145;
    // Answers
    public static final int ANSWER1_X = 45;
    public static final int ANSWER1_Y = 557;
    public static final int ANSWER2_X = 330;
    public static final int ANSWER2_Y = 557;
    public static final int ANSWER3_X = 615;
    public static final int ANSWER3_Y = 557;
    public static final int ANSWERBOX_X = 60;
    public static final int ANSWERBOX_Y = 8;
    public static final int ANSWERBOX_WIDTH = 214;
    public static final int ANSWERBOX_HEIGHT = 129;
    // Dice
    public static final int DICE_X = 862;
    public static final int DICE_Y = 281;
    public static final int DICE_TEXT_X = 889;
    public static final int DICE_TEXT_Y = 266;
    // Players
    public static final int PLAYERS_TURN_X = 670;
    public static final int PLAYERS_TURN_Y = 50;
    public static final int PLAYERS_TURN_GAP = 45;
   
    // Resources
    public static final int RES_MAIN_MENU_BACKGROUND = 0;
    public static final int RES_MAIN_MENU_BUTTONS = 1;
    public static final int RES_SPLASH = 2;
    public static final int RES_OPTIONS_MENU_BACKGROUND = 20;
    public static final int RES_OPTIONS_MENU_BUTTONS = 21;
    public static final int RES_GAME_BACKGROUND = 30;
    public static final int RES_GAME_BUTTONS = 31;
    public static final int RES_GAME_QUESTION_FONT = 32;
    public static final int RES_GAME_ANSWER_BOX = 33;
    public static final int RES_GAME_DICE = 34;
    public static final int RES_GAME_PLAYERS = 35;
    public static final int RES_GAME_PLAYERS_TURN = 36;
    public static final int RES_CURIO_BACKGROUND = 40;
    public static final int RES_CURIO_FONT = 41;
    // Buttons
    public static final int BUTTON_NORMAL = 0;
    public static final int BUTTON_SELECTED = 1;
    public static final int BUTTON_START = 0;
    public static final int BUTTON_OPTIONS = 1;
    public static final int BUTTON_EXIT = 2;
    public static final int BUTTON_BACK = 3;
    public static final int BUTTON_1PLAYER = 4;
    public static final int BUTTON_2PLAYER = 5;
    public static final int BUTTON_3PLAYER = 6;
    public static final int BUTTON_4PLAYER = 7;
    public static final int BUTTON_ANSWER1 = 8;
    public static final int BUTTON_ANSWER2 = 9;
    public static final int BUTTON_ANSWER3 = 10;
    public static final int BUTTON_HELP = 11;
    public static final int BUTTON_SHOW_IMAGE = 12;
    public static final int BUTTON_FIRST_MODULE = 20;
    

}
