/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui.components;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Clase que representa un botón que puede ser pulsado por el usuario. Estará formado por dos
 * imágenes, que se mostrarán dependiendo del estado del componente. 
 * @author Julio
 */
public class Button extends Component
{
    // Images
	/**
	 * Imagen cuando el botón está en estado normal
	 */
    private Image normal = null;
    /**
     * Imagen cuando el botón está presionado
     */
    private Image pressed = null;

    /**
     * Constructor de la clase que inicializa los valores de la instancia
     * @param id Identificador del botón
     * @param x Coordenada X del botón
     * @param y Coordenada Y del botón
     * @param width Ancho del botón
     * @param height Alto del botón
     * @param normal Imagen del botón en estado normal
     * @param pressed Imágen del botón en estado presionado
     */
    public Button(int id, int x, int y, int width, int height, Image normal, Image pressed)
    {
        super(id, x, y, width, height);

        this.normal  = normal;
        this.pressed = pressed;
    }

    /**
     * Constructor de la clase que inicializa los valores de la instancia, obteniendo los
     * valores de ancho y alto de la imagen.
     * @param id Identificador del botón
     * @param x Coordenada X del botón
     * @param y Coordenada Y del botón
     * @param normal Imagen del botón en estado normal
     * @param pressed Imágen del botón en estado presionado
     */
    public Button(int id, int x, int y, Image normal, Image pressed)
    {
        this(id, x, y, normal.getWidth(null), normal.getHeight(null), normal, pressed);
    }

    /**
     * Pinta el botón, utilizando la imagen correspondiente según el estado del mismo
     */
    @Override
    public void paint(Graphics g)
    {
        // Get Image
        Image image = isPressed() ? pressed : normal;
        // Clip and Paint
        g.setClip(x, y, width, height);
        g.drawImage(image, x, y, null);
    }

    /**
     * Modifica el estado del botón a presionado cuando se hace click con el ratón
     */
    @Override
    public void click()
    {
        state = STATE_PRESSED;
    }

    /**
     * Modifica el estado del botón a presionado
     */
    @Override
    public void press()
    {
        state = STATE_PRESSED;
    }

    /**
     *  Modifica el estado del botón a normal
     */
    @Override
    public void release()
    {
        state = STATE_NORMAL;
    }

	/**
	 *  Modifica el estado del botón a presionado cuando deja de pulsarse el botón del ratón
	 */
    @Override
    public void releaseClick()
    {
        state = STATE_NORMAL;
    }

    /**
     * Devuelve el cursor que tendrá el mouse. En este caso, el icono apuntador
     */
    @Override
    public int getCursor()
    {
        return Cursor.HAND_CURSOR;
    }

    /**
     * Devuelve la imagen del botón en estado normal
     * @return
     */
    public Image getNormal()
    {
        return normal;
    }

    /**
     * Establece la imagen del botón en estado normal
     * @param normal
     */
    public void setNormal(Image normal)
    {
        this.normal = normal;
    }

    /**
     * Devuelve la imagen del botón en estado presionado
     * @return
     */
    public Image getPressed()
    {
        return pressed;
    }

    /**
     * Establece la imagen del botón en estado presionado
     * @param normal
     */
    public void setPressed(Image pressed)
    {
        this.pressed = pressed;
    }
}
