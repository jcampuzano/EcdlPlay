/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

/**
 *
 * @author julio
 */
public class Board {
    private Square squares[];

    public Board(int numSquares)
    {
        this.squares = new Square[numSquares];

        initialize();
    }

    private void initialize()
    {
        for(int i = 0; i < squares.length; i++)
        {
            squares[i] = new Square(i);
        }
    }

    public Square getSquare(int numSquare)
    {
        return squares[numSquare];
    }

    public void setQuestions(int numSquare, Pregunta questions[])
    {
        squares[numSquare].setQuestions(questions);
    }

    public int getNumSquares()
    {
        return squares.length;
    }
}
