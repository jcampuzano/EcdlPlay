/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.infrastructure.interfaces;

import ecdlplay.domain.Respuesta;
import java.util.List;

/**
 *
 * @author julio
 */
public interface RespuestasRepositoryGeneric extends RepositoryGeneric<Respuesta> {
    /**
     * 
     * @param idPregunta
     * @return 
     */
    List<Respuesta> getByPregunta(int idPregunta);
}
