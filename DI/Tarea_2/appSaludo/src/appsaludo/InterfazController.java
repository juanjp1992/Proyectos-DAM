/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appsaludo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author juanj
 */
public class InterfazController implements Initializable {
    //Declaro los componentes con los que voy a trabajar indicándolo con la etiqueta @FXML.
    @FXML
    private Label txtHola;
    @FXML
    private TextField inputNombre;
    
    //Creo un método para darle funcionalidad al botón "Di, Hola".
    @FXML
    private void btnHola(ActionEvent event) {
        //Recojo los datos del TextField "inputNombre" y devuelvo un texto formateado en el Label "txtHola"
        txtHola.setText("Hola " + inputNombre.getText());
    }
    
    @Override
    //Al inicializar la app, realizará lo que aparece aquí dentro.
    public void initialize(URL url, ResourceBundle rb) {
        //Al iniciar hago que aparezca el Label "txtHola" sin texto.
        txtHola.setText("");
    }    
    
}
