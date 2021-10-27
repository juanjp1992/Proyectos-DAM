/**
 * Paquete del componente gráfico "formularioDatos"
 */
package formularioDatos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * Componente Gráfico "FormularioDatos"
 * @author Juan Jimenez Perez
 */
public class FormularioDatosController implements Initializable {
    //Llamo a todos los componentes.
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtApellidos;
    
    @FXML
    private TextField txtDNI;
    
    /**
    * Su función es recuperar el campo Nombre para su uso.
    * @return Devuelve el texto del campo Nombre.
    */
    public String obtenNombre(){
        return txtNombre.getText();
    }
    
    /**
    * Su función es recuperar el campo Apellidos para su uso.
    * @return Devuelve el texto del campo Apellidos.
    */
    public String obtenApellidos(){
        return txtApellidos.getText();
    }
    /**
    * Su función es recuperar el campo DNI para su uso.
    * @return Devuelve el texto del campo DNI.
    */
    public String obtenDNI(){
        return txtDNI.getText();
    }
    /**
    * Su función es la de limpiar los datos de los tres campos TextField.
    */
    public void limpiarCampos(){
        //Limpio los datos de los tres campos de texto
        txtNombre.clear();
        txtApellidos.clear();
        txtDNI.clear();
    }
    /**
    * Método desarrollado para la tarea RA3_E.
    * @return Devuelve resultados diferentes según el radio que le pasemos.
    */
    public static double supCircunferencia(double r){
        if(r >= 0)
            return Math.PI * r * r;
        return -1;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
