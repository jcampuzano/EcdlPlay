/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author julio
 */
public class Module extends Entity {

    private String name;
    private ArrayList<Question> questions;

    public Module(int id, String name, String description, ArrayList<Question> questions) {
        super(id, description);
        this.name = name;
        this.questions = questions;
    }

    public Module() {
        this.questions = new ArrayList<Question>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void shuffleQuestions() {
        Collections.shuffle(questions);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
    
    @Override
    public String toString(){
        return this.getName() + " - " + this.getDescription();
    }
}
