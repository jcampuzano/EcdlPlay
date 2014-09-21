/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.data;

import ecdlplay.domain.entities.Answer;
import ecdlplay.domain.entities.Image;
import ecdlplay.domain.entities.Module;
import ecdlplay.domain.entities.Question;
import ecdlplay.gui.canvas.GameCanvasConstants;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Clase encargada de leer el fichero de datos e ir almacenando la información en memoria
 * 
 * La estructura del XML será la siguiente:
 * <ecdlPlay> *  
 *  <dificultades>
 *      <dificultad>
 *          <descripcion></descripcion>
 *          <valor></valor>
 *      </dificultad>
 *  </dificultades>
 *  <modulos>
 *      <modulo>
 *          <id></id>
 *          <nombre></nombre>
 *          <descripcion></descripcion>
 *          <preguntas>
 *              <pregunta>
 *                  <texto></texto>
 *                  <dificultad></dificultad>
 *                  <imagen></imagen>
 *                  <respuestas>
 *                      <respuesta correcto="1"></respuesta>
 *                      <respuesta></respuesta>
 *                      <respuesta></respuesta>
 *                  </respuestas>
 *              </pregunta>
 *          </preguntas>
 *      </modulo>
 *  </modulos>
 * </ecdlPlay>
 * 
 */
public class GameDataLoader {

	/**
	 * Instancia singleton de la clase
	 */
    private static GameDataLoader dl = null;
    /**
     * Instancia de la clase GameData donde se almacenará la información
     */
    private GameData gd;

    /**
     * Devuelve la instancia de la clase GameData. Si no está inicializada, carga los datos
     * @return
     */
    public GameData getGameData() {
        if (gd == null) {
            loadGameData();
        }
        return gd;
    }
    
    /**
     * Método que se encarga de leer el fichero XML e ir cargando la información 
     */
    private void loadGameData() {
        // Create New Game Data
        gd = new GameData();
        // Get Document
        Document doc = loadXML(GameCanvasConstants.RES_PATH + "datos.xml");
        
        // Load Questions
        loadModules(gd, doc);
    }

    /**
     * Método auxiliar que sirve para obtener el valor de un nodo del fichero XML dado el elemento padre y el nombre
     * @param sTag Nombre del nodo que estamos buscando
     * @param eElement Nodo padre
     * @return
     */
    private String getTagValue(String sTag, Element eElement) {
        NodeList elementsByTagName = eElement.getElementsByTagName(sTag);
        if (elementsByTagName != null && elementsByTagName.getLength() > 0){
            NodeList nlList = elementsByTagName.item(0).getChildNodes();
            Node nValue = (Node) nlList.item(0);

            return nValue.getNodeValue();
        } 
        return "";
    }

    /**
     * Lee el fichero XML y genera una instancia de la clase Document para su lectura
     * @param filename Ruta del fichero
     * @return
     */
    private Document loadXML(String filename) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(filename));

            return doc;
        } catch (Exception e) {
        }

        return null;
    }

    /**
     * Devuelve la instancia singleton
     * @return
     */
    public static GameDataLoader getLoader() {
        if (dl == null) {
            dl = new GameDataLoader();
        }

        return dl;
    }

    /**
     * Lee los nodos modulo y va añadiendolos a la instancia GameData
     * @param gd Instancia GameData
     * @param doc Documento XML
     */
    private void loadModules(GameData gd, Document doc) {
        // Get Node List Questions
        NodeList nList = doc.getElementsByTagName("modulo");
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                // Convert to Element
                Element e = (Element) node;
                
                Module m = new Module();
                gd.addModulo(m);
                
                int id = Integer.parseInt(getTagValue("id", e));
                String nombre = getTagValue("nombre", e);
                String descripcion = getTagValue("descripcion", e);
                
                m.setId(id);
                m.setName(nombre);
                m.setDescription(descripcion);
                
                loadQuestions(gd, e, m);
            }
        }
    }
    
    /**
     * Lee los nodos pregunta y los va añadiendo al módulo
     * @param gd Instancia de la clase GameData
     * @param elem Elemento del documento XML
     * @param m Instancia del módulo al que pertenecerá la pregunta
     */
    private void loadQuestions(GameData gd, Element elem, Module m){
        NodeList nList = elem.getElementsByTagName("pregunta");
        
        for(int i = 0; i < nList.getLength(); i++){
            Node nodo = nList.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element)nodo;
                
                Question q = new Question();
                m.addQuestion(q);
                
                String texto = getTagValue("texto", e);
                String imagen = getTagValue("imagen", e);
                int dificultad = Integer.parseInt(getTagValue("dificultad", e));
                
                q.setTexto(texto);
                
                if (imagen != null && !imagen.equals("")){
                    Image img = new Image(imagen);
                    q.setImagen(img);
                }
                
                q.setDificultad(dificultad);
                
                loadAnswers(gd, e, q);
            }
        }
    }

    /**
     * Lee los nodos de tipo respuesta y los añade a la pregunta correspondiente
     * @param gd Instancia de la clase GameData
     * @param elem Elemento del documento XML
     * @param q Pregunta a la que pertenecerá la respuesta
     */
    private void loadAnswers(GameData gd, Element elem, Question q) {
        NodeList nList = elem.getElementsByTagName("respuesta");
        
        for(int i = 0; i < nList.getLength(); i++){
            Node nodo = nList.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element)nodo;
                
                Answer a = new Answer();
                q.addAnswer(a);
                
                String correcto = e.getAttribute("correcto");
                String texto = e.getTextContent();
                
                a.setCorrecta(correcto.equalsIgnoreCase("1"));
                a.setTexto(texto);
            }
        }
    }

}
