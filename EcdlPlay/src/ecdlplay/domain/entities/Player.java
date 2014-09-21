/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;

/**
 * Clase que representa a un jugador 
 * @author julio
 */
public class Player {
	/**
	 * Número de jugador
	 */
    private int numPlayer;
    /**
     * Casilla actual dentro del tablero
     */
    private int numSquare;
    /**
     * Indica si el jugador ha caido en una casilla de frenada y no puede jugar el siguiente turno
     */
    private boolean braked;
    /**
     * Indica si la ficha del jugador está en movimiento
     */
    private boolean straight;

    /**
     * Constructor de la clase con inicializacón de variables
     * @param numPlayer Número del jugador
     * @param numSquare Posición actual del jugador
     */
    public Player(int numPlayer, int numSquare)
    {
        this.numPlayer = numPlayer;
        this.numSquare = numSquare;
        this.braked    = false;
        this.straight  = false;
    }

    /**
     * Obtiene el número del jugador
     * @return
     */
    public int getNumPlayer()
    {
        return numPlayer;
    }

    /**
     * Obtiene la casilla en la que se encuentra el jugador
     * @return
     */
    public int getNumSquare()
    {
        return numSquare;
    }

    /**
     * Establece la casilla en la que se encuentra el jugador
     * @param numSquare
     */
    public void setNumSquare(int numSquare)
    {
        this.numSquare = numSquare;
    }

    /**
     * Indica si el jugador está detenido y no puede jugar
     * @return
     */
    public boolean isBraked()
    {
        return braked;
    }

    /**
     * Establece que el jugador está detenido
     * @param braked
     */
    public void setBraked(boolean braked)
    {
        this.braked = braked;
    }

    /**
     * Indica si la ficha del jugador está en movimiento
     * @return
     */
    public boolean isStraight()
    {
        return straight;
    }

    /**
     * Establece que la ficha del jugador está en movimiento
     * @param straight
     */
    public void setStraight(boolean straight)
    {
        this.straight = straight;
    }
}
