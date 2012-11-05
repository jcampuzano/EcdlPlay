/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.data.interfaces;

import ecdlplay.domain.Answer;
import java.util.List;

/**
 *
 * @author julio
 */
public interface RespuestasRepositoryGeneric extends RepositoryGeneric<Answer> {
    /**
     * 
     * @param idPregunta
     * @return 
     */
    List<Answer> getByPregunta(int idPregunta);
}
