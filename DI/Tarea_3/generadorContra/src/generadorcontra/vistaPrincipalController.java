/**
 * Paquete de la vista principal del proyecto generadorContra
 */
package generadorcontra;

import formularioDatos.FormularioDatosController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Controlador de la Vista Principal del Generador de Contraseñas"
 * @author Juan Jimenez Perez
 */
public class vistaPrincipalController implements Initializable {
    //Llamo a mi componente gráfico formularioDatos.
    @FXML
    FormularioDatosController formularioDatosController = new FormularioDatosController();
    
    //Llamo a mi componente txtContra para hacer uso de el.
    @FXML
    TextField txtContra = new TextField();
    
    
    //Acción del botón Generar Contraseña, el cual nos realizará todo para crear una nueva contraseña a partir de los campos Nombre, Apellidos y DNI.
    @FXML
    private void btnGenerarContra(ActionEvent event) {
        //En el caso de que no haya ningún campo vacio, genero la contraseña.
        if(!formularioDatosController.obtenNombre().isEmpty() && !formularioDatosController.obtenApellidos().isEmpty() && !formularioDatosController.obtenDNI().isEmpty()){
            /*Introduzco en el campo "Contraseña Generada" la contraseña cifrada tal y como
            se pedia en la tarea, haciendo uso del método cifradoContra, al cual al pasarle
            los datos recuperados del componente gráfico me devuelve la contraseña ya cifrada.*/
            txtContra.setText(cifradoContra(formularioDatosController.obtenNombre(), formularioDatosController.obtenApellidos(), formularioDatosController.obtenDNI()));
        }
        //En el caso contrario, devuelvo un PopUp de alerta avisando de que hay campos vacios.
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Campos Vacios");
            alert.setTitle("Advertencia");
            alert.setContentText("Para generar una contraseña es necesario rellenar todos los campos.");
            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void btnNuevosDatos(ActionEvent event) {
        //Utilizo el método de mi componente gráfico llamado "limpiar campos".
        formularioDatosController.limpiarCampos();
        
        //Limpio los datos de la contraseña generada.
        txtContra.clear();
    }
    
    //Acción del botón Salir, el cual nos cierra la aplicación.
    @FXML
    private void btnSalir(ActionEvent event) {
        System.exit(0);
    }
    
    //He creado un método extra para cifrar la contraseña y dejarla con el formato deseado, con el fin de mantener el código más limpio
    private String cifradoContra(String nombre, String apellidos, String DNI){
        //Reformateo todos los textos para darle el formato correspondiente y además lo pongo todo en mayúsculas.
        String cifrado = 
                (nombre.substring(0, 1) +
                apellidos.substring(0, 3) +
                DNI.substring(DNI.length()-4, DNI.length())).toUpperCase();
        
        //Devuelvo el texto ya formateado.
        return cifrado;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
