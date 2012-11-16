/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Morpheo
 */
public class Button extends Component
{
    // Images
    private Image normal = null, pressed = null;

    public Button(int id, int x, int y, int width, int height, Image normal, Image pressed)
    {
        super(id, x, y, width, height);

        this.normal  = normal;
        this.pressed = pressed;
    }

    public Button(int id, int x, int y, Image normal, Image pressed)
    {
        this(id, x, y, normal.getWidth(null), normal.getHeight(null), normal, pressed);
    }

    public void paint(Graphics g)
    {
        // Get Image
        Image image = isPressed() ? pressed : normal;
        // Clip and Paint
        g.setClip(x, y, width, height);
        g.drawImage(image, x, y, null);
    }

    public void click()
    {
        state = STATE_PRESSED;
    }

    public void press()
    {
        state = STATE_PRESSED;
    }

    public void release()
    {
        state = STATE_NORMAL;
    }

    public void releaseClick()
    {
        state = STATE_NORMAL;
    }

    @Override
    public int getCursor()
    {
        return Cursor.HAND_CURSOR;
    }

    public Image getNormal()
    {
        return normal;
    }

    public void setNormal(Image normal)
    {
        this.normal = normal;
    }

    public Image getPressed()
    {
        return pressed;
    }

    public void setPressed(Image pressed)
    {
        this.pressed = pressed;
    }
}
