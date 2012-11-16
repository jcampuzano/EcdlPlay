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

    public static final String texts[][] =
    {
        // Spanish
        {"¡CORRECTO!", "¡INCORRECTO!", "¡JUGADOR %p FRENA!"},
        // English
        {"RIGHT!", "FAIL!", "PLAYER %p BRAKES"},
    };

    public static String getText(int language, int key)
    {
        return texts[language][key];
    }

    public static String getText(int language, int key, String word)
    {
        return texts[language][key].replace("%p", word);
    }
}
