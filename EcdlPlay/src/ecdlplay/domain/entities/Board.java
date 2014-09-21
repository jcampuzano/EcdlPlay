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
     * elementos como indique el par�metro
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
     * Devuelve la casilla que corresponde al �ndice pasado por par�metro
     * @param numSquare N�mero de casilla a buscar
     * @return
     */
    public Square getSquare(int numSquare)
    {
        return squares[numSquare];
    }

    /**
     * Establece las preguntas que se corresponder�n con la casilla pasada por
     * par�metro
     * @param numSquare Casilla a actualizar
     * @param questions Array de preguntas
     */
    public void setQuestions(int numSquare, Question questions[])
    {
        squares[numSquare].setQuestions(questions);
    }

    /**
     * Obtiene el n�mero de casillas que formar�n el tablero
     * @return
     */
    public int getNumSquares()
    {
        return squares.length;
    }

    /**
     * Obtiene la pregunta que le corresponde al jugador pasado por par�metro
     * @param player Jugador
     * @return
     */
    Question getQuestion(Player player) {
        Square square = this.getSquare(player.getNumSquare());
        return square.getQuestion();
    }
}
