/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.Player;
import ecdlplay.domain.Pregunta;
import java.awt.event.ActionEvent;

/**
 *
 * @author julio
 */
public interface GameView {
        
    public void showDices(int value);
    
    public void hideDices();
    
    public void showQuestion(Pregunta pregunta);
    
    public void hideQuestion();
    
    public void movePlayer(Player player, int square);
    
    public void showSplash();
        
    public void showPrincipal();
    
    public void showMenu();
    
    public void showBoard();
    
    public void setPlayerTurn(Player player);
    
    public ActionEvent AnswerSelected(int numAnswer);
    
    public ActionEvent NumPlayerChanged(int numPlayers);
    
    public ActionEvent ModuloChanged(int idModulo);
}
