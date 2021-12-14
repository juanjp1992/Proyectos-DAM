
package generadorinformesdi;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.fxutils.viewer.JasperViewerFX;


/**
 *
 * @author Juan Jimenez Perez
 */
public class InterfazController implements Initializable {
    
    //Arraylist donde almacenaré todos los alumnos en memoria.
    private ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
    
    //Método enlazado al boton para ver el informe de los matriculados en DI.
    @FXML
    private void btnMatriculadosDI(ActionEvent event) {
        abreInformes("Matrículados en DI" ,"/informes/matriculadosDI.jasper", event);
    }
    
    //Método enlazado al botón que mostrará el Gráfico por Sectores.
    @FXML
    private void btnGraficoSectores(ActionEvent event) {
        abreInformes("Gráfico de Sectores" ,"/informes/graficoSectores.jasper", event);
            
    }
    
    //Método del botón que se encargará de mostrar el Gráfico de Barras.
    @FXML
    private void btnGraficoBarras(ActionEvent event) {
        abreInformes("Gráfico de barras" ,"/informes/graficoBarras.jasper", event);
    }
    
    //Método enlazado al botón Salir.
    @FXML
    private void btnSalir(ActionEvent event) {
        System.exit(0);
    }
    
    //Método encargado de abrir los informes.
    private void abreInformes(String titulo, String ruta, ActionEvent event){
        try {
            //Creamos la colección que usará el informe.
            JRBeanCollectionDataSource beanColDataSource = new 
                        JRBeanCollectionDataSource(alumnos);
            
            /*Recuperamos el evento del botón en el cual pulsamos y 
            creamos un nodo con el.*/
            Node source = (Node) event.getSource();
            
            /*Obtenemos la escena a la que pertenece el botón
            para después poder mostrar la ventana modal con 
            nuestro informe*/
            Stage stage = (Stage) source.getScene().getWindow();
            
            /*Creamos el visor de informes pasandole la ventana 
            desde donde se pide, el titulo, la ruta del informe, 
            y los datos*/
            JasperViewerFX viewerfx = new JasperViewerFX(
                stage,titulo, ruta, new HashMap(), beanColDataSource);
            
            // Y mostramos el informe.
            viewerfx.show();
            
        //En caso de error al mostrar el informe, mostramos un mensaje.
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Error al abrir el informe.");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Añado todos los datos de alumnos matriculados.
        alumnos.add(new Alumno("47619383E", "DI", 8.5, "Barcelona"));
        alumnos.add(new Alumno("84072996A", "DI", 6.5, "Álava"));
        alumnos.add(new Alumno("56417542S", "DI", 6.2, "Badajoz"));
        alumnos.add(new Alumno("29696137F", "DI", 6.8, "Cáceres"));
        alumnos.add(new Alumno("17764861G", "DI", 9.2, "Gerona"));
        alumnos.add(new Alumno("96885444H", "DI", 9.8, "Burgos"));
        alumnos.add(new Alumno("82934908J", "DI", 7.8, "Badajoz"));
        alumnos.add(new Alumno("45732855L", "DI", 8.5, "Almería"));
        alumnos.add(new Alumno("51812891X", "DI", 8.2, "Ávila"));
        alumnos.add(new Alumno("26718066Z", "DI", 7.3, "Cádiz"));
        
        alumnos.add(new Alumno("27085400F", "PMDM", 9.5, "Castellón"));
        alumnos.add(new Alumno("32289838D", "PMDM", 6.5, "Cuenca"));
        alumnos.add(new Alumno("43824741I", "PMDM", 9.3, "Granada"));
        alumnos.add(new Alumno("24317945O", "PMDM", 5.8, "Guadalajara"));
        alumnos.add(new Alumno("52507625P", "PMDM", 6.4, "Guipúzcoa"));
        alumnos.add(new Alumno("45884938E", "PMDM", 8.9, "Barcelona"));
        
        alumnos.add(new Alumno("80928823Q", "AD", 7.3, "Huelva"));
        alumnos.add(new Alumno("04210749A", "AD", 7.7, "Jaén"));
        alumnos.add(new Alumno("17088396J", "AD", 6.9, "León"));
        alumnos.add(new Alumno("87048589Y", "AD", 6.3, "Madrid"));
        alumnos.add(new Alumno("43809586U", "AD", 8.5, "Orense"));
        
        alumnos.add(new Alumno("55738274S", "PSP", 7.4, "Palencia"));
        alumnos.add(new Alumno("63181863A", "PSP", 9.9, "Salamanca"));
        alumnos.add(new Alumno("70430728G", "PSP", 7.1, "Soria"));
        
                        
        //Ordenamos el arraylist
        Collections.sort(alumnos);
       
        //Y mostramos un mensaje por consola mostrando el número de datos añadidos.
        System.out.println("Se han añadido " + alumnos.size() + " alumnos.");    
        
       
    }    
    
}
