/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author julio
 */
public class ImageContainer extends JPanel{
    private Image image;
    
    public void setImage(Image image){
        this.image = image;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, WIDTH, HEIGHT, this);
    }
}
