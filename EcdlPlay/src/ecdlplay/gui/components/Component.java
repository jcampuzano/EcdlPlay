/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui.components;

import java.awt.Cursor;
import java.awt.Graphics;

/**
 * Esta clase representa un componente gr�fico que puede reaccionar a la interacci�n por
 * parte del usuario. Es una clase abstracta por lo que la mayor implementaci�n la contiene cada 
 * implementaci�n de cada componente: Button, Checkbox y Label.
 * @author julio
 */
public abstract class Component
{
	/**
	 * Indica que el componente est� en estado normal
	 */
    protected static final int STATE_NORMAL  = 0;
    /**
     * Indica que el componente est� en estado presionado
     */
    protected static final int STATE_PRESSED = 1;

    /**
     * Identificador del componente
     */
    protected int id;

    /**
     * Coordinada X de la posici�n
     */
    protected int x;
    /**
     * Coordinada Y de la posici�n
     */
    protected int y;
    /**
     * Ancho del componente
     */
    protected int width;
    /**
     * Alto del componente
     */
    protected int height;

    /**
     * Estado del componente
     */
    protected int state;

    /**
     * Constructor por defecto del componente
     */
    public Component()
    {
        this(-1, 0, 0, 0, 0);
    }

    /**
     * Constructor de la clase componente con inicializaci�n de las variables
     * @param id Identificador del componente
     * @param x Coordenada X del componente
     * @param y Coordenada Y del componente
     * @param width Ancho del componente
     * @param height Alto del componente
     */
    public Component(int id, int x, int y, int width, int height)
    {
        this.id      = id;
        this.x       = x;
        this.y       = y;
        this.width   = width;
        this.height  = height;
        this.state   = STATE_NORMAL;
    }

    /**
     * M�todo abstracto que deben sobrescribir las clases heredadas. Se encarga de pintar
     * el propio componente
     * @param g
     */
    public abstract void paint(Graphics g);

    /**
     * M�todo abstracto que deben sobrescribir las clases heredadas. Se llama cuando se hace
     * click con el rat�n en el componente 
     */
    public abstract void click();
    
    /**
     * M�todo abstracto que deben sobrescribir las clases heredadas. Se llama cuando el componente
     * es presionado
     */
    public abstract void press();
    
    /**
     * M�todo abstracto que deben sobrescribir las clases heredadas. Se llama cuando el componente
     * es liberado de la presi�n
     */
    public abstract void release();
    
    /**
     * M�todo abstracto que deben sobrescribir las clases heredadas. Se llama cuando el rat�n deja
     * de ser pulsado
     */
    public abstract void releaseClick();

    /**
     * Devuelve el tipo de cursor que tendr� el rat�n cuando �ste se ponga encima del componente
     * @return
     */
    public int getCursor()
    {
        return Cursor.DEFAULT_CURSOR;
    }

    /**
     * Devuelve si el componente est� presionado
     * @return
     */
    public boolean isPressed()
    {
        return state == STATE_PRESSED;
    }

    /**
     * Devuelve el identificador del componente
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * Devuelve la coordenada X del componente
     */
    public int getX()
    {
        return x;
    }

    /**
     * Establece el valor de la coordenada X del componente
     * @param x La coordenada X del componente
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * Devuelve la coordenada Y del componente
     */
    public int getY()
    {
        return y;
    }

    /**
     * Establece el valor de la coordenada Y del componente
     * @param y La coordenada Y del componente
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Devuelve el ancho del componente
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * Establece el ancho del componente
     * @param width the width to set
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * Devuelve el alto del componente
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * Establece el alto del componente
     * @param height the height to set
     */
    public void setHeight(int height)
    {
        this.height = height;
    }
}
