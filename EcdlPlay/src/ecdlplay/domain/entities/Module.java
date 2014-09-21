/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa un módulo del juego
 * @author julio
 */
public class Module extends Entity {

	/**
	 * Nombre corto del módulo
	 */
    private String name;
    /**
     * Lista de todas las preguntas asociadas al módulo
     */
    private ArrayList<Question> questions;

    /**
     * Constructor de la clase con inicialización de lasa propiedades
     * @param id Identificador del módulo
     * @param name Nombre corto del módulo
     * @param description Descripción del módulo
     * @param questions Lista de preguntas del módulo
     */
    public Module(int id, String name, String description, ArrayList<Question> questions) {
        super(id, description);
        this.name = name;
        this.questions = questions;
    }

    /**
     * Constructor por defecto de la clase. Inicializa el array de preguntas.
     */
    public Module() {
        this.questions = new ArrayList<Question>();
    }

	/**
	 * Obtiene el nombre corto del módulo
	 * @return
	 */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre corto del módulo
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la lista de preguntas del módulo
     * @return
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Establece la lista de preguntas del módulo
     * @param questions
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * Mezcla o desordena las preguntas
     */
    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    /**
     * Añade una nueva pregunta a la lista
     * @param question
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }
    
    /**
     * Devuelve una cadena de caracteres concatenando el nombre y la descripcón
     */
    @Override
    public String toString(){
        return this.getName() + " - " + this.getDescription();
    }
}
