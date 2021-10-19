
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
        
        //Contruyo parser analizador del documento XML usando el objeto Builder.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        
        try {
            builder = factory.newDocumentBuilder();
        } 
        catch (ParserConfigurationException e) {
            System.out.println("ERROR: Fallo en la creación del documentBuilder --> " + e);
        }
        //Recuperamos la ruta del archivo alumno.xml
        File ficheroXML = new File(System.getProperty("user.home") + File.separator + "Desktop", "alumno.xml");
        
        //Parseamos el Documento
        Document doc = null;
        try {
            doc = builder.parse(ficheroXML);
        } 
        catch (IOException ex) {
            System.out.println("ERROR: Fallo en el fichero XML --> " + ex);
        }
        catch(SAXException ex){
            System.out.println("ERROR: Fallo en el parseo del fichero --> " + ex);
        }
        
        //Normalizamos el documento
        doc.getDocumentElement().normalize();
        
        //Recuperamos nuestro nodos raiz
        
        NodeList raiz = doc.getElementsByTagName("Alumno");
        
        //Abrimos la etiqueta raiz,recuperamos su nombre y lo mostramos en consola
        System.out.println("<" + doc.getDocumentElement().getNodeName() + ">");
        
        /*Cogemos la lista de nodos de la raiz y la añadimos a otro NodeList
          para recuperar todos los nodos hijos */
        NodeList lista = raiz.item(0).getChildNodes();
        
        //Y ahora la recorremos para recuperar todos los nodos hijos.
        for (int i = 0; i < lista.getLength(); i++) {
            //Lo metemos dentro de un objeto de la clase Node
            Node d = lista.item(i);
            
            //Mostramos la etiqueta de apertura del nodo hijo
            System.out.print("\t<"+d.getNodeName()+">");
            
            //Comprobamos que es una etiqueta de la que se puede sacar texto
            if(d.getNodeType() == Node.ELEMENT_NODE){
                System.out.print(d.getFirstChild().getNodeValue());
            }
            
            //Mostramos la etiqueta de cierre de ese nodo hijo
            System.out.println("</" + d.getNodeName() + ">");
        }
        
        //Y por último cerramos la etiqueta raiz
        System.out.println("</" + doc.getDocumentElement().getNodeName() + ">");
        
        
    }
}
