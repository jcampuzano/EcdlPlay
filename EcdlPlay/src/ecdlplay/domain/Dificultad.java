/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

/**
 *
 * @author julio
 */
public class Dificultad {
    private int id;
    private String nombre;
    private int valor;

    public Dificultad() {
    }

    public Dificultad(int id, String nombre, int valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
