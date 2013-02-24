/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.GameEngine;
import java.awt.Graphics;

/**
 *
 * @author julio
 */
public class PreSplashCanvas extends CanvasBase {
    
    public PreSplashCanvas(GameEngine ge){
        super(ge);
    }

    @Override
    public void loadResources() {
        
    }

    @Override
    public void paint(Graphics g) {
        paintBlack(g);
    }
    
}
