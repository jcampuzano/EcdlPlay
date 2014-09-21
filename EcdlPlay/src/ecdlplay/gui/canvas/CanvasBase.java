/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui.canvas;

import ecdlplay.domain.GameEngine;
import ecdlplay.domain.GameEngineConstants;
import ecdlplay.gui.components.Component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase base utilizada para pintar la pantalla según el estado del objeto GameEngine.
 * Contiene todos los métodos comunes que serán utilizados por GameEngine y por las clases
 * heredadas para pintar el escenario.
 * @author julio
 */
public abstract class CanvasBase {

	/**
	 * Indica si los componentes serán visibles en pantalla o no
	 */
    protected boolean visible;
    /**
     * Lista de componentes que deberán ser pintado
     */
    protected List<Component> components;
    /**
     * Instancia de GameEngine
     */
    protected GameEngine gameEngine;
    
    /**
     * Fondo de la pantalla
     */
    private Image background;

    /**
     * Constructor que inicializa las variables
     * @param ge
     */
    protected CanvasBase(GameEngine ge) {
        this.gameEngine = ge;
        this.components = new ArrayList<Component>();
        this.visible = true;
    }

    /**
     * Método abstracto que debe ser rescrito por las clases heredadas. Se
     * encargará de cargar los recursos necesarios (imágenes, fuentes, etc)
     */
    public abstract void loadResources();

    /**
     * Método abstracto que debe ser rescrito por las clases heredadas. Se 
     * encargará de pintar cada uno de los componentes.
     * @param g
     */
    public abstract void paint(Graphics g);

    /**
     * Devuelve si los componentes serán visibles o no
     * @return
     */
    public boolean isComponentsVisible() {
        return visible;
    }

    /**
     * Establece la visibilidad de los componentes
     * @param visible Valor de la visibilidad
     */
    public void setComponentsVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Obtiene el componente cuyo identificador se corresponde con el pasado
     * por parámetro. Si no existe, devuelve null.
     * @param id Identificador del componente a buscar
     * @return
     */
    public Component getComponent(int id) {
        for (Component c : components) {
            if (c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    /**
     * Obtiene el componenete que se encuentra en la posición determinada por las
     * coordenadas pasadas por parámetro
     * @param x Coordenada X
     * @param y Coordenada Y
     * @return
     */
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

    /**
     * Añade un componente a la lista
     * @param component
     */
    public void addComponent(Component component) {
        components.add(component);
    }
    
    /**
     * Elimina el componente que se encuentra en las coordenadas pasadas por 
     * parámetros si es que existe. 
     * @param x Coordenada X
     * @param y Coordenada Y
     */
    public void removeComponent(int x, int y) {
        Component c = getComponent(x, y);
        if (c != null){
            components.remove(c);
        }
    }

    /**
     * Método que se ejecuta cuando el usuario hace click sobre cualquier parte de la pantalla.
     * Si existe un componente en esa posición, llama al método click del mismo.
     * @param x Coordenada X
     * @param y Coordenada Y
     */
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

    /**
     * Método que se ejecuta cuando el usuario libera el botón del ratón
     */
    public void mouseReleased() {
        releaseAllComponents();
    }

    /**
     * Llama al método releaseClick de todos los componentes
     */
    private void releaseAllComponents() {
        for (Component c : components) {
            c.releaseClick();
        }
    }

    /**
     * Elimina todos los componentes de la lista
     */
    public void removeAllComponents() {
        components.clear();
    }

    /**
     * Pinta la pantalla de negro
     * @param g
     */
    protected void paintBlack(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameCanvasConstants.SCREEN_WIDTH, GameCanvasConstants.SCREEN_HEIGHT);
    }

    /**
     * Pinta cada uno de los componentes
     * @param g
     */
    protected void paintComponents(Graphics g) {
        // Paint All Component
        for (Component b : components) {
            b.paint(g);
        }
    }

    /**
     * Obtiene la imagen de fondo
     * @return the background
     */
    public Image getBackground() {
        return background;
    }

    /**
     * Establece la imagen de fondo
     * @param Imagen de fondo a establecer
     */
    public void setBackground(Image background) {
        this.background = background;
    }

    /**
     * Pinta la imagen de fondo
     * @param g
     */
    protected void paintBackground(Graphics g) {
        resetClip(g);
        g.drawImage(background, 0, 0, null);
    }

    /**
     * Restablece los valores de la imagen
     * @param g
     */
    protected void resetClip(Graphics g) {
        g.setClip(0, 0, GameCanvasConstants.SCREEN_WIDTH, GameCanvasConstants.SCREEN_HEIGHT);
    }

    /**
     * Obtiene la instancia de la clase adecuada según el estado del juego (patrón Factory)
     * @param ge Actual GameEngine
     * @return
     */
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
                return new GameCanvas(ge);
        }
    }
    
}
