/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.gui;

import com.sun.org.apache.bcel.internal.util.JavaWrapper;
import ecdlplay.domain.Player;
import ecdlplay.domain.Pregunta;
import ecdlplay.domain.Respuesta;
import java.awt.event.ActionEvent;
import java.io.Console;

/**
 *
 * @author julio
 */
public class GameViewConsole implements GameView {

    
    @Override
    public void showDices(int value) {
        System.out.printf("Dados lanzados, valor: %s", value);
    }

    @Override
    public void hideDices() {
        System.out.printf("Dados ocultados");
    }

    @Override
    public void showQuestion(Pregunta pregunta) {
        System.out.printf("Pregunta: %s", pregunta.getTexto());
        for(Respuesta respuesta : pregunta.getRespuestas())
        {
            System.out.printf("Respuesta: %s", respuesta.getTexto());
        }
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
