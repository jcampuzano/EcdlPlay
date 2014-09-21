/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.data;

/**
 * Esta clase contiene los textos din�micos que se muestran en pantalla
 *
 * @author julio
 */
public class Texts
{
	/**
	 * �ndice del texto de respuesta correcta
	 */
    public static final int TEXT_RIGHT  = 0;
    /**
	 * �ndice del texto de respuesta incorrecta
	 */
    public static final int TEXT_FAIL   = 1;
    /**
	 * �ndice del texto para indicar que el jugador frena
	 */
    public static final int TEXT_BRAKES = 2;
    /**
	 * �ndice del texto para indicar que el jugador ha ganado
	 */
    public static final int TEXT_WIN = 3;

    /**
     * Array de textos din�micos
     */
    public static final String texts[] =
    {
        "¡CORRECTO!", "¡INCORRECTO!", "¡JUGADOR %p FRENA!", "¡JUGADOR %p GANA!"
    };

    /**
     * Obtiene el texto correspondiente con la clave pasada por par�metro
     * @param key Clave del texto a buscar
     * @return
     */
    public static String getText(int key)
    {
        return texts[key];
    }

    /**
     * Obtiene el texto correspondiente con la clave pasada por par�metro y sustituye el par�metro con el argumento word
     * @param key Clave del texto a buscar
     * @param word Valor del par�metro
     * @return
     */
    public static String getText(int key, String word)
    {
        return texts[key].replace("%p", word);
    }
}
