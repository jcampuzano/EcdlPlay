/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.GameEngine;
import ecdlplay.domain.GameEngineConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julio
 */
public abstract class CanvasBase {

    protected boolean visible;
    protected List<Component> components;
    protected GameEngine gameEngine;
    private Image background;

    protected CanvasBase(GameEngine ge) {
        this.gameEngine = ge;
        this.components = new ArrayList<Component>();
        this.visible = true;
    }

    public abstract void loadResources();

    public abstract void paint(Graphics g);

    public boolean isComponentsVisible() {
        return visible;
    }

    public void setComponentsVisible(boolean visible) {
        this.visible = visible;
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

    public void addComponent(Component component) {
        components.add(component);
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

    protected void paintBlack(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameCanvasConstants.SCREEN_WIDTH, GameCanvasConstants.SCREEN_HEIGHT);
    }

    protected void paintComponents(Graphics g) {
        // Paint All Component
        for (Component b : components) {
            b.paint(g);
        }
    }

    /**
     * @return the background
     */
    public Image getBackground() {
        return background;
    }

    /**
     * @param background the background to set
     */
    public void setBackground(Image background) {
        this.background = background;
    }

    protected void paintBackground(Graphics g) {
        resetClip(g);
        g.drawImage(background, 0, 0, null);
    }

    protected void resetClip(Graphics g) {
        g.setClip(0, 0, GameCanvasConstants.SCREEN_WIDTH, GameCanvasConstants.SCREEN_HEIGHT);
    }

    public static CanvasBase getCanvas(GameEngine ge) {
        switch (ge.getState()) {
            case GameEngineConstants.STATE_PRE_SPLASH:
                return new PreSplashCanvas(ge);
            case GameEngineConstants.STATE_SPLASH:
                return new SplashCanvas(ge);
            case GameEngineConstants.STATE_MAIN_MENU:
                return new MainMenuCanvas(ge);
            case GameEngineConstants.STATE_OPTIONS_MENU:
                return new OptionsMenuCanvas(ge);
            default:
                return new GGameCanvas(ge);
        }
    }
}
