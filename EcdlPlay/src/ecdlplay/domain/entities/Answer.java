/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;

/**
 * Clase que contiene la información de una respuesta del juego
 * @author julio
 */
public class Answer {
	/**
	 * Identificador único de la respuesta
	 */
    private int id;
    /**
     * Texto de la respuesta
     */
    private String texto;
    /**
     * Indica si la respuesta es correcta
     */
    private boolean correcta;

    /**
     * Constructor por defecto de la clase
     */
    public Answer() {
    }

    /**
     * Constructor de la clase con inicialización de variables
     * @param id Identificador de la respuesta
     * @param texto Texto de la respuesta
     * @param correcta Respuesta correcta o incorrecta
     */
    public Answer(int id, String texto, boolean correcta) {
        this.id = id;
        this.texto = texto;
        this.correcta = correcta;
    }

    /**
     * Devuelve el identificador de la respuesta
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la respuesta
     * @param id Identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve el texto de la respuesta
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Establece el texto de la respuesta
     * @param texto Nuevo texto
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Devuelve si la respuesta es correcta o no
     */
    public boolean isCorrecta() {
        return correcta;
    }

    /**
     * Establece la corrección de la respuesta
     * @param correcta Nuevo valor
     */
    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
    
    
}
