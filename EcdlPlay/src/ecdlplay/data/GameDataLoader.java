/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecdlplay.data;

import ecdlplay.domain.*;
import ecdlplay.gui.GameCanvas;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
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

    private static GameDataLoader dl = null;
    private GameData gd;

    public GameData getGameData() {
        if (gd == null) {
            loadGameData();
        }
        return gd;
    }
    
    private void loadGameData() {
        // Create New Game Data
        gd = new GameData();
        // Get Document
        Document doc = loadXML(GameCanvas.RES_PATH + "data.xml");
        
        // Load Questions
        loadModules(gd, doc);
    }

    private String getTagValue(String sTag, Element eElement) {
        NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);

        return nValue.getNodeValue();
    }

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

    public static GameDataLoader getLoader() {
        if (dl == null) {
            dl = new GameDataLoader();
        }

        return dl;
    }

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
                m.setNombre(nombre);
                m.setDescripcion(descripcion);
                
                loadQuestions(gd, e, m);
            }
        }
    }
    
    private void loadQuestions(GameData gd, Element elem, Module m){
        NodeList nList = elem.getElementsByTagName("pregunta");
        
        for(int i = 0; i < nList.getLength(); i++){
            Node nodo = nList.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE){
                Element e = (Element)nodo;
                
                Question q = new Question();
                m.addPregunta(q);
                
                String texto = getTagValue("texto", elem);
                String imagen = getTagValue("imagen", elem);
                int dificultad = Integer.parseInt(getTagValue("dificultad", elem));
                
                q.setTexto(texto);
                
                Image img = new Image(imagen);
                q.setImagen(img);
                
                q.setDificultad(dificultad);
                
                loadAnswers(gd, e, q);
            }
        }
    }

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
