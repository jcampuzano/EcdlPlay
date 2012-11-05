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
public class Module {
    private int id;
    private String nombre;    
    private ArrayList<Question> preguntas;

    
    public Module(int id, String nombre, ArrayList<Question> preguntas) {
        this.id = id;
        this.nombre = nombre;
        this.preguntas = preguntas;
    }
        
    public Module() {
        this.preguntas = new ArrayList<Question>();
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

    public ArrayList<Question> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Question> preguntas) {
        this.preguntas = preguntas;
    }
    
    
}
