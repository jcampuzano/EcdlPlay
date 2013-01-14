/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.data.GameData;
import ecdlplay.data.GameDataLoader;
import ecdlplay.domain.Answer;
import ecdlplay.domain.Entity;
import ecdlplay.domain.GameEngine;
import ecdlplay.domain.GameEngineConstants;
import ecdlplay.domain.Player;
import ecdlplay.utils.ImageLoader;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Morpheo
 */
public class GameCanvas {
    
    
    // GameEngine
    private GameEngine ge;
    
    // Images
    private Image logo;
    private Image background;
    private Image dice[];
    private Image players[];
    private Image playerBright;
    private Image playerTurnBright;
    // Fonts
    private TFont fntQuestion = null;
    private TFont fntAnswerNormal = null;
    private TFont fntDice = null;
    private TFont fntModules = null;
    // Vector Buttons
    private List<Component> components;
    private boolean visible;

    public GameCanvas(GameEngine ge) {
        this.ge = ge;
        this.components = new ArrayList<Component>();
        this.visible = true;
    }

    public void loadResource(int resource) {
        switch (resource) {
            case GameCanvasConstants.RES_SPLASH:
                logo = ImageLoader.loadImageJAR("splash.png");
                break;
            case GameCanvasConstants.RES_CURIO_BACKGROUND:
                background = ImageLoader.loadImageJAR("curiosity_background.png");
                break;
            case GameCanvasConstants.RES_CURIO_FONT:
                fntQuestion = new TFont("futura.ttf", 20, Font.BOLD, 0xFFFFFF);
                break;
            case GameCanvasConstants.RES_MAIN_MENU_BACKGROUND:
                background = ImageLoader.loadImageJAR("menu_background.png");
                break;
            case GameCanvasConstants.RES_MAIN_MENU_BUTTONS:
                loadMainMenuComponents();
                break;
            case GameCanvasConstants.RES_OPTIONS_MENU_BACKGROUND:
                background = ImageLoader.loadImageJAR("options_background.png");
                break;
            case GameCanvasConstants.RES_OPTIONS_MENU_BUTTONS:
                loadOptionsMenuComponents();
                break;
            case GameCanvasConstants.RES_GAME_BACKGROUND:
                background = ImageLoader.loadImageJAR("game_background.png");
                break;
            case GameCanvasConstants.RES_GAME_BUTTONS:
                loadGameButtons();
                break;
            case GameCanvasConstants.RES_GAME_QUESTION_FONT:
                fntQuestion = new TFont("futura.ttf", 18, Font.BOLD, 0xFFFFFF);
                fntAnswerNormal = new TFont("futura.ttf", 14, Font.PLAIN, 0xEED4F5);
                fntDice = new TFont("futura.ttf", 30, Font.BOLD, 0xFFFFFF);
                break;
            case GameCanvasConstants.RES_GAME_ANSWER_BOX:
                loadAnswerComponents();
                break;
            case GameCanvasConstants.RES_GAME_DICE:
                // Create Array
                dice = new Image[6];
                // Load Images
                for (int i = 0; i < dice.length; i++) {
                    dice[i] = ImageLoader.loadImageJAR("game_dice_" + (i + 1) + ".png");
                }
            case GameCanvasConstants.RES_GAME_PLAYERS:
                // Create Players array
                players = new Image[4];
                // Load Images
                for (int i = 0; i < players.length; i++) {
                    players[i] = ImageLoader.loadImageJAR("game_player_" + (i + 1) + ".png");
                }
                // Load Brights
                playerBright = ImageLoader.loadImageJAR("game_player_bright.png");
                playerTurnBright = ImageLoader.loadImageJAR("game_player_turn_bright.png");
                break;
        }
    }

