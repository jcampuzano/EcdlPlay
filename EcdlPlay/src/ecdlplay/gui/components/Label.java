/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecdlplay.gui.components;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Componente que representa un texto. En este caso, no se puede interactuar con el control
 * @author julio
 */
public class Label extends Component
{
    /**
     * Imagen que representa el control
     */
    private Image normal = null;

    /**
     * Constructor de la clase que inicializa los valores de la instancia
     * @param id Identificador de la etiqueta
     * @param x Coordenada X de la etiqueta
     * @param y Coordenada Y de la etiqueta
     * @param width Ancho de la etiqueta
     * @param height Alto de la etiqueta
     * @param normal Imagen de la etiqueta
     */
    public Label(int id, int x, int y, int width, int height, Image normal)
    {
        super(id, x, y, width, height);

        this.normal  = normal;
    }

    /**
     * Constructor de la clase que inicializa los valores de la instancia, obteniendo los
     * valores de ancho y alto de la imagen.
     * @param id Identificador de la etiqueta
     * @param x Coordenada X de la etiqueta
     * @param y Coordenada Y de la etiqueta
     * @param normal Imagen de la etiqueta
     */
    public Label(int id, int x, int y, Image normal)
    {
        this(id, x, y, normal.getWidth(null), normal.getHeight(null), normal);
    }

    /**
     * Pinta la imagen que representa a la etiqueta
     */
    @Override
    public void paint(Graphics g)
    {
        // Clip and Paint
        g.setClip(x, y, width, height);
        g.drawImage(normal, x, y, null);
    }

    /**
     * Cuando se hace click no se realiza ninguna modificación en el estado de la etiqueta
     */
    @Override
    public void click()
    {
        state = STATE_NORMAL;
    }
    /**
     * Cuando se presiona no se realiza ninguna modificación en el estado de la etiqueta
     */
    @Override
    public void press()
    {
        state = STATE_NORMAL;
    }

    /**
     * Cuando se libera no se realiza ninguna modificación en el estado de la etiqueta
     */
    @Override
    public void release()
    {
        state = STATE_NORMAL;
    }
    /**
     * Cuando se deja de hacer click no se realiza ninguna modificación en el estado de la etiqueta
     */
    @Override
    public void releaseClick()
    {
        state = STATE_NORMAL;
    }

    /**
     * Devuelve la imagen de la etiqueta
     * @return
     */
    public Image getNormal()
    {
        return normal;
    }

    /**
     * Establece la imagen de la etiqueta
     * @param normal
     */
    public void setNormal(Image normal)
    {
        this.normal = normal;
    }
}
