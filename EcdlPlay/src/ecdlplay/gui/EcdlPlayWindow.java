/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.GameEngine;
import ecdlplay.utils.ImageLoader;
import java.awt.BorderLayout;
import java.awt.HeadlessException;

/**
 * La ventana de la aplicación. Al ejecutarse en un ordenador necesitamos crear una 
 * ventana que contendrá todo el juego, EcdlPlayWindow se encarga de crearla.
 * @author julio
 */
public class EcdlPlayWindow extends javax.swing.JFrame {
	
	private static final long serialVersionUID = -8755181755584953138L;
	
	/**
	 * Ancho de la ventana
	 */
	public static final int WINDOW_WIDTH = 952;
	/**
	 * Alto de la ventana
	 */
    public static final int WINDOW_HEIGHT = 700;
    /**
     * Instancia singleton de EcdlPlayWindow
     */
    private static EcdlPlayWindow instance;

    /**
     * Devuelve el singleton de EcdlPlayWindow, de esta manera nunca tendremos más 
     * de una instancia en todo el ciclo de ejecución del programa.
     * @return
     */
    public static EcdlPlayWindow getInstance() {
        if (instance == null) {
            instance = new EcdlPlayWindow();
        }
        return instance;
    }

    /**
     * Constructor de la clase, Inicializa el tamaño de la ventana, el icono, añade el GameEngine 
     * como un componente más y finalmente muestra la ventana por pantalla.
     * @throws HeadlessException
     */
    public EcdlPlayWindow() throws HeadlessException {


        initComponents();

        // Set Properties
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        // Change Icon
        setIconImage(ImageLoader.loadImageJAR("icon.png"));

        // Create GameCanvas
        GameEngine ge = new GameEngine();
        add(ge, BorderLayout.CENTER);

        // Show
        setVisible(true);
    }

    /**
     * Encargado de cambiar el título de la ventana
     */
    private void initComponents() {
        instance = this;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ECDL Play");

        pack();
    }
}
