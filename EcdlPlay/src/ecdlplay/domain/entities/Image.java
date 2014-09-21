/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;

import javax.xml.bind.DatatypeConverter;

/**
 * Representa una imagen que se asociará con una pregunta
 * @author julio
 */
public class Image {

	/**
	 * Array de bytes que representan a la imagen
	 */
    private byte[] imagen;

    /**
     * Constructor por defecto de la clase
     */
    public Image() {
    }

    /**
     * Constructor de la clase con inicialización de las propiedades
     * @param imagen Array de bytes de la imagen
     */
    public Image(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * Constructor de la clase que obtiene el array de bytes a partir 
     * de un texto en Base64
     * @param imagen Texto en Base64 que representa la imagen
     */
    public Image(String imagen) {
        if (imagen != null && !imagen.equals("")) {
            byte[] imagenAux = ConvertStringBase64ToByteArray(imagen);
            this.imagen = imagenAux;
        }
    }

    /**
     * Obtiene el valor actual del array de bytes
     * @return
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Establece un nuevo valor para el array de bytes
     * @param imagen
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * Convierte un texto en Base64 a array de bytes
     * @param imagen Texto en Base64
     * @return
     */
    private byte[] ConvertStringBase64ToByteArray(String imagen) {
        return DatatypeConverter.parseBase64Binary(imagen);
    }
}
