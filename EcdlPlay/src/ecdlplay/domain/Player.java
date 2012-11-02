/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

/**
 *
 * @author julio
 */
public class Player {
    private int numPlayer;
    private int numSquare;
    private boolean braked;
    private boolean straight;

    public Player(int numPlayer, int numSquare)
    {
        this.numPlayer = numPlayer;
        this.numSquare = numSquare;
        this.braked    = false;
        this.straight  = false;
    }

    public int getNumPlayer()
    {
        return numPlayer;
    }

    public int getNumSquare()
    {
        return numSquare;
    }

    public void setNumSquare(int numSquare)
    {
        this.numSquare = numSquare;
    }

    public boolean isBraked()
    {
        return braked;
    }

    public void setBraked(boolean braked)
    {
        this.braked = braked;
    }

    public boolean isStraight()
    {
        return straight;
    }

    public void setStraight(boolean straight)
    {
        this.straight = straight;
    }
}
