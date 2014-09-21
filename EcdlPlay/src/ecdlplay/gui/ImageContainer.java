/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 * Panel contenedor de las imágenes asociadas a las preguntas del juego
 * @author julio
 */
public class ImageContainer extends JPanel{

	private static final long serialVersionUID = 7950839260650935798L;

	/**
	 * Instancia de la imágen que se va a mostrar
	 */
	private Image image;
    
	/**
	 * Establece la imagen a mostrar
	 * @param image Imagen a mostrar
	 */
    public void setImage(Image image){
        this.image = image;
    }
    
    /**
     * Pinta la imagen en el contenedor
     */
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, WIDTH, HEIGHT, this);
    }
}
