/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import java.util.Random;

/**
 * Clase encargada de realizar los lanzamientos con el dado
 * @author julio
 */
public class Dice {
	/**
	 * Instancia singleton del objeto Random
	 */
    private static Random random;
    
    /**
     * Lanza el dado y obtiene el resultado (un valor entre 1 y 6)
     * @return
     */
    public static int throwDice(){
        if (random == null){
            random = new Random();
        }
        
        return random.nextInt(5) + 1;
    }
}
