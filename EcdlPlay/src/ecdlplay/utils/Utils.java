/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.utils;

import java.util.Random;

/**
 * Clase con utilidades varias
 * @author julio
 */
public class Utils {

	/**
	 * Instancia singleton de la clase Random
	 */
    private static Random random;

    /**
     * Obtiene un valor aleatorio entre 0 y el valor pasado por parámetro  
     * @param max Valor máximo que se puede obtener 
     * @return
     */
    public static int randomInt(int max) {
        if (random == null) {
            random = new Random(System.currentTimeMillis());
        }
        return random.nextInt(max);
    }
        
}
