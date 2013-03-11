/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.data;

/**
 *
 * @author Morpheo
 */
public class Texts
{
    public static final int TEXT_RIGHT  = 0;
    public static final int TEXT_FAIL   = 1;
    public static final int TEXT_BRAKES = 2;
    public static final int TEXT_WIN = 3;

    public static final String texts[] =
    {
        "¡CORRECTO!", "¡INCORRECTO!", "¡JUGADOR %p FRENA!", "¡JUGADOR %p GANA!"
    };

    public static String getText(int key)
    {
        return texts[key];
    }

    public static String getText(int key, String word)
    {
        return texts[key].replace("%p", word);
    }
}
