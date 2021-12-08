/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea3pspserver;

/**
 *
 * @author juanj
 */
public class Tarea3PSPServer {

    /*Creamos objeto de la clase interfazGrafica como static para poder 
    utilizarlo para que los Popup's sigan nuestra ventana.*/
    static InterfazServer interfaz;
    
    public static void main(String[] args) {
        //Instanciamos el objeto interfazGrafica.
        interfaz = new InterfazServer();
        
        //Hacemos visible la ventana.
        interfaz.setVisible(true);
    }
    
}
