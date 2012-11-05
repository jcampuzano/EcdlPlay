/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import java.util.Random;

/**
 *
 * @author julio
 */
public class Dice {
    private static Random random;
    
    public static int throwDice(){
        if (random == null){
            random = new Random();
        }
        
        return random.nextInt(5) + 1;
    }
}
