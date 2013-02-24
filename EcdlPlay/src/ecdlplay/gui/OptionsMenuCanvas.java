/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.GameEngine;
import ecdlplay.utils.ImageLoader;
import java.awt.Graphics;

/**
 *
 * @author julio
 */
public class OptionsMenuCanvas extends CanvasBase {

    public OptionsMenuCanvas(GameEngine ge){
        super(ge);
    }
    
    @Override
    public void loadResources() {
        setBackground(ImageLoader.loadImageJAR("options_background.png"));
        
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
//        addComponent(new ComboBox(
//                GameCanvasConstants.COMBO_MODULE,
//                GameCanvasConstants.OPTIONS_MENU_MODULE_COMBO_X,
//                GameCanvasConstants.OPTIONS_MENU_MODULE_COMBO_Y,
//                GameCanvasConstants.OPTIONS_MENU_MODULE_COMBO_HEIGHT,
//                GameCanvasConstants.OPTIONS_MENU_MODULE_COMBO_WIDTH,
//                GameDataLoader.getLoader().getGameData().getModules(),
//                null,
//                fntModules)); //TODO:
        
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

    @Override
    public void paint(Graphics g) {
        paintBackground(g);
        paintComponents(g);
    }
    
}
