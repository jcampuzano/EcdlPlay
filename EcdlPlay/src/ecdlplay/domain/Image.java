/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import java.io.IOException;
import sun.misc.BASE64Decoder;

/**
 *
 * @author julio
 */
public class Image {

    private int id;
    private byte[] imagen;

    public Image() {
    }

    public Image(int id, byte[] imagen) {
        this.id = id;
        this.imagen = imagen;
    }

    public Image(int id, String imagen) {
        byte[] imagenAux = ConvertStringBase64ToByteArray(imagen);
        this.id = id;
        this.imagen = imagenAux;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    
    
    private byte[] ConvertStringBase64ToByteArray(String imagen) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            return decoder.decodeBuffer(imagen);
        } catch (IOException e) {
            return null;
        }
    }
}
