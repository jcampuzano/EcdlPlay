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
	 * N�mero de jugador
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
     * Indica si la ficha del jugador est� en movimiento
     */
    private boolean straight;

    /**
     * Constructor de la clase con inicializac�n de variables
     * @param numPlayer N�mero del jugador
     * @param numSquare Posici�n actual del jugador
     */
    public Player(int numPlayer, int numSquare)
    {
        this.numPlayer = numPlayer;
        this.numSquare = numSquare;
        this.braked    = false;
        this.straight  = false;
    }

    /**
     * Obtiene el n�mero del jugador
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
     * Indica si el jugador est� detenido y no puede jugar
     * @return
     */
    public boolean isBraked()
    {
        return braked;
    }

    /**
     * Establece que el jugador est� detenido
     * @param braked
     */
    public void setBraked(boolean braked)
    {
        this.braked = braked;
    }

    /**
     * Indica si la ficha del jugador est� en movimiento
     * @return
     */
    public boolean isStraight()
    {
        return straight;
    }

    /**
     * Establece que la ficha del jugador est� en movimiento
     * @param straight
     */
    public void setStraight(boolean straight)
    {
        this.straight = straight;
    }
}
