/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.*;
import java.util.List;

/**
 *
 * @author julio
 */
public interface GameView {
        
    public void showDices(int value);
    
    public void hideDices();
    
    public void showQuestion(Question pregunta);
    
    public void hideQuestion();
    
    public void movePlayer(Player player, int square);
    
    public void showSplash();
        
    public void showPrincipal();
    
    public void showMenu(GameConfig config);
    
    public void showBoard(GameConfig config);
        
    public void showAnswer(boolean correcta);
    
    public void setPlayerTurn(Player player);
    
    public void AnswerSelected(int numAnswer);
    
    public void MenuExit(GameConfig config);
    
    public void GameExit();

    public void showWinner(Player player);

}
