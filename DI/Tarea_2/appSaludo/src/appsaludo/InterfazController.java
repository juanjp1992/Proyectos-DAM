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
    
    @FXML
    private Label txtHola;
    @FXML
    private TextField inputNombre;
    
    @FXML
    private void btnHola(ActionEvent event) {
        txtHola.setText("Hola " + inputNombre.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtHola.setText("");
    }    
    
}
