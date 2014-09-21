/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.domain.entities;

/**
 * Clase base para las entidades. Uso futuro
 * @author julio
 */
public class Entity {
	/**
	 * Identificador �nico del registro
	 */
    private int id;
    /**
     * Descripci�n del registro
     */
    private String description;
    
    /**
     * Constructor por defecto del registro
     */
    protected Entity(){
        
    }
    
    /**
     * Constructor del registro con inicializaci�n de variables
     * @param id Identificador 
     * @param description Descripci�n
     */
    protected Entity(int id, String description){
        this.id = id;
        this.description = description;
    }
    
    /**
     * Obtiene el actual identificador del registro
     * @return
     */
    public int getId(){
        return id;
    }
    /**
     * Establece el valor del identificador del registro
     * @param id
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * Obtiene el valor actual de la descripci�n del registro
     * @return
     */
    public String getDescription(){
        return description;
    }
    /**
     * Establece el valor de la descripci�n del registro
     * @param description
     */
    public void setDescription(String description){
        this.description = description;
    }
}
