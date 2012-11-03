/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

/**
 *
 * @author julio
 */
public class EcdlPlayWindow extends javax.swing.JFrame {

    public static final int WINDOW_WIDTH  = 952;
    public static final int WINDOW_HEIGHT = 700;
    
    private static EcdlPlayWindow instance;
    public static EcdlPlayWindow getInstance()
    {
        if (instance == null)
        {
            instance = new EcdlPlayWindow();
        }
        return instance;
    }
    
    public EcdlPlayWindow() throws HeadlessException {
        
        initComponents();

        // Set Properties
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        // Change Icon
        //setIconImage(ImageLoader.loadImageJAR("icon.png"));

        // Create GameCanvas
        GameViewCanvas gc = new GameViewCanvas();
        add(gc, BorderLayout.CENTER);
        
        // Show
        setVisible(true);
    }
    
    
    
    private void initComponents()
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ECDL Play");

        pack();
    }
    
    
}
