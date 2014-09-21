/*
 * TFont.java
 *
 * Created on 18 de julio de 2006, 8:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package ecdlplay.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import ecdlplay.gui.canvas.GameCanvasConstants;

/**
 * Clase encargada de pintar texto. Es una clase muy compleja ya que se encarga de pintar
 * un texto con una fuente determinada en cualquier recuadro indicado en el menor tiempo 
 * posible, calculando cuántas palabras caben por letra, separación entre líneas, si hay 
 * textos en cursiva, etc.
 * 
 * @author julio
 */
public class TFont {

	/**
	 * Centrado horizontal
	 */
    public static final int HCENTER = 1;
    /**
     * Centrado vertical
     */
    public static final int VCENTER = 2;
    /**
     * Alineación izquierda
     */
    public static final int LEFT = 4;
    /**
     * Alineación derecha
     */
    public static final int RIGHT = 8;
    /**
     * Alineación superior
     */
    public static final int TOP = 16;
    /**
     * Alineación inferior
     */
    public static final int BOTTOM = 32;
    
    /**
     * Alineación basada en la línea
     */
    public static final int BASELINE = 64;
    /**
     * Alineación justificada
     */
    public static final int JUSTIFY = 128;
    /**
     * Fuente normal
     */
    public static final int TYPE_NORMAL = 0;
    /**
     * Fuente cursiva
     */
    public static final int TYPE_ITALIC = 1;
    /**
     * Fuente superíndice
     */
    public static final int TYPE_SUPERSCRIPT = 2;
    /**
     * Fuente subindice
     */
    public static final int TYPE_SUBSCRIPT = 3;
    /**
     * Array de fuentes a utilizar
     */
    private Font fonts[];
    /**
     * Array de métricas de las fuentes
     */
    private FontMetrics metrics[];
    /**
     * Color del texto
     */
    private int fontColor;
    /**
     * Tamaño del texto
     */
    private int fontSize;
    /**
     * Espacio entre línea
     */
    private int lineSpacing;
    /**
     * Tamaño del carácter espacio
     */
    private int spaceSize;

