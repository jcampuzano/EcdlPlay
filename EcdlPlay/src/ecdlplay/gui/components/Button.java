/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui.components;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Clase que representa un bot�n que puede ser pulsado por el usuario. Estar� formado por dos
 * im�genes, que se mostrar�n dependiendo del estado del componente. 
 * @author Julio
 */
public class Button extends Component
{
    // Images
	/**
	 * Imagen cuando el bot�n est� en estado normal
	 */
    private Image normal = null;
    /**
     * Imagen cuando el bot�n est� presionado
     */
    private Image pressed = null;

    /**
     * Constructor de la clase que inicializa los valores de la instancia
     * @param id Identificador del bot�n
     * @param x Coordenada X del bot�n
     * @param y Coordenada Y del bot�n
     * @param width Ancho del bot�n
     * @param height Alto del bot�n
     * @param normal Imagen del bot�n en estado normal
     * @param pressed Im�gen del bot�n en estado presionado
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
     * @param id Identificador del bot�n
     * @param x Coordenada X del bot�n
     * @param y Coordenada Y del bot�n
     * @param normal Imagen del bot�n en estado normal
     * @param pressed Im�gen del bot�n en estado presionado
     */
    public Button(int id, int x, int y, Image normal, Image pressed)
    {
        this(id, x, y, normal.getWidth(null), normal.getHeight(null), normal, pressed);
    }

    /**
     * Pinta el bot�n, utilizando la imagen correspondiente seg�n el estado del mismo
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
     * Modifica el estado del bot�n a presionado cuando se hace click con el rat�n
     */
    @Override
    public void click()
    {
        state = STATE_PRESSED;
    }

    /**
     * Modifica el estado del bot�n a presionado
     */
    @Override
    public void press()
    {
        state = STATE_PRESSED;
    }

    /**
     *  Modifica el estado del bot�n a normal
     */
    @Override
    public void release()
    {
        state = STATE_NORMAL;
    }

	/**
	 *  Modifica el estado del bot�n a presionado cuando deja de pulsarse el bot�n del rat�n
	 */
    @Override
    public void releaseClick()
    {
        state = STATE_NORMAL;
    }

    /**
     * Devuelve el cursor que tendr� el mouse. En este caso, el icono apuntador
     */
    @Override
    public int getCursor()
    {
        return Cursor.HAND_CURSOR;
    }

    /**
     * Devuelve la imagen del bot�n en estado normal
     * @return
     */
    public Image getNormal()
    {
        return normal;
    }

    /**
     * Establece la imagen del bot�n en estado normal
     * @param normal
     */
    public void setNormal(Image normal)
    {
        this.normal = normal;
    }

    /**
     * Devuelve la imagen del bot�n en estado presionado
     * @return
     */
    public Image getPressed()
    {
        return pressed;
    }

    /**
     * Establece la imagen del bot�n en estado presionado
     * @param normal
     */
    public void setPressed(Image pressed)
    {
        this.pressed = pressed;
    }
}
