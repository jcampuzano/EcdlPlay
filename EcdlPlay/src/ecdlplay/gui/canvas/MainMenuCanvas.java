/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui.canvas;

import ecdlplay.domain.GameEngine;
import ecdlplay.gui.components.Button;
import ecdlplay.utils.ImageLoader;

import java.awt.Graphics;

/**
 * Clase Canvas utilizada para pintar los componentes del menú principal del juego
 * @author julio
 */
public class MainMenuCanvas extends CanvasBase {
        
	/**
     * Constructor que inicializa las variables
     * @param ge
     */
    public MainMenuCanvas(GameEngine ge){
        super(ge);
    }
    
    /**
     * Método sobrescrito de la clase base que se encarga de cargar los botones 
     * de navegación del menú principal.
     */
    @Override
    public void loadResources() {
        setBackground(ImageLoader.loadImageJAR("menu_background.png"));
        
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

    /**
     * Pinta los componentes del menú principal
     */
    @Override
    public void paint(Graphics g) {
        paintBackground(g);
        paintComponents(g);
    }
    
}
