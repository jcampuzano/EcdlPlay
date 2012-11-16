/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui;

import java.awt.Cursor;
import java.awt.Graphics;

/**
 *
 * @author Morpheo
 */
public abstract class Component
{
    protected static final int STATE_NORMAL  = 0;
    protected static final int STATE_PRESSED = 1;

    // ID
    protected int id;
    // Coord and Size
    protected int x, y, width, height;
    // State
    protected int state;

    public Component()
    {
        this(-1, 0, 0, 0, 0);
    }

    public Component(int id, int x, int y, int width, int height)
    {
        this.id      = id;
        this.x       = x;
        this.y       = y;
        this.width   = width;
        this.height  = height;
        this.state   = STATE_NORMAL;
    }

    public abstract void paint(Graphics g);

    public abstract void click();
    public abstract void press();
    public abstract void release();
    public abstract void releaseClick();

    public int getCursor()
    {
        return Cursor.DEFAULT_CURSOR;
    }

    public boolean isPressed()
    {
        return state == STATE_PRESSED;
    }

    public int getId()
    {
        return id;
    }

    /**
     * @return the x
     */
    public int getX()
    {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY()
    {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * @return the width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height)
    {
        this.height = height;
    }
}
