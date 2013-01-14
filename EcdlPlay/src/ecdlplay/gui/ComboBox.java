/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.Entity;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author julio
 */
public class ComboBox<T> extends Component {

    private ArrayList<T> data;
    private T selected;
    private TFont font;
    
    private final int separate = 8;

    public ComboBox(int id, int x, int y, int height, int width, ArrayList<T> data, T selected, TFont font) {
        super(id, x, y, width, height);

        this.data = data;
        this.selected = selected;
        this.font = font;
    }

    @Override
    public void paint(Graphics g) {
        int sum = 0;
        for(T item : this.data){
            font.drawString(g, item.toString(), this.getX(), this.getY() + sum, TFont.JUSTIFY | TFont.TOP);
            sum += separate;
        }

    }

    @Override
    public void click() {
        //state = STATE_PRESSED;
    }

    @Override
    public void press() {
        state = STATE_PRESSED;
    }

    @Override
    public void release() {
        state = STATE_NORMAL;
    }

    @Override
    public void releaseClick() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getCursor() {
        return Cursor.HAND_CURSOR;
    }
}
