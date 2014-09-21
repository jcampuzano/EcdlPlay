/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;


/**
 * Clase que representa el tablero del juego
 * @author julio
 */
public class Board {
	/**
	 * Array de casillas dentro del tablero
	 */
    private Square squares[];

    /**
     * Constructor de la clase. Inicializa el array de casillas con tantos 
     * elementos como indique el parámetro
     * @param numSquares
     */
    public Board(int numSquares)
    {
        this.squares = new Square[numSquares];

        initialize();
    }

    /**
     * Inicializa cada una de las casillas del tablero
     */
    private void initialize()
    {
        for(int i = 0; i < squares.length; i++)
        {
            squares[i] = new Square(i);
        }
    }

    /**
     * Devuelve la casilla que corresponde al índice pasado por parámetro
     * @param numSquare Número de casilla a buscar
     * @return
     */
    public Square getSquare(int numSquare)
    {
        return squares[numSquare];
    }

    /**
     * Establece las preguntas que se corresponderán con la casilla pasada por
     * parámetro
     * @param numSquare Casilla a actualizar
     * @param questions Array de preguntas
     */
    public void setQuestions(int numSquare, Question questions[])
    {
        squares[numSquare].setQuestions(questions);
    }

    /**
     * Obtiene el número de casillas que formarán el tablero
     * @return
     */
    public int getNumSquares()
    {
        return squares.length;
    }

    /**
     * Obtiene la pregunta que le corresponde al jugador pasado por parámetro
     * @param player Jugador
     * @return
     */
    Question getQuestion(Player player) {
        Square square = this.getSquare(player.getNumSquare());
        return square.getQuestion();
    }
}
