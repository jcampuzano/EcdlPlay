/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.*;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author julio
 */
public class GameViewCanvas extends JPanel implements GameView {

    private GameEngine gameEngine;
    public GameViewCanvas(){
        this.gameEngine = new GameEngine(this);
        
    }
    
    @Override
    public void showDices(int value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hideDices() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showQuestion(Question pregunta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hideQuestion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void movePlayer(Player player, int square) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showSplash() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showPrincipal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showMenu(GameConfig config) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showBoard(GameConfig config) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPlayerTurn(Player player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AnswerSelected(int numAnswer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    @Override
    public void MenuExit(GameConfig config){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void GameExit(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showAnswer(boolean correcta) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showWinner(Player player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
