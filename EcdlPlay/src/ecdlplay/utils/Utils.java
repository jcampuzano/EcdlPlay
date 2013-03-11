/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.utils;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Morpheo
 */
public class Utils {

    private static Random random;

    public static int randomInt(int max) {
//        if (random == null) {
//            random = new Random(System.currentTimeMillis());
//        }
//        return random.nextInt(max);
        return max - 1;
    }
        
}
