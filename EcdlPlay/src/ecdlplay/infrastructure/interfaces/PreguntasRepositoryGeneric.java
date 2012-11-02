/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.infrastructure.interfaces;
import ecdlplay.domain.Pregunta;
import java.util.List;
/**
 *
 * @author julio
 */
public interface PreguntasRepositoryGeneric extends RepositoryGeneric<Pregunta> {
    
    List<Pregunta> getByModulo(int idModulo);
    
    List<Pregunta> getByModuloAndDificultad(int idModulo, int idDificultad);
    
}
