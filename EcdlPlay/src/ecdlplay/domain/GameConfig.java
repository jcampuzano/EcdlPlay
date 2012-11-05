/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain;

import java.util.List;

/**
 *
 * @author julio
 */
public class GameConfig {
    
    private int numPlayers;
    private Module modulo;
    
    private List<Module> modulos;
    private int maxNumPlayers;

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Module getModulo() {
        return modulo;
    }

    public void setModulo(Module modulo) {
        this.modulo = modulo;
    }

    public List<Module> getModulos() {
        return modulos;
    }

    public void setModulos(List<Module> modulos) {
        this.modulos = modulos;
    }

    public int getMaxNumPlayers() {
        return maxNumPlayers;
    }

    public void setMaxNumPlayers(int maxNumPlayers) {
        this.maxNumPlayers = maxNumPlayers;
    }

    public GameConfig(){
        
    }
    
    public GameConfig(int numPlayers, Module modulo, List<Module> modulos, int maxNumPlayers) {
        this.numPlayers = numPlayers;
        this.modulo = modulo;
        this.modulos = modulos;
        this.maxNumPlayers = maxNumPlayers;
    }
    
}
