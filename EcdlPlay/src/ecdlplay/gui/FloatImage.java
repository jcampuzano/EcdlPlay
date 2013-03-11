/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.GameEngine;
import ecdlplay.utils.ImageLoader;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.JFrame;

/**
 *
 * @author julio
 */
public class FloatImage extends JFrame {

    private ImageContainer imagenContainer;
    private static FloatImage instance;
    
    
    public static FloatImage getInstance() {
        if (instance == null) {
            instance = new FloatImage();
        }
        return instance;
    }
   
    void setImage(Image image) {
        this.imagenContainer.setImage(image);
    }
    
    public FloatImage() throws HeadlessException {

        initComponents();
        
        setResizable(false);
        setLocationRelativeTo(null);
        // Change Icon
        setIconImage(ImageLoader.loadImageJAR("icon.png"));
    }

    private void initComponents() {
        instance = this;

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("ECDL Play - Visor de Imagenes");
        
        imagenContainer = new ImageContainer();
        add(imagenContainer);

        pack();
        
    }
    
    
}