    public void unloadResource(int resource) {
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public Component getComponent(int id) {
        for (Component c : components) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public Component getComponent(int x, int y) {
        if (!isComponentsVisible()) {
            return null;
        }

        for (Component b : components) {
            if (x >= b.getX() && x <= b.getX() + b.getWidth()
                    && y >= b.getY() && y <= b.getY() + b.getHeight()) {
                return b;
            }
        }

        return null;
    }

    public void setComponentsVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isComponentsVisible() {
        return visible;
    }

    public void mousePressed(int x, int y) {
        // Release All
        releaseAllComponents();

        // Get Component Clicked
        Component c = getComponent(x, y);

        // Check Component
        if (c != null) // Click
        {
            c.click();
        }
    }

    public void mouseReleased() {
        releaseAllComponents();
    }

    private void releaseAllComponents() {
        for (Component c : components) {
            c.releaseClick();
        }
    }

    public void removeAllComponents() {
        components.clear();
    }

    private void paintBackground(Graphics g) {
        resetClip(g);
        g.drawImage(background, 0, 0, null);
    }

    private void paintComponents(Graphics g) {
        // Paint All Component
        for (Component b : components) {
            b.paint(g);
        }
    }

    public void paintBlack(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameCanvasConstants.SCREEN_WIDTH, GameCanvasConstants.SCREEN_HEIGHT);
    }

    public void paintSplash(Graphics g, long time) {
        // Paint Background
        paintBlack(g);

        // Get Graphics
        Graphics2D g2 = (Graphics2D) g;

        if (time >= GameEngineConstants.TIME_SPLASH - GameEngineConstants.TIME_SPLASH_FADE) {
            int t = (int) (time - (GameEngineConstants.TIME_SPLASH - GameEngineConstants.TIME_SPLASH_FADE));
            float alpha = 1.0f - (t / 1000f);

            // Create Alpha
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC, alpha);
            // Set Alpha
            g2.setComposite(ac);
        } else if (time <= GameEngineConstants.TIME_SPLASH_FADE) {
            float alpha = time / 1000f;

            // Create Alpha
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC, alpha);
            // Set Alpha
            g2.setComposite(ac);
        }

