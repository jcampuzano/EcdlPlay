/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import ecdlplay.gui.GameCanvas;

/**
 *
 * @author julio
 */
public class GameEngine implements Runnable {

    private boolean running;
    private States state;
    private GameCanvas canvas;

    public GameEngine(GameCanvas canvas) {
        this.canvas = canvas;
    }

    
    @Override
    public void run() {
        this.running = true;
        this.state = States.PRE_SPLASH;

        while (running) {
        }
    }
    
}
