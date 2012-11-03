/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import ecdlplay.domain.Player;
import ecdlplay.domain.Pregunta;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

/**
 *
 * @author julio
 */
public class GameViewCanvas extends JPanel implements GameView {

    @Override
    public void showDices(int value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void hideDices() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showQuestion(Pregunta pregunta) {
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
    public void showMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void showBoard() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPlayerTurn(Player player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ActionEvent AnswerSelected(int numAnswer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ActionEvent NumPlayerChanged(int numPlayers) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ActionEvent ModuloChanged(int idModulo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