        // Draw Image
        g2.drawImage(logo, GameCanvasConstants.SCREEN_WIDTH / 2 - logo.getWidth(null) / 2, GameCanvasConstants.SCREEN_HEIGHT / 2 - logo.getHeight(null) / 2, null);
    }

    public void paintMainMenu(Graphics g) {
        paintBackground(g);
        paintComponents(g);
    }

    public void paintCuriosity(Graphics g, String text) {
        paintBackground(g);

        // Get Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        // Set Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Paint Text
        fntQuestion.drawString(g, text, 
                GameCanvasConstants.CURIOBOX_X, 
                GameCanvasConstants.CURIOBOX_Y, 
                GameCanvasConstants.CURIOBOX_WIDTH, 
                GameCanvasConstants.CURIOBOX_HEIGHT, 
                TFont.TOP | TFont.LEFT);
    }

    public void paintGame(Graphics g, Player players[]) {
        paintBackground(g);
        paintPlayers(g, players);
        paintPlayersTurn(g, players.length);
    }

    public void paintDice(Graphics g, int numDice) {
        // Remove any Clip
        resetClip(g);

        if (numDice <= 6) // Draw Dice
        {
            g.drawImage(dice[numDice - 1], GameCanvasConstants.DICE_X, GameCanvasConstants.DICE_Y, null);
        }


        // Draw Text
        fntDice.drawString(g, String.valueOf(numDice), 
                GameCanvasConstants.DICE_TEXT_X, 
                GameCanvasConstants.DICE_TEXT_Y, 
                TFont.BASELINE | TFont.HCENTER);
    }

    public void paintAnswers(Graphics g, ArrayList<Answer> anwsers) {
        // Paint Checkboxes
        paintComponents(g);

        // Paint Answers
        paintAnswer(g, 
                GameCanvasConstants.ANSWER1_X + GameCanvasConstants.ANSWERBOX_X, 
                GameCanvasConstants.ANSWER1_Y + GameCanvasConstants.ANSWERBOX_Y, 
                GameCanvasConstants.ANSWERBOX_WIDTH, 
                GameCanvasConstants.ANSWERBOX_HEIGHT, 
                anwsers.get(0));
        
        paintAnswer(g, 
                GameCanvasConstants.ANSWER2_X + GameCanvasConstants.ANSWERBOX_X, 
                GameCanvasConstants.ANSWER3_Y + GameCanvasConstants.ANSWERBOX_Y, 
                GameCanvasConstants.ANSWERBOX_WIDTH, 
                GameCanvasConstants.ANSWERBOX_HEIGHT, 
                anwsers.get(1));
        
        paintAnswer(g, 
                GameCanvasConstants.ANSWER3_X + GameCanvasConstants.ANSWERBOX_X, 
                GameCanvasConstants.ANSWER3_Y + GameCanvasConstants.ANSWERBOX_Y, 
                GameCanvasConstants.ANSWERBOX_WIDTH, 
                GameCanvasConstants.ANSWERBOX_HEIGHT, 
                anwsers.get(2));
    }

    private void paintAnswer(Graphics g, int x, int y, int width, int height, Answer answer) {
        // Remove any clip
        resetClip(g);
        //g.setColor(Color.RED);
        //g.fillRect(x, y, width, height);
        // Draw Text
        fntAnswerNormal.drawString(g, answer.getTexto(), x, y, width, height, TFont.JUSTIFY | TFont.TOP);
    }

    public void paintQuestion(Graphics g, String text) {
        // Remove any clip
        resetClip(g);
        // Get Graphics2D
        Graphics2D g2d = (Graphics2D) g;
        // Set Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Paint Question
        fntQuestion.drawString(g2d, text, 
                GameCanvasConstants.QUESTION_X, 
                GameCanvasConstants.QUESTION_Y, 
                GameCanvasConstants.QUESTION_WIDTH, 
                GameCanvasConstants.QUESTION_HEIGHT, 
                TFont.VCENTER | TFont.HCENTER);
    }

    private void paintPlayersTurn(Graphics g, int numPlayers) {
        int x = GameCanvasConstants.PLAYERS_TURN_X;
        int y = GameCanvasConstants.PLAYERS_TURN_Y;

        // Reset clip
        resetClip(g);
        // Iterate Players
        for (int i = 0; i < numPlayers; i++) {
            if (ge.getTurn() == i) {
                g.drawImage(playerTurnBright,
                        x - (playerTurnBright.getWidth(null) >> 2) + 3,
                        y - (playerTurnBright.getHeight(null) >> 2) + 2,
                        null);
            }

            // Paint Turn
            g.drawImage(players[i], x, y, null);
            // Increment next
            x += GameCanvasConstants.PLAYERS_TURN_GAP;
        }
    }

    private void paintPlayers(Graphics g, Player players[]) {
        for (Player p : players) {
            if (p.getNumPlayer() == ge.getTurn()) {
                paintPlayer(g, p, ge.playerRouteOffsetX, ge.playerRouteOffsetY);
            } else {
                paintPlayer(g, p, 0, 0);
            }
        }

        // Paint Turn Player
        paintPlayer(g, players[ge.getTurn()], ge.playerRouteOffsetX, ge.playerRouteOffsetY);
    }

    private void paintPlayer(Graphics g, Player player, int offsetX, int offsetY) {
        // Get Square
        int numSquare = player.getNumSquare();
        // Create Coords
        int x = GameCanvasConstants.SQUARES_COORD[ numSquare << 1] + offsetX;
        int y = GameCanvasConstants.SQUARES_COORD[(numSquare << 1) + 1] + offsetY;

        // Reset Clip
        resetClip(g);

        // Check Actual Turn
        if (player.getNumPlayer() == ge.getTurn() && ge.getState() != GameEngineConstants.STATE_GAME_MOVING) {
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

    private void resetClip(Graphics g) {
        g.setClip(0, 0, GameCanvasConstants.SCREEN_WIDTH, GameCanvasConstants.SCREEN_HEIGHT);
    }

    private void loadOptionsMenuComponents() {
                
        fntModules = new TFont("futura.ttf", 14, Font.PLAIN, 0xFFFFFF);

        
        // Component Back
        addComponent(new Button(
                GameCanvasConstants.BUTTON_BACK,
                GameCanvasConstants.OPTIONS_MENU_BUTTON_BACK_X,
                GameCanvasConstants.OPTIONS_MENU_BUTTON_BACK_Y,
                ImageLoader.loadImageJAR("menu_button_back_1.png"),
                ImageLoader.loadImageJAR("menu_button_back_2.png")));
        // Component Module Label
        addComponent(new Label(
                -1,
                GameCanvasConstants.OPTIONS_MENU_MODULE_X,
                GameCanvasConstants.OPTIONS_MENU_MODULE_Y,
                ImageLoader.loadImageJAR("menu_options_module.png")));
        // Component Players Label
        addComponent(new Label(
                -1,
                GameCanvasConstants.OPTIONS_MENU_PLAYERS_X,
                GameCanvasConstants.OPTIONS_MENU_PLAYERS_Y,
                ImageLoader.loadImageJAR("menu_options_players.png")));
        
        // Component Module
        addComponent(new ComboBox(
                GameCanvasConstants.COMBO_MODULE,
                GameCanvasConstants.OPTIONS_MENU_MODULE_COMBO_X,
                GameCanvasConstants.OPTIONS_MENU_MODULE_COMBO_Y,
                GameCanvasConstants.OPTIONS_MENU_MODULE_COMBO_HEIGHT,
                GameCanvasConstants.OPTIONS_MENU_MODULE_COMBO_WIDTH,
                GameDataLoader.getLoader().getGameData().getModules(),
                null,
                fntModules)); //TODO:
        
        // Component Players
        addComponent(new Checkbox(
                GameCanvasConstants.BUTTON_1PLAYER,
                GameCanvasConstants.OPTIONS_MENU_1PLAYER_X,
                GameCanvasConstants.OPTIONS_MENU_1PLAYER_Y,
                ImageLoader.loadImageJAR("menu_options_1p_1.png"),
                ImageLoader.loadImageJAR("menu_options_1p_2.png")));
        addComponent(new Checkbox(
                GameCanvasConstants.BUTTON_2PLAYER,
                GameCanvasConstants.OPTIONS_MENU_2PLAYER_X,
                GameCanvasConstants.OPTIONS_MENU_2PLAYER_Y,
                ImageLoader.loadImageJAR("menu_options_2p_1.png"),
                ImageLoader.loadImageJAR("menu_options_2p_2.png")));
        addComponent(new Checkbox(
                GameCanvasConstants.BUTTON_3PLAYER,
                GameCanvasConstants.OPTIONS_MENU_3PLAYER_X,
                GameCanvasConstants.OPTIONS_MENU_3PLAYER_Y,
                ImageLoader.loadImageJAR("menu_options_3p_1.png"),
                ImageLoader.loadImageJAR("menu_options_3p_2.png")));
        addComponent(new Checkbox(
                GameCanvasConstants.BUTTON_4PLAYER,
                GameCanvasConstants.OPTIONS_MENU_4PLAYER_X,
                GameCanvasConstants.OPTIONS_MENU_4PLAYER_Y,
                ImageLoader.loadImageJAR("menu_options_4p_1.png"),
                ImageLoader.loadImageJAR("menu_options_4p_2.png")));
        // Component Help
        addComponent(new Button(
                GameCanvasConstants.BUTTON_HELP,
                GameCanvasConstants.MAIN_MENU_BUTTON_HELP_X,
                GameCanvasConstants.MAIN_MENU_BUTTON_HELP_Y,
                ImageLoader.loadImageJAR("menu_button_help.png"),
                ImageLoader.loadImageJAR("menu_button_help.png")));
        
    }

    private void loadMainMenuComponents() {
        // Component Start
        addComponent(new Button(
                GameCanvasConstants.BUTTON_START,
                GameCanvasConstants.MAIN_MENU_BUTTON_START_X,
                GameCanvasConstants.MAIN_MENU_BUTTON_START_Y,
                ImageLoader.loadImageJAR("menu_button_start_1.png"),
                ImageLoader.loadImageJAR("menu_button_start_2.png")));
        // Component Options
        addComponent(new Button(
                GameCanvasConstants.BUTTON_OPTIONS,
                GameCanvasConstants.MAIN_MENU_BUTTON_OPTIONS_X,
                GameCanvasConstants.MAIN_MENU_BUTTON_OPTIONS_Y,
                ImageLoader.loadImageJAR("menu_button_options_1.png"),
                ImageLoader.loadImageJAR("menu_button_options_2.png")));
        // Component Exit
        addComponent(new Button(
                GameCanvasConstants.BUTTON_EXIT,
                GameCanvasConstants.MAIN_MENU_BUTTON_EXIT_X,
                GameCanvasConstants.MAIN_MENU_BUTTON_EXIT_Y,
                ImageLoader.loadImageJAR("menu_button_exit_1.png"),
                ImageLoader.loadImageJAR("menu_button_exit_2.png")));
        // Component Help
        addComponent(new Button(
                GameCanvasConstants.BUTTON_HELP,
                GameCanvasConstants.MAIN_MENU_BUTTON_HELP_X,
                GameCanvasConstants.MAIN_MENU_BUTTON_HELP_Y,
                ImageLoader.loadImageJAR("menu_button_help.png"),
                ImageLoader.loadImageJAR("menu_button_help.png")));
    }

    private void loadGameButtons() {
        // Component Back
        addComponent(new Button(
                GameCanvasConstants.BUTTON_BACK,
                GameCanvasConstants.GAME_BUTTON_BACK_X,
                GameCanvasConstants.GAME_BUTTON_BACK_Y,
                ImageLoader.loadImageJAR("menu_button_back_1.png"),
                ImageLoader.loadImageJAR("menu_button_back_2.png")));
        // Component Help
        addComponent(new Button(
                GameCanvasConstants.BUTTON_HELP,
                GameCanvasConstants.MAIN_MENU_BUTTON_HELP_X,
                GameCanvasConstants.MAIN_MENU_BUTTON_HELP_Y,
                ImageLoader.loadImageJAR("menu_button_help.png"),
                ImageLoader.loadImageJAR("menu_button_help.png")));
    }

    private void loadAnswerComponents() {
        // Component Answers
        addComponent(new Checkbox(
                GameCanvasConstants.BUTTON_ANSWER1,
                GameCanvasConstants.ANSWER1_X,
                GameCanvasConstants.ANSWER1_Y,
                ImageLoader.loadImageJAR("game_answerbox_1.png"),
                ImageLoader.loadImageJAR("game_answerbox_2.png")));
        addComponent(new Checkbox(
                GameCanvasConstants.BUTTON_ANSWER2,
                GameCanvasConstants.ANSWER2_X,
                GameCanvasConstants.ANSWER2_Y,
                ImageLoader.loadImageJAR("game_answerbox_1.png"),
                ImageLoader.loadImageJAR("game_answerbox_2.png")));
        addComponent(new Checkbox(
                GameCanvasConstants.BUTTON_ANSWER3,
                GameCanvasConstants.ANSWER3_X,
                GameCanvasConstants.ANSWER3_Y,
                ImageLoader.loadImageJAR("game_answerbox_1.png"),
                ImageLoader.loadImageJAR("game_answerbox_2.png")));
    }
}
