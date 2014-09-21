/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;

/**
 * Representa cada una de las casillas del tablero
 * @author julio
 */
public class Square {

	/**
	 * Lista de preguntas asociadas a la casilla
	 */
    private Question questions[];
    /**
     * Posición dentro del array de preguntas de la actual
     */
    private int posQuestion;
    /**
     * Número que ocupa la casilla dentro del tablero
     */
    private int numSquare;

    /**
     * Constructor de la clase con inicialización de variables
     * @param numSquare
     */
    public Square(int numSquare) {
        this.numSquare = numSquare;
        this.posQuestion = 0;
    }

    /**
     * Obtiene la pregunta actual de la casilla
     * @return
     */
    public Question getQuestion() {
        return questions[posQuestion];
    }

    /**
     * Establece el array de preguntas que se asociarán con la casilla
     * @param questions
     */
    public void setQuestions(Question questions[]) {
        this.questions = questions;
    }

    /**
     * Actualiza la posición de la siguiente pregunta
     */
    public void nextQuestion() {
        posQuestion = (posQuestion + 1) % questions.length;
    }

    /**
     * Obtiene el número que ocupa la casilla dentro del tablero
     * @return
     */
    public int getNumSquare() {
        return numSquare;
    }
}
