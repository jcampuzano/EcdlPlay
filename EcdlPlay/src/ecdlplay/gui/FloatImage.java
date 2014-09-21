/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.utils.ImageLoader;
import java.awt.HeadlessException;
import java.awt.Image;
import javax.swing.JFrame;

/**
 * Ventana flotante que servirá para visualizar las imágenes asociadas a preguntas
 * @author julio
 */
public class FloatImage extends JFrame {

    
	private static final long serialVersionUID = 3081570750510537178L;
	
	/**
	 * Contenedor de la imagen a mostrar
	 */
	private ImageContainer imagenContainer;
	/**
	 * Instancia singleton de la ventana
	 */
    private static FloatImage instance;
    
    /**
     * Obtiene o inicializa la instancia singleton
     * @return
     */
    public static FloatImage getInstance() {
        if (instance == null) {
            instance = new FloatImage();
        }
        return instance;
    }
   
    /**
     * Establece la imagena a mostrar
     * @param image
     */
    public void setImage(Image image) {
        this.imagenContainer.setImage(image);
    }
    
    /**
     * Constructor de la clase. Inicializa la ventana
     * @throws HeadlessException
     */
    public FloatImage() throws HeadlessException {

        initComponents();
        
        setResizable(false);
        setLocationRelativeTo(null);
        // Change Icon
        setIconImage(ImageLoader.loadImageJAR("icon.png"));
    }

    /**
     * Encargada de cambiar el título de la ventana
     */
    private void initComponents() {
        instance = this;

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("ECDL Play - Visor de Imagenes");
        
        imagenContainer = new ImageContainer();
        add(imagenContainer);

        pack();
        
    }
    
    
}
