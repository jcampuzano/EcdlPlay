/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa un m�dulo del juego
 * @author julio
 */
public class Module extends Entity {

	/**
	 * Nombre corto del m�dulo
	 */
    private String name;
    /**
     * Lista de todas las preguntas asociadas al m�dulo
     */
    private ArrayList<Question> questions;

    /**
     * Constructor de la clase con inicializaci�n de lasa propiedades
     * @param id Identificador del m�dulo
     * @param name Nombre corto del m�dulo
     * @param description Descripci�n del m�dulo
     * @param questions Lista de preguntas del m�dulo
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
	 * Obtiene el nombre corto del m�dulo
	 * @return
	 */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre corto del m�dulo
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la lista de preguntas del m�dulo
     * @return
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Establece la lista de preguntas del m�dulo
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
     * A�ade una nueva pregunta a la lista
     * @param question
     */
    public void addQuestion(Question question) {
        this.questions.add(question);
    }
    
    /**
     * Devuelve una cadena de caracteres concatenando el nombre y la descripc�n
     */
    @Override
    public String toString(){
        return this.getName() + " - " + this.getDescription();
    }
}
