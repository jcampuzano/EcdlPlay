/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa a una pregunta del juego
 * @author julio
 */
public class Question {

	/**
	 * Identificador de la pregunta
	 */
    private int id;
    /**
     * Texto de la pregunta
     */
    private String texto;
    /**
     * Imagen asociada a la pregunta
     */
    private Image imagen;
    /**
     * Dificultad de la pregunta
     */
    private int dificultad;
    /**
     * Lista de posibles respuestas
     */
    private ArrayList<Answer> respuestas;

    /**
     * Constructor por defecto de la clase. Inicializa los objetos internos
     */
    public Question() {
        this.respuestas = new ArrayList<Answer>();
        this.imagen = new Image();
    }

    /**
     * Constructor de la clase con inicialización de las variables
     * @param id Identificador de la pregunta
     * @param texto Texto de la pregunta
     * @param imagen Imagen asociada a la pregutna (puede ser null)
     * @param dificultad Dificultad de la pregunta
     * @param respuestas Array de respuestas asociadas a la pregunta
     */
    public Question(int id, String texto, Image imagen, int dificultad, ArrayList<Answer> respuestas) {
        this.id = id;
        this.texto = texto;
        this.imagen = imagen;
        this.dificultad = dificultad;
        this.respuestas = respuestas;
    }

    /**
     * Obtiene el identificador de la pregunta
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador de la pregunta
     * @param id Nuevo identificador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el texto de la pregunta
     * @return
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Establece el texto de la pregunta
     * @param texto Nuevo texto
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Obtiene la imagen asociada a la pregunta
     * @return
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Establece la imagen asociada a la pregunta
     * @param imagen Nueva imagen
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la dificultad de la pregunta
     * @return
     */
    public int getDificultad() {
        return dificultad;
    }

    /**
     * Establece la dificultad de la pregunta
     * @param dificultad
     */
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    /**
     * Obtiene el array de respuestas asociadas a la pregunta
     * @return
     */
    public ArrayList<Answer> getRespuestas() {
        return respuestas;
    }

    /**
     * Establece el array de respuestas asociadas a la pregunta
     * @param respuestas Nuevo array de pregutnas
     */
    public void setRespuestas(ArrayList<Answer> respuestas) {
        this.respuestas = respuestas;
    }
    
    /**
     * Desordena el array de respuestas asociadas a la pregunta
     */
    public void shuflleAnswers()
    {
        Collections.shuffle(respuestas);
    }
    
    /**
     * Añade una nueva respuesta a la pregunta
     * @param answer Nueva respuesta a añadir
     */
    public void addAnswer(Answer answer){
        this.respuestas.add(answer);
    }

    /**
     * Obtiene la respuesta que corresponde al parámetro
     * @param numAnswer Número de pregunta a buscar
     * @return
     */
    public Answer getRespuesta(int numAnswer) {
        return this.respuestas.get(numAnswer);
    }
}
