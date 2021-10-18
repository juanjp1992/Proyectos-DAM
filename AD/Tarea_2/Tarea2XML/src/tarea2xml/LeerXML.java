
package tarea2xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Juan Jimenez Perez
 */
public class LeerXML {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        
        try {
            builder = factory.newDocumentBuilder();
        } 
        catch (ParserConfigurationException e) {
            System.out.println("ERROR: Fallo en la creación del documentBuilder");
        }
        
        File ficheroXML = new File(System.getProperty("user.home") + File.separator + "Desktop", "alumno.xml");
        
        //Parseamos el Documento
        
        Document doc = null;
        try {
            doc = builder.parse(ficheroXML);
        } 
        catch (IOException ex) {
            System.out.println("ERROR: Fallo en el fichero XML");
        }
        catch(SAXException ex){
            System.out.println("ERROR: Fallo en el parseo del fichero");
        }
        
        //Normalizamos el documento
        doc.getDocumentElement().normalize();
        
        //Creamos una lista de nodos con todos los nodos de la raiz
        NodeList raiz = doc.getElementsByTagName("Alumno");
        
        //Abrimos la etiqueta raiz
        System.out.println("<" + doc.getDocumentElement().getNodeName() + ">");
        
        //Cogemos la lista de nodos de la raiz
        NodeList lista = raiz.item(0).getChildNodes();
        
        for (int i = 0; i < lista.getLength(); i++) {
            //
            Node d = lista.item(i);
            
            //Mostramos la etiqueta de apertura de ese nodo
            System.out.print("\t<"+d.getNodeName()+">");
            
            //Comprobamos que es una etiqueta que se le puede sacar texto
            
            if(d.getNodeType() == Node.ELEMENT_NODE){
                System.out.print(d.getFirstChild().getNodeValue());
            }
            
            //Mostramos la etiqueta de cierre de ese nodo
            System.out.println("</" + d.getNodeName() + ">");
            
            
        }
        
        //Cerramos la etiqueta raiz
        System.out.println("</" + doc.getDocumentElement().getNodeName() + ">");
        
        
    }
}
