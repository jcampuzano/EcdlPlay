/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay;

import ecdlplay.gui.*;
/**
 * La primera clase de la aplicaci�n. Encargada �nicamente de ser el punto de entrada de ejecuci�n y cargar la ventana de la aplicaci�n.
 *  
 * @author julio
 */
public class EcdlPlayMain {

    /**
     * Punto de entrada de la aplicaci�n. Crea una instancia de EcdlPlayWindow y la muestra.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EcdlPlayWindow.getInstance();        
    }
}
