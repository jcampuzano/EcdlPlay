/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

/**
 *
 * @author julio
 */
public class Square {

    private Pregunta questions[];
    private int posQuestion;
    private int numSquare;

    public Square(int numSquare) {
        this.numSquare = numSquare;
        this.posQuestion = 0;
    }

    public Pregunta getQuestion() {
        return questions[posQuestion];
    }

    public void setQuestions(Pregunta questions[]) {
        this.questions = questions;
    }

    public void nextQuestion() {
        posQuestion = (posQuestion + 1) % questions.length;
    }

    public int getNumSquare() {
        return numSquare;
    }
}
