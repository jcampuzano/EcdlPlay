/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay;

import ecdlplay.gui.*;
/**
 * La primera clase de la aplicación. Encargada únicamente de ser el punto de entrada de ejecución y cargar la ventana de la aplicación.
 *  
 * @author julio
 */
public class EcdlPlayMain {

    /**
     * Punto de entrada de la aplicación. Crea una instancia de EcdlPlayWindow y la muestra.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EcdlPlayWindow.getInstance();        
    }
}
