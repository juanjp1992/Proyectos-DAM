/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2xml;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author Juan Jimenez Perez
 */
public class EscribirXML {

    public static void main(String[] args) {
        //Contruyo parser analizador del documento XML usando el objeto Builder.
        DOMImplementation imp = null;
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            imp = builder.getDOMImplementation();
        }
        catch(ParserConfigurationException ex){
            System.out.println("ERROR: En la creación del DOM --> " + ex);
        } 
        
        //Creamos un documento de XML con su nodo raiz.
        Document doc = imp.createDocument(null, "Alumno", null);
                
        //Le añadimos la versión XML al Documento
        doc.setXmlVersion("1.0");
        
        //Creamos la etiqueta hija "Nombre"
        Element etiqueta = doc.createElement("Nombre");
        
        //Creamos el texto que se pide en al tarea para luego añadirlo al nodo Nombre.
        Text nombre = doc.createTextNode("Juan Jimenez Perez");
        
        //Añadimos la etiqueta hija al documento, por lo que aparecerá dentro de la raiz alumno.
        doc.getDocumentElement().appendChild(etiqueta);
        
        //Añadimos el texto a la etiqueta <nombre>.
        etiqueta.appendChild(nombre);

        //Después transformamos el document XML al archivo final XML.
        Source docFuente = new DOMSource(doc);
        
        //Creamos un File con la ruta que tendrá nuestro XML.
        File ficheroXML = new File(System.getProperty("user.home") + File.separator + "Desktop", "alumno.xml");
        
        
        Result result = new StreamResult(ficheroXML);
        
        //Juntamos nuestro archivo xml con todo lo que hemos creado.
        try {
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.transform(docFuente, result);
        } catch (TransformerException ex) {
            System.out.println("ERROR: Fallo en la transformación --> " + ex);
        }
        
    }
    
}
