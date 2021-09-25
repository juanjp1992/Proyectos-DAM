package tarea1di;

/**
 *
 * @author Juan Jimenez Perez
 */
public class Tarea1DI {

    /*Creamos objeto de la clase interfazGrafica como static para poder 
    utilizarlo para que los Popup's sigan nuestra ventana.*/
    static interfazGrafica interfaz;
    
    public static void main(String[] args) {
        
        //Instanciamos el objeto interfazGrafica.
        interfaz = new interfazGrafica();
        
        //Hacemos visible la ventana.
        interfaz.setVisible(true);
    }
    
}
