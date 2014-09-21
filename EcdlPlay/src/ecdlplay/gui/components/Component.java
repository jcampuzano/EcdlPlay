/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui.components;

import java.awt.Cursor;
import java.awt.Graphics;

/**
 * Esta clase representa un componente gráfico que puede reaccionar a la interacción por
 * parte del usuario. Es una clase abstracta por lo que la mayor implementación la contiene cada 
 * implementación de cada componente: Button, Checkbox y Label.
 * @author julio
 */
public abstract class Component
{
	/**
	 * Indica que el componente está en estado normal
	 */
    protected static final int STATE_NORMAL  = 0;
    /**
     * Indica que el componente está en estado presionado
     */
    protected static final int STATE_PRESSED = 1;

    /**
     * Identificador del componente
     */
    protected int id;

    /**
     * Coordinada X de la posición
     */
    protected int x;
    /**
     * Coordinada Y de la posición
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
     * Constructor de la clase componente con inicialización de las variables
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
     * Método abstracto que deben sobrescribir las clases heredadas. Se encarga de pintar
     * el propio componente
     * @param g
     */
    public abstract void paint(Graphics g);

    /**
     * Método abstracto que deben sobrescribir las clases heredadas. Se llama cuando se hace
     * click con el ratón en el componente 
     */
    public abstract void click();
    
    /**
     * Método abstracto que deben sobrescribir las clases heredadas. Se llama cuando el componente
     * es presionado
     */
    public abstract void press();
    
    /**
     * Método abstracto que deben sobrescribir las clases heredadas. Se llama cuando el componente
     * es liberado de la presión
     */
    public abstract void release();
    
    /**
     * Método abstracto que deben sobrescribir las clases heredadas. Se llama cuando el ratón deja
     * de ser pulsado
     */
    public abstract void releaseClick();

    /**
     * Devuelve el tipo de cursor que tendrá el ratón cuando éste se ponga encima del componente
     * @return
     */
    public int getCursor()
    {
        return Cursor.DEFAULT_CURSOR;
    }

    /**
     * Devuelve si el componente está presionado
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
