
package tarea1psphijo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author Juan Jimenez Perez
 */
public class Tarea1PSPHijo {

    public static void main(String[] args) {
        //Creo una entrada de datos para el hijo
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            // Guardo los datos recuperados en un string, los cuales usaré para calcular las tablas.
            String numTabla = reader.readLine();
            
            //Ahora le digo que mientras el texto que recibamos por el flujo de datos no sea "fin" seguirá en bucle.
            while(!numTabla.equals("fin")){
                //Recorremos con el núm que nos haya llegado las tablas multiplicando del 1 al 10.
                for (int i = 1; i <= 10; i++) {
                    //Imprimimos los valores
                    System.out.println(numTabla + "x" + i + " = " + (Integer.parseInt(numTabla) * i));
                }
                //Avisamos de que la tabla ha sido finalizada, esto lo usará el padre para salir del bucle.
                System.out.println("tabla finalizada");
                
                //Volvemos a leer el siguiente num para ver que no sea "fin".
                numTabla = reader.readLine();
               
            }  
           
             
        }
        catch(IOException | NumberFormatException ex){
            System.out.println("ERROR: No se ha podido procesar la tabla");
            
        }
        
        
    
}
    
}

