/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.data;

/**
 * Esta clase contiene los textos dinámicos que se muestran en pantalla
 *
 * @author julio
 */
public class Texts
{
	/**
	 * Índice del texto de respuesta correcta
	 */
    public static final int TEXT_RIGHT  = 0;
    /**
	 * Índice del texto de respuesta incorrecta
	 */
    public static final int TEXT_FAIL   = 1;
    /**
	 * Índice del texto para indicar que el jugador frena
	 */
    public static final int TEXT_BRAKES = 2;
    /**
	 * Índice del texto para indicar que el jugador ha ganado
	 */
    public static final int TEXT_WIN = 3;

    /**
     * Array de textos dinámicos
     */
    public static final String texts[] =
    {
        "Â¡CORRECTO!", "Â¡INCORRECTO!", "Â¡JUGADOR %p FRENA!", "Â¡JUGADOR %p GANA!"
    };

    /**
     * Obtiene el texto correspondiente con la clave pasada por parámetro
     * @param key Clave del texto a buscar
     * @return
     */
    public static String getText(int key)
    {
        return texts[key];
    }

    /**
     * Obtiene el texto correspondiente con la clave pasada por parámetro y sustituye el parámetro con el argumento word
     * @param key Clave del texto a buscar
     * @param word Valor del parámetro
     * @return
     */
    public static String getText(int key, String word)
    {
        return texts[key].replace("%p", word);
    }
}
