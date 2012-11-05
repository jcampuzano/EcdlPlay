/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import java.util.ArrayList;

/**
 *
 * @author julio
 */
public class Question {

    private int id;
    private String texto;
    private Image imagen;
    private Difficulty dificultad;
    private ArrayList<Answer> respuestas;

    public Question() {
        this.respuestas = new ArrayList<Answer>();
        this.imagen = new Image();
        this.dificultad = new Difficulty();
    }

    public Question(int id, String texto, Image imagen, Difficulty dificultad, ArrayList<Answer> respuestas) {
        this.id = id;
        this.texto = texto;
        this.imagen = imagen;
        this.dificultad = dificultad;
        this.respuestas = respuestas;
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

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public Difficulty getDificultad() {
        return dificultad;
    }

    public void setDificultad(Difficulty dificultad) {
        this.dificultad = dificultad;
    }

    public ArrayList<Answer> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<Answer> respuestas) {
        this.respuestas = respuestas;
    }
}
