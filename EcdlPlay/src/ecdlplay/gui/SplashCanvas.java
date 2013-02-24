/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.GameEngine;
import ecdlplay.utils.ImageLoader;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author julio
 */
public class SplashCanvas extends CanvasBase {
    
    private Image logo;

    public SplashCanvas(GameEngine ge){
        super(ge);
    }
    
    @Override
    public void loadResources() {
        this.logo = ImageLoader.loadImageJAR("splash.png");
    }

    @Override
    public void paint(Graphics g) {
        // Paint Background
        paintBlack(g);

        // Draw Image
        g.drawImage(logo, GameCanvasConstants.SCREEN_WIDTH / 2 - logo.getWidth(null) / 2, GameCanvasConstants.SCREEN_HEIGHT / 2 - logo.getHeight(null) / 2, null);
    
    }
    
}
