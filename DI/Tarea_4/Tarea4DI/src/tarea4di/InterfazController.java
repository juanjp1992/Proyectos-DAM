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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Esto es una prueba
 * @author Juan Jimenez Perez
 */
public class InterfazController implements Initializable {
    
    /*Componente AnchorPane de nuestra aplicación 
    que he traido para poder usarlo en el código*/
    @FXML
    AnchorPane panelContenedor;
    
    /**
    * Aplica el estilo 1
    * @param event Capta el evento del Botón Aplicar Estilo 1
    */
    @FXML
    private void aplicarEstilo1(ActionEvent event) {
        this.cambiarEstilo("/estilos/estilo1.css");
    }
    /**
    * Aplica el estilo 2
    * @param event Capta el evento del Botón Aplicar Estilo 2
    */
    @FXML
    private void aplicarEstilo2(ActionEvent event) {
        this.cambiarEstilo("/estilos/estilo2.css");
    }
    
    /**
    * Aplica el estilo 3
    * @param event Capta el evento del Botón Aplicar Estilo 3
    */
    @FXML
    private void aplicarEstilo3(ActionEvent event) {
        this.cambiarEstilo("/estilos/estilo3.css");
    }
    
    /**
    * Resetea los estilos.
    * @param event Capta el evento de Botón Resetear
    */
    @FXML
    private void resetear(ActionEvent event) {
        panelContenedor.getStylesheets().clear();
    }
    
    /**
    * Sale de la App.
    * @param event Capta el evento del Botón Salir
    */
    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }
    
    /**
    * Sale de la App.
    * @param event Capta el evento del item del menú "Acerca de...".
    */
    @FXML
    private void acercaDe(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Aplicación realizada por...");
        alert.setTitle("Acerca de...");
        alert.setContentText("Juan Jimenez Perez.\nVersión 1.0.");
        alert.showAndWait(); 
    }
    
    /**
    * Capta la ruta css, limpia y cambia a la nueva hoja de estilo.
    * @param rutaCSS Ruta de la nueva hoja de estilos.
    */
    private void cambiarEstilo(String rutaCSS){  
    panelContenedor.getStylesheets().clear();
    panelContenedor.getStylesheets().add(rutaCSS);
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
