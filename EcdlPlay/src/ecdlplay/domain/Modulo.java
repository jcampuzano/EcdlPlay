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
public class Modulo {
    private int id;
    private String nombre;    
    private ArrayList<Pregunta> preguntas;

    
    public Modulo(int id, String nombre, ArrayList<Pregunta> preguntas) {
        this.id = id;
        this.nombre = nombre;
        this.preguntas = preguntas;
    }
        
    public Modulo() {
        this.preguntas = new ArrayList<Pregunta>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
    
    
}
