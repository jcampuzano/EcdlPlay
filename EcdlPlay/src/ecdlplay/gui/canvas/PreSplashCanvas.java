/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui.canvas;

import ecdlplay.domain.GameEngine;
import java.awt.Graphics;

/**
 * Clase Canvas utilizada para pintar los componentes de la p�gina inicial. B�sicamente, pinta la pantalla de negro
 * @author julio
 */
public class PreSplashCanvas extends CanvasBase {
    
	/**
	 * Constructor que inicializa las variables
	 * @param ge
	 */
    public PreSplashCanvas(GameEngine ge){
        super(ge);
    }

    /**
     * M�todo sobrescrito de la clase base. No necesita cargar ning�n recurso.
     */
    @Override
    public void loadResources() {
        
    }

    /**
     * Pinta la pantalla de negro
     */
    @Override
    public void paint(Graphics g) {
        paintBlack(g);
    }
    
}