    /**
     * Crea una nueva instancia de la clase, inicializando sus valores
     * @param filename Ruta relativa del fichero de fuente
     * @param size Tamaño de la letra
     * @param style Estilo de letra
     * @param fontColor Color de la fuente
     */
    public TFont(String filename, int size, int style, int fontColor) {
        try {
            // Create Base Font
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(GameCanvasConstants.RES_PATH + filename));
            // Create array for derive fonts
            fonts = new Font[4];

            // Derive Fonts
            fonts[TYPE_NORMAL] = font.deriveFont(style, size);
            fonts[TYPE_ITALIC] = font.deriveFont(Font.ITALIC, size);
            fonts[TYPE_SUPERSCRIPT] = font.deriveFont(style, (size * 2) / 3);
            fonts[TYPE_SUBSCRIPT] = font.deriveFont(style, (size * 2) / 3);

            this.fontColor = fontColor;
            this.fontSize = size;
            this.lineSpacing = 0;
        } catch (Exception e) {
            System.out.println("Imposible cargar la fuente");
        }
    }

    /**
     * Obtiene el tamaño de la fuente
     * @return
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * Establece el color de la fuente
     * @param fontColor
     */
    public void setColor(int fontColor) {
        this.fontColor = fontColor;
    }

    /**
     * Obtiene la métrica para un tipo de letra concreto
     * @param type
     * @return
     */
    public FontMetrics getMetrics(int type) {
        return metrics[type];
    }

    /**
     * Carga las metricas de cada una de las fuentes cargadas
     * @param g
     */
    private void loadMetrics(Graphics g) {
        if (metrics == null) {
            // Initialize Array
            metrics = new FontMetrics[fonts.length];
            // Load Metrics
            for (int i = 0; i < metrics.length; i++) {
                metrics[i] = g.getFontMetrics(fonts[i]);
            }

            // Calculate Space Size
            spaceSize = metrics[0].stringWidth(" ");
        }
    }

    /**
     * Calcula la posición y el ancho de las líneas en que se dividirá
     * el texto
     * @param text Texto a escribir
     * @param width Ancho del espacio donde escribir el texto
     * @return
     */
    public short[][] getLineData(String text, int width) {
        ArrayList<short[]> lines = new ArrayList<short[]>();

        int index = 0;
        int lineStart = 0;// Data for line storage
        int lineEnd = 0;
        int lineWidth = 0;
        boolean noData = true;
        int nextWordStart = 0;
        int processWidth = 0;
        char lastCharType = '0'; // Indicates what was the last character (0 =
        // Nothing, S = Space, C = Character, N =
        // New line)
        char actualCharacter;
        short lineData[];

        while (index < text.length()) {
            actualCharacter = text.charAt(index);
            switch (actualCharacter) {
                case ' ':
                    if (lastCharType == 'C') {
                        lineEnd = index - 1;
                        lineWidth = processWidth; // We skip the last character
                        // spacing
                    }

                    processWidth += (getSpaceSize());
                    lastCharType = 'S';
                    break;
                case '\n':
                    lineEnd = index - 1;
                    lineWidth = processWidth; // We skip the last character spacing
                    lastCharType = 'N';
                    break;
                default:
                    if (noData) {
                        lineStart = index;
                        noData = false;
                    }
                    if (lastCharType == 'S') {
                        nextWordStart = index;
                    } else if (lastCharType == 'N') {
                        nextWordStart = index;
                    }
                    processWidth += (getCharacterWidth(TYPE_NORMAL, actualCharacter));
                    lastCharType = 'C';
                    break;
            }

            if (index == text.length() - 1) {
                lineEnd = index;
                lineWidth = processWidth;
                lastCharType = 'N';
            }

            if (processWidth >= width || lastCharType == 'N') {
                // Create LineData Array
                lineData = new short[3];
                if (!noData) {
                    lineData[0] = (short) lineStart;
                    lineData[1] = (short) (lineEnd + 1);
                    lineData[2] = (short) metrics[TYPE_NORMAL].stringWidth(text
                            .substring(lineStart, lineEnd + 1));
                }
                // Save LineData
                lines.add(lineData);
                // Check
                if (nextWordStart <= lineEnd) {
                    noData = true;
                } else {
                    lineStart = nextWordStart;
                    // Skip caracter spaces
                    processWidth -= ((nextWordStart - lineEnd) * getSpaceSize() - 2);
                }
                // Remove Line Width
                processWidth -= lineWidth;
            }

            // Next Character
            index++;
        }

        // Step #5: Convert Vector to short array
        short result[][] = new short[lines.size()][];
        Iterator<short[]> e = lines.iterator();
        for (int i = 0; i < result.length; i++) {
            result[i] = (short[]) e.next();
        }
        return result;
    }

    /**
     * Obtiene el ancho de un carácter dado según el tipo de fuente
     * @param fontType
     * @param character
     * @return
     */
    private int getCharacterWidth(int fontType, char character) {
        return metrics[fontType].stringWidth(String.valueOf(character));
    }

    /**
     * Obtiene el ancho de un texto según el tipo de fuente dado
     * @param fontType
     * @param text
     * @return
     */
    public int getLineWidth(int fontType, String text) {
        return metrics[fontType].stringWidth(text);
    }

    /**
     * Pinta un texto por pantalla dadas unas coordenadas y un tipo de justificación en un color determinado
     * @param g Objeto gráfico para pintar
     * @param text Texto a pintar
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param color Color del texto
     * @param anchor Tipo de justificación
     * @return
     */
    public int drawString(Graphics g, String text, int x, int y, int color, int anchor) {
        // First Load FontMetrics
        loadMetrics(g);

        // Check anchors
        if ((anchor & TFont.TOP) != 0) {
            y += metrics[TYPE_NORMAL].getAscent();
        }
        if ((anchor & TFont.HCENTER) != 0) {
            x -= (metrics[TYPE_NORMAL].stringWidth(text) >> 1);
        }

        // Set Font
        g.setFont(fonts[TYPE_NORMAL]);
        // Set Color
        g.setColor(new Color(color));
        // Draw String
        g.drawString(text, x, y);

        // Return size of the text
        return metrics[TYPE_NORMAL].stringWidth(text);
    }

    /**
     * Pinta un texto por pantalla dadas unas coordenadas y un tipo de justificación en un color determinado, 
     * bordeándolo con una línea del color pasado por parámetro
     * @param g Objeto gráfico para pintar
     * @param text Texto a pintar
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param color Color del texto
     * @param colorBorder Color del borde
     * @param anchor Tipo de justificación
     * @return
     */
    public int drawStringBorder(Graphics g, String text, int x, int y, int color, int colorBorder, int anchor) {
        drawString(g, text, x - 1, y, colorBorder, anchor);
        drawString(g, text, x + 1, y, colorBorder, anchor);
        drawString(g, text, x, y - 1, colorBorder, anchor);
        drawString(g, text, x, y + 1, colorBorder, anchor);

        return drawString(g, text, x, y, color, anchor);
    }

    /**
     * Pinta un texto por pantalla dadas unas coordenadas y un tipo de justificación en el color predeterminado
     * @param g Objeto gráfico para pintar
     * @param text Texto a pintar
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param anchor Tipo de justificación
     * @return
     */
    public int drawString(Graphics g, String text, int x, int y, int anchor) {
        return drawString(g, text, x, y, fontColor, anchor);
    }

    /**
     * Pinta un texto por pantalla dads unas coordenads, un tipo de justificación y un ancho y alto máximos para el texto
     * @param g Objeto gráfico para pintar
     * @param data Texto a pintar
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param width Ancho máximo del texto
     * @param height Alto máximo del texto
     * @param anchor Tipo de justificación
     * @return
     */
    public short[][] drawString(Graphics g, String data, int x, int y, int width, int height, int anchor) {
        return drawString(g, data, x, y, width, height, fontColor, anchor);
    }

    /**
     * Pinta un texto por pantalla dads unas coordenads, un tipo de justificación, un color y un ancho 
     * y alto máximos para el texto
     * @param g Objeto gráfico para pintar
     * @param data Texto a pintar
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param width Ancho máximo del texto
     * @param height Alto máximo del texto
     * @param color Color del texto
     * @param anchor Tipo de justificación
     * @return
     */
    public short[][] drawString(Graphics g, String data, int x, int y, int width, int height, int color, int anchor) {
        // First Load FontMetrics
        loadMetrics(g);

        // Calculate Lines
        short mLineData[][] = getLineData(data, width);
        // Draw String
        drawFastString(g, data, x, y, width, height, color, anchor, mLineData);
        // Return Data
        return mLineData;
    }

    /**
     * Pinta un texto por pantalla dads unas coordenads, un tipo de justificación, un color y un ancho 
     * y alto máximos para el texto, utilizando para ello información de las líneas calculadas previamente
     * @param g Objeto gráfico para pintar
     * @param data Texto a pintar
     * @param x Coordenada X
     * @param y Coordenada Y
     * @param width Ancho máximo del texto
     * @param height Alto máximo del texto
     * @param color Color del texto
     * @param anchor Tipo de justificación
     * @param mLineData Información sobre dónde se escribirán las líneas
     * @return
     */
    private short[][] drawFastString(Graphics g, String data, int x, int y, int width, int height, int color, int anchor, short[][] mLineData) {
        int maxLines = height / (getFontHeight() + getLineSpacing());
        if (maxLines < 1) {
            maxLines = 1;
        }

        if (mLineData.length < maxLines) {
            maxLines = mLineData.length;
        }

        int textHeight = maxLines == 1 ? maxLines * (getFontHeight()) : maxLines * (getLineSpacing() + getFontHeight());

        if ((anchor & TFont.TOP) != 0) {
            y += metrics[TYPE_NORMAL].getAscent();
        } else if ((anchor & TFont.BOTTOM) != 0) {
            y += metrics[TYPE_NORMAL].getAscent() + height - textHeight;
        } else if ((anchor & TFont.VCENTER) != 0) {
            y += metrics[TYPE_NORMAL].getAscent() + ((height - textHeight) >> 1);
        }

        if ((anchor & TFont.RIGHT) != 0) {
            x = x + width;
        } else if ((anchor & TFont.HCENTER) != 0) {
            x = x + width / 2;
        }

        for (int i = 0; i < maxLines; i++) {
            if (mLineData[i][2] != 0) {
                if ((anchor & JUSTIFY) != 0) {
                    drawFastString(g, data, x, y, width, color, anchor, mLineData[i], i == maxLines - 1);
                } else {
                    drawFastString(g, data, x, y, color, anchor, mLineData[i]);
                }
            }

            y = y + getLineSpacing() + getFontHeight();
        }

        return mLineData;
    }

    
    /**
     * Escribe una línea del texto justificado
     */
    private void drawFastString(Graphics g, String text, int x, int y, int width, int color, int anchor, short lineData[], boolean lastLine) {
        String textNoSpecialChars;
        int incy;
        float gap;
        float rx;

        // Crop Text
        text = text.substring(lineData[0], lineData[1]);

        // Get Slices
        SliceString[] slices = getStringSlices(text);
        // Get String without Special Chars
        textNoSpecialChars = text.replaceAll("\\$|\\@|\\#", "");

        // Check Anchors
        if ((anchor & TFont.RIGHT) != 0) {
            x -= getLineWidth(TYPE_NORMAL, textNoSpecialChars);
        } else if ((anchor & TFont.HCENTER) != 0) {
            x -= (getLineWidth(TYPE_NORMAL, textNoSpecialChars) >> 1);
        }

        // Iterate Slices to calculate real width
        int realWidth = 0;
        for (SliceString slice : slices) {
            realWidth += getLineWidth(slice.typeFont, slice.text);
        }
        // Calculate space remaining
        int remainWidth = width - realWidth;
        // Check Result
        if (remainWidth < 0) {
            remainWidth = 0;
        }

        // Calculate Num. Words
        int numSpaces = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                numSpaces++;
            }
        }

        // Gap to add each space
        if (numSpaces == 0 || lastLine) {
            gap = 0;
        } else {
            gap = remainWidth / numSpaces;
        }

        // Initialize Real X
        rx = x;

        // Iterate Slices to Paint
        for (SliceString slice : slices) {
            // Get Words
            String[] words = slice.text.split(" ");

            // Calculate incy
            switch (slice.typeFont) {
                case TYPE_SUBSCRIPT:
                    incy = metrics[TYPE_NORMAL].getDescent() >> 1;
                    break;
                case TYPE_SUPERSCRIPT:
                    incy = -(metrics[TYPE_NORMAL].getDescent() + metrics[TYPE_SUPERSCRIPT].getDescent());
                    break;
                default:
                    incy = 0;
            }

            // Iterate Words
            for (String w : words) {
                // Set Font, color and paint
                g.setFont(fonts[slice.typeFont]);
                g.setColor(new Color(color));
                g.drawString(w, (int) rx, y + incy);

                // Move Coord
                rx += getLineWidth(slice.typeFont, w + " ") + gap;
            }
        }
    }

    /**
     * Escribe una línea del texto
     */
    private void drawFastString(Graphics g, String text, int x, int y, int color, int anchor, short lineData[]) {
        String textNoSpecialChars;
        int sliceWidth;
        int incy;

        // Crop Text
        text = text.substring(lineData[0], lineData[1]);

        // Get Slices
        SliceString[] slices = getStringSlices(text);
        // Get String without Special Chars
        textNoSpecialChars = text.replaceAll("\\$|\\@|\\#", "");

        // Check Anchors
        if ((anchor & TFont.RIGHT) != 0) {
            x -= getLineWidth(TYPE_NORMAL, textNoSpecialChars);
        } else if ((anchor & TFont.HCENTER) != 0) {
            x -= (getLineWidth(TYPE_NORMAL, textNoSpecialChars) >> 1);
        }

        // Iterate Slices to Paint
        for (SliceString slice : slices) {
            // Calculate Slice size's
            sliceWidth = getLineWidth(slice.typeFont, slice.text);

            // Calculate incy
            switch (slice.typeFont) {
                case TYPE_SUBSCRIPT:
                    incy = metrics[TYPE_NORMAL].getDescent() >> 1;
                    break;
                case TYPE_SUPERSCRIPT:
                    incy = -(metrics[TYPE_NORMAL].getDescent() + metrics[TYPE_SUPERSCRIPT].getDescent());
                    break;
                default:
                    incy = 0;
            }

            // Set Font, color and paint
            g.setFont(fonts[slice.typeFont]);
            g.setColor(new Color(color));
            g.drawString(slice.text, x, y + incy);

            // Increment X
            x += sliceWidth;
        }
    }

    /**
     * Divide el texto en porciones según caracteres especiales:
     * - Si el texto está entre símbolos $, se escibirá en cursiva.
     * - Si el texto está entre símbolos #, se escribirá en subíndice.
     * - Si el texto está entre símbolos @, se escribirá en superíndice.
     * @param text
     * @return
     */
    private SliceString[] getStringSlices(String text) {
        int lastIndex;
        int actualState;
        char c;

        // Initialize Vector
        ArrayList<SliceString> slices = new ArrayList<SliceString>();

        // Initialize Flag
        actualState = TYPE_NORMAL;
        // Initialize Last Index
        lastIndex = 0;

        // Iterate
        for (int i = 0; i < text.length(); i++) {
            // Get Character
            c = text.charAt(i);

            switch (c) {
                case '$':
                    // Save before slice
                    slices.add(new SliceString(text.substring(lastIndex, i), actualState));
                    // Update Index
                    lastIndex = i + 1;
                    // Change state
                    actualState = actualState == TYPE_NORMAL ? TYPE_ITALIC : TYPE_NORMAL;
                    break;
                case '#':
                    // Save before slice
                    slices.add(new SliceString(text.substring(lastIndex, i), actualState));
                    // Update Index
                    lastIndex = i + 1;
                    // Change state
                    actualState = actualState == TYPE_NORMAL ? TYPE_SUBSCRIPT : TYPE_NORMAL;
                    break;
                case '@':
                    // Save before slice
                    slices.add(new SliceString(text.substring(lastIndex, i), actualState));
                    // Update Index
                    lastIndex = i + 1;
                    // Change state
                    actualState = actualState == TYPE_NORMAL ? TYPE_SUPERSCRIPT : TYPE_NORMAL;
                    break;
            }
        }

        // Add Last Slice
        slices.add(new SliceString(text.substring(lastIndex, text.length()), actualState));

        // Create Array Result
        SliceString[] slicesArray = new SliceString[slices.size()];

        for (int i = 0; i < slices.size(); i++) {
            slicesArray[i] = slices.get(i);
        }

        // Return Slices
        return slicesArray;
    }

    /**
     * Devuelve el alto que ocupará el texto, según el tamaño de la fuente, el número de líneas y
     * el espaciado
     * @param numLines Número de líneas que ocupa el texto
     * @return
     */
    public int getTextHeight(int numLines) {
        return getFontHeight() * numLines + getLineSpacing() * (numLines - 1);
    }

    /**
     * Alto de la fuente
     * @return
     */
    public int getFontHeight() {
        return metrics[TYPE_NORMAL].getHeight();
    }

    /**
     * Obtiene el espaciado
     * @return
     */
    public int getLineSpacing() {
        return lineSpacing;
    }

    /**
     * Obtiene el tamaño de un carácter blanco
     * @return
     */
    public int getSpaceSize() {
        return spaceSize;
    }

    /**
     * Esta clase representa un trozo de texto
     * @author Julio
     *
     */
    private class SliceString {

    	/**
    	 * Texto representado
    	 */
        public String text;
        /**
         * Tipo de fuente del texto
         */
        public int typeFont;

        /**
         * Crea una nueva instancia de la clase, inicializando sus valores
         * @param text
         * @param typeFont
         */
        public SliceString(String text, int typeFont) {
            this.text = text;
            this.typeFont = typeFont;
        }
    }

    /**
     * Obtiene el objeto Font para un tipo determinado
     * @param fontType
     * @return
     */
    public Font getFont(int fontType) {
        return fonts[fontType];
    }
}
