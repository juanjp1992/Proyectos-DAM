/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea4di;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author juanj
 */
public class InterfazController implements Initializable {
    
    @FXML
    AnchorPane panelContenedor;
    
    @FXML
    private void aplicarEstilo1(ActionEvent event) {
        this.cambiarEstilo("/estilos/estilo1.css");
    }
    @FXML
    private void aplicarEstilo2(ActionEvent event) {
        this.cambiarEstilo("/estilos/estilo2.css");
    }
    @FXML
    private void aplicarEstilo3(ActionEvent event) {
        this.cambiarEstilo("/estilos/estilo3.css");
    }
    @FXML
    private void resetear(ActionEvent event) {
        panelContenedor.getStylesheets().clear();
    }
    
    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }
    
    private void cambiarEstilo(String rutaCSS){  
    panelContenedor.getStylesheets().clear();
    panelContenedor.getStylesheets().add(rutaCSS);
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
