/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    private TextField txtNombre = new TextField();
    
    @FXML
    private TextField txtApellidos = new TextField();
    
    @FXML
    private TextField txtDNI = new TextField();
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
