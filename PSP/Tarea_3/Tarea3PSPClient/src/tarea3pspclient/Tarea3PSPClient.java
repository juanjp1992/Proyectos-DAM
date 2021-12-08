/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea3pspclient;

/**
 *
 * @author juanj
 */
public class Tarea3PSPClient {

    /*Creamos objeto de la clase interfazGrafica como static para poder 
    utilizarlo para que los Popup's sigan nuestra ventana.*/
    static InterfazClient interfaz;
    
    public static void main(String[] args) {
        //Instanciamos el objeto interfazGrafica.
        interfaz = new InterfazClient();
        
        //Hacemos visible la ventana.
        interfaz.setVisible(true);
    }
    
}
