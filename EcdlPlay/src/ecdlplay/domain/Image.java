/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author julio
 */
public class Image {

    private byte[] imagen;

    public Image() {
    }

    public Image(byte[] imagen) {
        this.imagen = imagen;
    }

    public Image(String imagen) {
        if (imagen != null && !imagen.equals("")) {
            byte[] imagenAux = ConvertStringBase64ToByteArray(imagen);
            this.imagen = imagenAux;
        }
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    private byte[] ConvertStringBase64ToByteArray(String imagen) {
        return DatatypeConverter.parseBase64Binary(imagen);
    }
}
