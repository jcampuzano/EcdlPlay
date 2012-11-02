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
public class Pregunta {

    private int id;
    private String texto;
    private Imagen imagen;
    private Dificultad dificultad;
    private ArrayList<Respuesta> respuestas;

    public Pregunta() {
        this.respuestas = new ArrayList<Respuesta>();
        this.imagen = new Imagen();
        this.dificultad = new Dificultad();
    }

    public Pregunta(int id, String texto, Imagen imagen, Dificultad dificultad, ArrayList<Respuesta> respuestas) {
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

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(ArrayList<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
