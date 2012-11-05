/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.data.interfaces;
import ecdlplay.domain.Question;
import java.util.List;
/**
 *
 * @author julio
 */
public interface PreguntasRepositoryGeneric extends RepositoryGeneric<Question> {
    
    List<Question> getByModulo(int idModulo);
    
    List<Question> getByModuloAndDificultad(int idModulo, int idDificultad);
    
}
