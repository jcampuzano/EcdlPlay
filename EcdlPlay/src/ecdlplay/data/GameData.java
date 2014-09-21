/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.data;

import ecdlplay.domain.entities.Module;
import ecdlplay.domain.entities.Question;

import java.util.ArrayList;

/**
 * Clase encargada de servir como repositorio de datos para la aplicaci�n
 * @author julio
 */
public class GameData {

	/**
	 * Lista de m�dulos disponibles
	 */
    private ArrayList<Module> modulos;

    /**
     * Constructor de la clase. Inicializa el array de m�dulos
     */
    public GameData() {
        this.modulos = new ArrayList<Module>();
    }

    /**
     * A�ade un nuevo m�dulo
     * @param modulo N�evo m�dulo a a�adir
     */
    public void addModulo(Module modulo) {
        modulos.add(modulo);
    }

    /**
     * Para cada m�dulo, desordena las preguntas
     */
    public void shuffleQuestions() {
        for (Module modulo : modulos) {
            modulo.shuffleQuestions();
        }
    }

    /**
     * Obtiene todas las preguntas que corresponden con la dificultad y el m�dulo indicados
     * @param difficult Dificultad de las preguntas
     * @param modulo Modulo de las preguntas
     * @return
     */
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

        if (m != null) {
            for (Question q : m.getQuestions()) {
                if (q.getDificultad() == difficult) {
                    qs.add(q);
                }
            }
        }
        return qs;

    }

    /**
     * Obtiene todos los m�dulos
     * @return
     */
    public ArrayList<Module> getModules() {
        return this.modulos;
    }
}
