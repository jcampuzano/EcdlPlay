/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui;

import ecdlplay.domain.Answer;
import ecdlplay.domain.GameEngine;
import ecdlplay.domain.Player;
import ecdlplay.utils.ImageLoader;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Morpheo
 */
public class GameCanvas
{
    // Path Res
    public static final String RES_PATH = "res/";

    // Game Size
    public static final int SCREEN_WIDTH  = 946;
    public static final int SCREEN_HEIGHT = 694;

    // Main Menu
    public static final int MAIN_MENU_BUTTON_START_X   = 290;
    public static final int MAIN_MENU_BUTTON_START_Y   = 584;
    public static final int MAIN_MENU_BUTTON_OPTIONS_X = 443;
    public static final int MAIN_MENU_BUTTON_OPTIONS_Y = 582;
    public static final int MAIN_MENU_BUTTON_EXIT_X    = 597;
    public static final int MAIN_MENU_BUTTON_EXIT_Y    = 578;
    public static final int MAIN_MENU_BUTTON_HELP_X    = 10;
    public static final int MAIN_MENU_BUTTON_HELP_Y    = 10;
    // Options Menu
    public static final int OPTIONS_MENU_BUTTON_BACK_X = 429;
    public static final int OPTIONS_MENU_BUTTON_BACK_Y = 578;
    public static final int OPTIONS_MENU_LANGUAGE_X    = 168;
    public static final int OPTIONS_MENU_LANGUAGE_Y    = 200;
    public static final int OPTIONS_MENU_SPANISH_X     = 203;
    public static final int OPTIONS_MENU_SPANISH_Y     = 302;
    public static final int OPTIONS_MENU_ENGLISH_X     = 275;
    public static final int OPTIONS_MENU_ENGLISH_Y     = 316;
    public static final int OPTIONS_MENU_PLAYERS_X     = 522;
    public static final int OPTIONS_MENU_PLAYERS_Y     = 199;
    public static final int OPTIONS_MENU_1PLAYER_X     = 490;
    public static final int OPTIONS_MENU_1PLAYER_Y     = 302;
    public static final int OPTIONS_MENU_2PLAYER_X     = 562;
    public static final int OPTIONS_MENU_2PLAYER_Y     = 316;
    public static final int OPTIONS_MENU_3PLAYER_X     = 635;
    public static final int OPTIONS_MENU_3PLAYER_Y     = 302;
    public static final int OPTIONS_MENU_4PLAYER_X     = 707;
    public static final int OPTIONS_MENU_4PLAYER_Y     = 316;
    // Game
    public static final int GAME_BUTTON_BACK_X = 850;
    public static final int GAME_BUTTON_BACK_Y = 487;
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
        269, 221, 240, 253, 235, 293, 235, 339, 255, 382, 297, 398, 335, 401, 380, 401, 425, 401, 471, 401,
    };

    // Question
    public static final int QUESTION_X      = 286;
    public static final int QUESTION_Y      = 228;
    public static final int QUESTION_WIDTH  = 386;
    public static final int QUESTION_HEIGHT = 145;

    // Answers
    public static final int ANSWER1_X        = 45;
    public static final int ANSWER1_Y        = 557;
    public static final int ANSWER2_X        = 330;
    public static final int ANSWER2_Y        = 557;
    public static final int ANSWER3_X        = 615;
    public static final int ANSWER3_Y        = 557;
    public static final int ANSWERBOX_X      = 60;
    public static final int ANSWERBOX_Y      = 8;
    public static final int ANSWERBOX_WIDTH  = 214;
    public static final int ANSWERBOX_HEIGHT = 129;

    // Dice
    public static final int DICE_X      = 862;
    public static final int DICE_Y      = 281;
    public static final int DICE_TEXT_X = 889;
    public static final int DICE_TEXT_Y = 266;

    // Players
    public static final int PLAYERS_TURN_X   = 670;
    public static final int PLAYERS_TURN_Y   = 50;
    public static final int PLAYERS_TURN_GAP = 45;

    // Curiosity
    public static final int CURIOBOX_X      = 167;
    public static final int CURIOBOX_Y      = 258;
    public static final int CURIOBOX_WIDTH  = 560;
    public static final int CURIOBOX_HEIGHT = 225;


    // Resources
    public static final int RES_MAIN_MENU_BACKGROUND     = 0;
    public static final int RES_MAIN_MENU_BUTTONS        = 1;
    public static final int RES_SPLASH                   = 2;
    public static final int RES_OPTIONS_MENU_BACKGROUND  = 20;
    public static final int RES_OPTIONS_MENU_BUTTONS     = 21;
    public static final int RES_GAME_BACKGROUND          = 30;
    public static final int RES_GAME_BUTTONS             = 31;
    public static final int RES_GAME_QUESTION_FONT       = 32;
    public static final int RES_GAME_ANSWER_BOX          = 33;
    public static final int RES_GAME_DICE                = 34;
    public static final int RES_GAME_PLAYERS             = 35;
    public static final int RES_GAME_PLAYERS_TURN        = 36;
    public static final int RES_CURIO_BACKGROUND         = 40;
    public static final int RES_CURIO_FONT               = 41;

    // Buttons
    public static final int BUTTON_NORMAL    = 0;
    public static final int BUTTON_SELECTED  = 1;

    public static final int BUTTON_START   = 0;
    public static final int BUTTON_OPTIONS = 1;
    public static final int BUTTON_EXIT    = 2;
    public static final int BUTTON_BACK    = 3;
    public static final int BUTTON_SPANISH = 4;
    public static final int BUTTON_ENGLISH = 5;
    public static final int BUTTON_1PLAYER = 6;
    public static final int BUTTON_2PLAYER = 7;
    public static final int BUTTON_3PLAYER = 8;
    public static final int BUTTON_4PLAYER = 9;
    public static final int BUTTON_ANSWER1 = 10;
    public static final int BUTTON_ANSWER2 = 11;
    public static final int BUTTON_ANSWER3 = 12;
    public static final int BUTTON_HELP    = 13;

    // GameEngine
    private GameEngine ge;

    // Theme
    private String theme;

    // Images
    private Image logo;
    private Image background;
    private Image dice[];
    private Image players[];
    private Image playerBright;
    private Image playerTurnBright;
    // Fonts
    private TFont fntQuestion     = null;
    private TFont fntAnswerNormal = null;
    private TFont fntDice         = null;

    // Vector Buttons
    private List<Component> components;
    private boolean visible;

    public GameCanvas(GameEngine ge)
    {
        this.ge         = ge;
        this.components = new ArrayList<Component>();
        this.visible    = true;
        this.theme      = "sp";
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public void loadResource(int resource)
    {
        switch(resource)
        {
        	case RES_SPLASH:
        		logo = ImageLoader.loadImageJAR("splash.png", theme);
        		break;
            case RES_CURIO_BACKGROUND:
                background = ImageLoader.loadImageJAR("curiosity_background.png", theme);
                break;
            case RES_CURIO_FONT:
                fntQuestion = new TFont("futura.ttf", 20, Font.BOLD, 0xFFFFFF);
                break;
            case RES_MAIN_MENU_BACKGROUND:
                background = ImageLoader.loadImageJAR("menu_background.png", theme);
                break;
            case RES_MAIN_MENU_BUTTONS:
                // Component Start
                addComponent(new Button(
                            BUTTON_START,
                            MAIN_MENU_BUTTON_START_X,
                            MAIN_MENU_BUTTON_START_Y,
                            ImageLoader.loadImageJAR("menu_button_start_1.png", theme),
                            ImageLoader.loadImageJAR("menu_button_start_2.png", theme)
                            ));
                // Component Options
                addComponent(new Button(
                            BUTTON_OPTIONS,
                            MAIN_MENU_BUTTON_OPTIONS_X,
                            MAIN_MENU_BUTTON_OPTIONS_Y,
                            ImageLoader.loadImageJAR("menu_button_options_1.png", theme),
                            ImageLoader.loadImageJAR("menu_button_options_2.png", theme)
                            ));
                // Component Exit
                addComponent(new Button(
                            BUTTON_EXIT,
                            MAIN_MENU_BUTTON_EXIT_X,
                            MAIN_MENU_BUTTON_EXIT_Y,
                            ImageLoader.loadImageJAR("menu_button_exit_1.png", theme),
                            ImageLoader.loadImageJAR("menu_button_exit_2.png", theme)
                            ));
                // Component Help
                addComponent(new Button(
                            BUTTON_HELP,
                            MAIN_MENU_BUTTON_HELP_X,
                            MAIN_MENU_BUTTON_HELP_Y,
                            ImageLoader.loadImageJAR("menu_button_help.png", theme),
                            ImageLoader.loadImageJAR("menu_button_help.png", theme)
                            ));
                break;
            case RES_OPTIONS_MENU_BACKGROUND:
                background = ImageLoader.loadImageJAR("options_background.png", theme);
                break;
            case RES_OPTIONS_MENU_BUTTONS:
                // Component Back
                addComponent(new Button(
                            BUTTON_BACK,
                            OPTIONS_MENU_BUTTON_BACK_X,
                            OPTIONS_MENU_BUTTON_BACK_Y,
                            ImageLoader.loadImageJAR("menu_button_back_1.png", theme),
                            ImageLoader.loadImageJAR("menu_button_back_2.png", theme)
                            ));
                // Component Language Label
                addComponent(new Label(
                            -1,
                            OPTIONS_MENU_LANGUAGE_X,
                            OPTIONS_MENU_LANGUAGE_Y,
                            ImageLoader.loadImageJAR("menu_options_language.png", theme)
                            ));
                // Component Players Label
                addComponent(new Label(
                            -1,
                            OPTIONS_MENU_PLAYERS_X,
                            OPTIONS_MENU_PLAYERS_Y,
                            ImageLoader.loadImageJAR("menu_options_players.png", theme)
                            ));
                // Component Spanish
                addComponent(new Checkbox(
                            BUTTON_SPANISH,
                            OPTIONS_MENU_SPANISH_X,
                            OPTIONS_MENU_SPANISH_Y,
                            ImageLoader.loadImageJAR("menu_options_spanish_1.png", theme),
                            ImageLoader.loadImageJAR("menu_options_spanish_2.png", theme)
                            ));
                // Component English
                addComponent(new Checkbox(
                            BUTTON_ENGLISH,
                            OPTIONS_MENU_ENGLISH_X,
                            OPTIONS_MENU_ENGLISH_Y,
                            ImageLoader.loadImageJAR("menu_options_english_1.png", theme),
                            ImageLoader.loadImageJAR("menu_options_english_2.png", theme)
                            ));
                // Component Players
                addComponent(new Checkbox(
                            BUTTON_1PLAYER,
                            OPTIONS_MENU_1PLAYER_X,
                            OPTIONS_MENU_1PLAYER_Y,
                            ImageLoader.loadImageJAR("menu_options_1p_1.png", theme),
                            ImageLoader.loadImageJAR("menu_options_1p_2.png", theme)
                            ));
                addComponent(new Checkbox(
                            BUTTON_2PLAYER,
                            OPTIONS_MENU_2PLAYER_X,
                            OPTIONS_MENU_2PLAYER_Y,
                            ImageLoader.loadImageJAR("menu_options_2p_1.png", theme),
                            ImageLoader.loadImageJAR("menu_options_2p_2.png", theme)
                            ));
                addComponent(new Checkbox(
                            BUTTON_3PLAYER,
                            OPTIONS_MENU_3PLAYER_X,
                            OPTIONS_MENU_3PLAYER_Y,
                            ImageLoader.loadImageJAR("menu_options_3p_1.png", theme),
                            ImageLoader.loadImageJAR("menu_options_3p_2.png", theme)
                            ));
                addComponent(new Checkbox(
                            BUTTON_4PLAYER,
                            OPTIONS_MENU_4PLAYER_X,
                            OPTIONS_MENU_4PLAYER_Y,
                            ImageLoader.loadImageJAR("menu_options_4p_1.png", theme),
                            ImageLoader.loadImageJAR("menu_options_4p_2.png", theme)
                            ));
                // Component Help
                addComponent(new Button(
                            BUTTON_HELP,
                            MAIN_MENU_BUTTON_HELP_X,
                            MAIN_MENU_BUTTON_HELP_Y,
                            ImageLoader.loadImageJAR("menu_button_help.png", theme),
                            ImageLoader.loadImageJAR("menu_button_help.png", theme)
                            ));
                break;
            case RES_GAME_BACKGROUND:
                background = ImageLoader.loadImageJAR("game_background.png", theme);
                break;
            case RES_GAME_BUTTONS:
                // Component Back
                addComponent(new Button(
                            BUTTON_BACK,
                            GAME_BUTTON_BACK_X,
                            GAME_BUTTON_BACK_Y,
                            ImageLoader.loadImageJAR("menu_button_back_1.png", theme),
                            ImageLoader.loadImageJAR("menu_button_back_2.png", theme)
                            ));
                // Component Help
                addComponent(new Button(
                            BUTTON_HELP,
                            MAIN_MENU_BUTTON_HELP_X,
                            MAIN_MENU_BUTTON_HELP_Y,
                            ImageLoader.loadImageJAR("menu_button_help.png", theme),
                            ImageLoader.loadImageJAR("menu_button_help.png", theme)
                            ));
                break;
            case RES_GAME_QUESTION_FONT:
                fntQuestion     = new TFont("futura.ttf", 18, Font.BOLD,  0xFFFFFF);
                fntAnswerNormal = new TFont("futura.ttf", 14, Font.PLAIN, 0xEED4F5);
                fntDice         = new TFont("futura.ttf", 30, Font.BOLD,  0xFFFFFF);
                break;
            case RES_GAME_ANSWER_BOX:
                // Component Answers
                addComponent(new Checkbox(
                            BUTTON_ANSWER1,
                            ANSWER1_X,
                            ANSWER1_Y,
                            ImageLoader.loadImageJAR("game_answerbox_1.png", theme),
                            ImageLoader.loadImageJAR("game_answerbox_2.png", theme)
                            ));
                addComponent(new Checkbox(
                            BUTTON_ANSWER2,
                            ANSWER2_X,
                            ANSWER2_Y,
                            ImageLoader.loadImageJAR("game_answerbox_1.png", theme),
                            ImageLoader.loadImageJAR("game_answerbox_2.png", theme)
                            ));
                addComponent(new Checkbox(
                            BUTTON_ANSWER3,
                            ANSWER3_X,
                            ANSWER3_Y,
                            ImageLoader.loadImageJAR("game_answerbox_1.png", theme),
                            ImageLoader.loadImageJAR("game_answerbox_2.png", theme)
                            ));
                break;
            case RES_GAME_DICE:
                // Create Array
                dice = new Image[6];
                // Load Images
                for(int i = 0; i < dice.length; i++)
                    dice[i] = ImageLoader.loadImageJAR("game_dice_" + (i + 1) + ".png", theme);
            case RES_GAME_PLAYERS:
                // Create Players array
                players = new Image[4];
                // Load Images
                for(int i = 0; i < players.length; i++)
                    players[i] = ImageLoader.loadImageJAR("game_player_" + (i + 1) + ".png", theme);
                // Load Brights
                playerBright     = ImageLoader.loadImageJAR("game_player_bright.png", theme);
                playerTurnBright = ImageLoader.loadImageJAR("game_player_turn_bright.png", theme);
                break;
        }
    }

    public void unloadResource(int resource)
    {
        
    }

    public void addComponent(Component button)
    {
        components.add(button);
    }

    public Component getComponent(int id)
    {
        for(Component c : components)
        {
            if (c.getId() == id)
                return c;
        }

        return null;
    }

    public Component getComponent(int x, int y)
    {
        if (!isComponentsVisible())
            return null;

        for(Component b : components)
        {
            if (x >= b.getX() && x <= b.getX() + b.getWidth() &&
                y >= b.getY() && y <= b.getY() + b.getHeight())
            {
                return b;
            }
        }

        return null;
    }

    public void setComponentsVisible(boolean visible)
    {
        this.visible = visible;
    }

    public boolean isComponentsVisible()
    {
        return visible;
    }

    public void mousePressed(int x, int y)
    {
        // Release All
        releaseAllComponents();

        // Get Component Clicked
        Component c = getComponent(x, y);

        // Check Component
        if (c != null)
            // Click
            c.click();
    }

    public void mouseReleased()
    {
        releaseAllComponents();
    }

    private void releaseAllComponents()
    {
        for(Component c: components)
            c.releaseClick();
    }

    public void removeAllComponents()
    {
        components.clear();
    }

    private void paintBackground(Graphics g)
    {
        resetClip(g);
        g.drawImage(background, 0, 0, null);
    }

    private void paintComponents(Graphics g)
    {
        // Paint All Component
        for(Component b : components)
        {
            b.paint(g);
        }
    }
    
    public void paintBlack(Graphics g)
    {
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    public void paintSplash(Graphics g, long time)
    {
    	// Paint Background
    	paintBlack(g);
    	
    	// Get Graphics
    	Graphics2D g2 = (Graphics2D)g;
    	
    	if (time >= GameEngine.TIME_SPLASH - GameEngine.TIME_SPLASH_FADE)
    	{
    		int t = (int)(time - (GameEngine.TIME_SPLASH - GameEngine.TIME_SPLASH_FADE));
    		float alpha = 1.0f - (t / 1000f);

	    	// Create Alpha
	    	AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC, alpha);
	    	// Set Alpha
	    	g2.setComposite(ac);
    	} else if (time <= GameEngine.TIME_SPLASH_FADE)
    	{
    		float alpha = time / 1000f;

	    	// Create Alpha
	    	AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC, alpha);
	    	// Set Alpha
	    	g2.setComposite(ac);    		
    	}
    	
    	// Draw Image
        g2.drawImage(logo, SCREEN_WIDTH / 2 - logo.getWidth(null) / 2, SCREEN_HEIGHT / 2 - logo.getHeight(null) / 2, null);
    }

    public void paintMainMenu(Graphics g)
    {
        paintBackground(g);
        paintComponents(g);
    }

    public void paintCuriosity(Graphics g, String text)
    {
        paintBackground(g);

        // Get Graphics2D
        Graphics2D g2d = (Graphics2D)g;
        // Set Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Paint Text
        fntQuestion.drawString(g, text, CURIOBOX_X, CURIOBOX_Y, CURIOBOX_WIDTH, CURIOBOX_HEIGHT, TFont.TOP | TFont.LEFT);
    }

    public void paintGame(Graphics g, Player players[])
    {
        paintBackground(g);
        paintPlayers(g, players);
        paintPlayersTurn(g, players.length);
    }

    public void paintDice(Graphics g, int numDice)
    {
        // Remove any Clip
        resetClip(g);

        if (numDice <= 6)
            // Draw Dice
            g.drawImage(dice[numDice - 1], DICE_X, DICE_Y, null);


        // Draw Text
        fntDice.drawString(g, String.valueOf(numDice), DICE_TEXT_X, DICE_TEXT_Y, TFont.BASELINE | TFont.HCENTER);
    }

    public void paintAnswers(Graphics g, ArrayList<Answer> anwsers)
    {
        // Paint Checkboxes
        paintComponents(g);

        // Paint Answers
        paintAnswer(g, ANSWER1_X + ANSWERBOX_X, ANSWER1_Y + ANSWERBOX_Y, ANSWERBOX_WIDTH, ANSWERBOX_HEIGHT, anwsers.get(0));
        paintAnswer(g, ANSWER2_X + ANSWERBOX_X, ANSWER3_Y + ANSWERBOX_Y, ANSWERBOX_WIDTH, ANSWERBOX_HEIGHT, anwsers.get(1));
        paintAnswer(g, ANSWER3_X + ANSWERBOX_X, ANSWER3_Y + ANSWERBOX_Y, ANSWERBOX_WIDTH, ANSWERBOX_HEIGHT, anwsers.get(2));
    }

    private void paintAnswer(Graphics g, int x, int y, int width, int height, Answer answer)
    {
        // Remove any clip
        resetClip(g);
        //g.setColor(Color.RED);
        //g.fillRect(x, y, width, height);
        // Draw Text
        fntAnswerNormal.drawString(g, answer.getTexto(), x, y, width, height, TFont.JUSTIFY | TFont.TOP);
    }

    public void paintQuestion(Graphics g, String text)
    {
        // Remove any clip
        resetClip(g);
        // Get Graphics2D
        Graphics2D g2d = (Graphics2D)g;
        // Set Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Paint Question
        fntQuestion.drawString(g2d, text, QUESTION_X, QUESTION_Y, QUESTION_WIDTH, QUESTION_HEIGHT, TFont.VCENTER | TFont.HCENTER);
    }

    private void paintPlayersTurn(Graphics g, int numPlayers)
    {
        int x = PLAYERS_TURN_X;
        int y = PLAYERS_TURN_Y;

        // Reset clip
        resetClip(g);
        // Iterate Players
        for(int i = 0; i < numPlayers; i++)
        {
            if (ge.getTurn() == i)
            {
                g.drawImage(playerTurnBright,
                    x - (playerTurnBright.getWidth(null) >> 2) + 3,
                    y - (playerTurnBright.getHeight(null) >> 2) + 2,
                    null);
            }

            // Paint Turn
            g.drawImage(players[i], x, y, null);
            // Increment next
            x += PLAYERS_TURN_GAP;
        }
    }

    private void paintPlayers(Graphics g, Player players[])
    {
        for(Player p : players)
        {
            if (p.getNumPlayer() == ge.getTurn())
                paintPlayer(g, p, ge.playerRouteOffsetX, ge.playerRouteOffsetY);
            else
                paintPlayer(g, p, 0, 0);
        }

        // Paint Turn Player
        paintPlayer(g, players[ge.getTurn()], ge.playerRouteOffsetX, ge.playerRouteOffsetY);
    }

    private void paintPlayer(Graphics g, Player player, int offsetX, int offsetY)
    {
        // Get Square
        int numSquare = player.getNumSquare();
        // Create Coords
        int x = GameCanvas.SQUARES_COORD[ numSquare << 1     ] + offsetX;
        int y = GameCanvas.SQUARES_COORD[(numSquare << 1) + 1] + offsetY;

        // Reset Clip
        resetClip(g);

        // Check Actual Turn
        if (player.getNumPlayer() == ge.getTurn() && ge.getState() != GameEngine.STATE_GAME_MOVING)
        {
            g.drawImage(playerBright,
                x - (playerBright.getWidth(null) >> 1),
                y - (playerBright.getHeight(null) >> 1),
                null);
        }

        // Paint
        g.drawImage(players[player.getNumPlayer()],
                x - (players[player.getNumPlayer()].getWidth(null) >> 1),
                y - (players[player.getNumPlayer()].getHeight(null) >> 1),
                null);
    }

    private void resetClip(Graphics g)
    {
        g.setClip(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}

