/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.infrastructure.interfaces;

import java.util.*;
/**
 *
 * @author julio
 */
public interface RepositoryGeneric<T> {
    List<T> getAll();
    
    T getById(int id);
    
    void Add(T entity);
    
    void Update(T entity);
    
    void Delete(T entity);
    
    void PopulateDataSet(List<T> dataset);
    
}
