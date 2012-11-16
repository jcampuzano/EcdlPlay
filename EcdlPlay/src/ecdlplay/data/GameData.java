/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.data;

import ecdlplay.domain.Module;
import ecdlplay.domain.Question;
import java.util.ArrayList;

/**
 *
 * @author Morpheo
 */
public class GameData {

    private ArrayList<Module> modulos;

    public GameData() {
        this.modulos = new ArrayList<Module>();
    }

    public void addModulo(Module modulo) {
        modulos.add(modulo);
    }
    
    public void shuffleQuestions() {
        for (Module modulo : modulos) {
            modulo.shuffleQuestions();
        }
    }

    public ArrayList<Question> getQuestions(int difficult, int modulo) {
        // Check Input
        if (difficult < 0) {
            difficult = 1;
        } else if (difficult > 3) {
            difficult = 3;
        }

        // Init Result
        ArrayList<Question> qs = new ArrayList<Question>();

        // Iterate Questions
        Module m = null;
        for (Module maux : modulos) {
            if (maux.getId() == modulo) {
                m = maux;
                break;
            }
        }

        for (Question q : m.getPreguntas()) {
            if (q.getDificultad() == difficult) {
                qs.add(q);
            }
        }

        return qs;
    }
}
