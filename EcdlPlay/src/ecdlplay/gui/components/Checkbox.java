/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui.components;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Componente que representa un control que puede estar seleccionado o no. Su comportamiento es
 * como el del componente Button, pero no vuelve a su estado normal al dejar de presionarse si no
 * que su estado va alternando cuando se pulsa.
 * @author julio
 */
public class Checkbox extends Component
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
     * @param id Identificador del checkbox
     * @param x Coordenada X del checkbox
     * @param y Coordenada Y del checkbox
     * @param width Ancho del checkbox
     * @param height Alto del checkbox
     * @param normal Imagen del checkbox en estado normal
     * @param pressed Imágen del checkbox en estado presionado
     */
    public Checkbox(int id, int x, int y, int width, int height, Image normal, Image pressed)
    {
        super(id, x, y, width, height);

        this.normal  = normal;
        this.pressed = pressed;
    }

    /**
     * Constructor de la clase que inicializa los valores de la instancia, obteniendo los
     * valores de ancho y alto de la imagen.
     * @param id Identificador del checkbox
     * @param x Coordenada X del checkbox
     * @param y Coordenada Y del checkbox
     * @param normal Imagen del checkbox en estado normal
     * @param pressed Imágen del checkbox en estado presionado
     */
    public Checkbox(int id, int x, int y, Image normal, Image pressed)
    {
        this(id, x, y, normal.getWidth(null), normal.getHeight(null), normal, pressed);
    }

    /**
     * Pinta el checkbox, utilizando la imagen correspondiente según el estado del mismo
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
     * Cuando se hace click, el estado no se modifica
     */
    @Override
    public void click()
    {
        //state = isPressed() ? STATE_NORMAL : STATE_PRESSED;
    }

    /**
     * Se modifica el estado a presionado
     */
    @Override
    public void press()
    {
        state = STATE_PRESSED;
    }

    /**
     * Se modifica el estado a normal
     */
    @Override
    public void release()
    {
        state = STATE_NORMAL;
    }

    /**
     * El estado no se modifica cuando deja de hacerse click con el ratón
     */
    @Override
    public void releaseClick()
    {
        // Nothing
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
