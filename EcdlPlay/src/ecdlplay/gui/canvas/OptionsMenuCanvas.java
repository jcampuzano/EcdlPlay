/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui.canvas;

import ecdlplay.domain.GameEngine;
import ecdlplay.domain.entities.Module;
import ecdlplay.data.GameDataLoader;
import ecdlplay.gui.components.Button;
import ecdlplay.gui.components.Checkbox;
import ecdlplay.gui.components.Label;
import ecdlplay.gui.components.TFont;
import ecdlplay.utils.ImageLoader;

import java.awt.Font;
import java.awt.Graphics;

/**
 * Clase Canvas utilizada para pintar los componentes del men� de opciones del juego
 * @author julio
 */
public class OptionsMenuCanvas extends CanvasBase {

	/**
	 * Fuente que se utiliar� para escribir los nombres de los m�dulos
	 */
    private TFont fontModule;

    /**
     * Constructor que inicializa las variables
     * @param ge
     */
    public OptionsMenuCanvas(GameEngine ge) {
        super(ge);
    }

    /**
     * M�todo sobrescrito que se encarga de cargar los recursos (im�genes y fuentes) as� como
     * crear los distintos componentes que se visualizar�n en pantalla
     */
    @Override
    public void loadResources() {
        setBackground(ImageLoader.loadImageJAR("options_background.png"));


        loadModules();

        loadPlayers();
        loadButtons();

        // Load module font
        fontModule = new TFont("futura.ttf", 18, Font.BOLD, 0xFFFFFF);
    }

    /**
     * Pinta los componentes en pantalla
     */
    @Override
    public void paint(Graphics g) {
        paintBackground(g);
        paintComponents(g);

        paintModules(g);
    }

    /**
     * Escribe los nombres de los m�dulos junto a cada uno de los checkbox
     * @param g
     */
    private void paintModules(Graphics g) {
        resetClip(g);


        int actualY = GameCanvasConstants.OPTIONS_MENU_MODULE_BOX_X;

        for (Module module : GameDataLoader.getLoader().getGameData().getModules()) {


            fontModule.drawString(g, module.getName(),
                    GameCanvasConstants.OPTIONS_MENU_MODULE_BOX_X,
                    actualY,
                    GameCanvasConstants.OPTIONS_MENU_MODULE_BOX_WIDTH,
                    GameCanvasConstants.OPTIONS_MENU_MODULE_BOX_HEIGHT,
                    TFont.JUSTIFY | TFont.TOP);

            actualY += 40;

        }

    }

    /**
     * Carga los botones para seleccionar el n�mero de jugadores de la partida
     */
    private void loadPlayers() {
        // Component Players Label
        addComponent(new Label(
                -1,
                GameCanvasConstants.OPTIONS_MENU_PLAYERS_X,
                GameCanvasConstants.OPTIONS_MENU_PLAYERS_Y,
                ImageLoader.loadImageJAR("menu_options_players.png")));

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
    }

    /**
     * Carga los checkboxes para cada uno de los m�dulos dados de alta.
     */
    private void loadModules() {
        int i, actualId,actualY;

        // Component Module Label
        addComponent(new Label(
                -1,
                GameCanvasConstants.OPTIONS_MENU_MODULE_X,
                GameCanvasConstants.OPTIONS_MENU_MODULE_Y,
                ImageLoader.loadImageJAR("menu_options_module.png")));

        actualId = GameCanvasConstants.BUTTON_FIRST_MODULE;
        actualY = GameCanvasConstants.OPTIONS_MENU_MODULE_BUTTONS_Y;


        for (i = 0; i < GameDataLoader.getLoader().getGameData().getModules().size(); i++) {
            // Component Answers
            addComponent(new Checkbox(
                    actualId,
                    GameCanvasConstants.OPTIONS_MENU_MODULE_BUTTONS_X,
                    actualY,
                    ImageLoader.loadImageJAR("game_answerbox_1.png"),
                    ImageLoader.loadImageJAR("game_answerbox_2.png")));

            actualId++;
            actualY += 40;
        }
    }

    /**
     * Carga los botones de navegaci�n
     */
    private void loadButtons() {
        // Component Back
        addComponent(new Button(
                GameCanvasConstants.BUTTON_BACK,
                GameCanvasConstants.OPTIONS_MENU_BUTTON_BACK_X,
                GameCanvasConstants.OPTIONS_MENU_BUTTON_BACK_Y,
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
}
