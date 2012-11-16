/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Morpheo
 */
public class Label extends Component
{
    // Images
    private Image normal = null;

    public Label(int id, int x, int y, int width, int height, Image normal)
    {
        super(id, x, y, width, height);

        this.normal  = normal;
    }

    public Label(int id, int x, int y, Image normal)
    {
        this(id, x, y, normal.getWidth(null), normal.getHeight(null), normal);
    }

    public void paint(Graphics g)
    {
        // Clip and Paint
        g.setClip(x, y, width, height);
        g.drawImage(normal, x, y, null);
    }

    public void click()
    {
        state = STATE_NORMAL;
    }

    public void press()
    {
        state = STATE_NORMAL;
    }

    public void release()
    {
        state = STATE_NORMAL;
    }

    public void releaseClick()
    {
        state = STATE_NORMAL;
    }

    public Image getNormal()
    {
        return normal;
    }

    public void setNormal(Image normal)
    {
        this.normal = normal;
    }
}
