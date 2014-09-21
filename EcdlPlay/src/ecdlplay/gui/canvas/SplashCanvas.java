/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui.canvas;

import ecdlplay.domain.GameEngine;
import ecdlplay.utils.ImageLoader;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Clase canvas utilizada para pintar la pantalla de bienvenida del juego.
 * @author julio
 */
public class SplashCanvas extends CanvasBase {
    
	/**
	 * Imagen que se mostrará al comienzo del juego
	 */
    private Image logo;

    /**
     * Constructo que inicializa las variables
     * @param ge
     */
    public SplashCanvas(GameEngine ge){
        super(ge);
    }
    
    /**
     * Carga la imagen del logo inicial
     */
    @Override
    public void loadResources() {
        this.logo = ImageLoader.loadImageJAR("splash.png");
    }

    /**
     * Pinta el logo inicial por pantalla
     */
    @Override
    public void paint(Graphics g) {
        // Paint Background
        paintBlack(g);

        // Draw Image
        g.drawImage(logo, GameCanvasConstants.SCREEN_WIDTH / 2 - logo.getWidth(null) / 2, GameCanvasConstants.SCREEN_HEIGHT / 2 - logo.getHeight(null) / 2, null);
    
    }
    
}
