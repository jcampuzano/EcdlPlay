/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

/**
 *
 * @author julio
 */
public class Respuesta {
    private int id;
    private String texto;
    private boolean correcta;

    public Respuesta() {
    }

    public Respuesta(int id, String texto, boolean correcta) {
        this.id = id;
        this.texto = texto;
        this.correcta = correcta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
    
    
}
