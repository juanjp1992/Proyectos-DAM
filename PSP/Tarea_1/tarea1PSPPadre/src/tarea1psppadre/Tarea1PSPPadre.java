
package tarea1psppadre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 *
 * @author Juan Jimenez Perez
 */
public class Tarea1PSPPadre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try{
            //Creo un proceso hijo a partir del archivo .jar creado a partir del otro proyecto
            Process hijo = new ProcessBuilder("java","-jar","tarea1PSPHijo.jar").start();
            
            //Abro un flujo de entrada hacia el hijo
            BufferedReader entradaHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            //Abro un flujo de salida del hijo, par arecibir los datos
            PrintStream salidaHijo = new PrintStream(hijo.getOutputStream());
           
            //Inicializo la variable resultado, donde almacenaré los datos recibidos del hijo
            String resultado = "";
            
            //Inicio un bucle que usaré para enviar al hijo que queremos las tablas del 1 al 10.
            for (int i = 1; i <= 10; i++) {
                //Envio al hijo el número de la tabla que vamos a querer.
                salidaHijo.println(String.valueOf(i));
                //Obligamos a enviar los datos.
                salidaHijo.flush();
                //Introducimos el titular para saber que tabla será.
                System.out.println("TABLA DEL " + i);
                
                //Empezamos un bucle while que recuperará las tablas, y sabrá el final de cada una usando "tabla finalizada" recibido del hijo.
                while(!(resultado = entradaHijo.readLine()).equals("tabla finalizada")){
                    //Imprimo las tablas
                    System.out.println("- " + resultado);

                }
                //Hago un salto de linea para espaciar las tablas.
                System.out.println("");
            }
            //Finalizo el programa hijo
            salidaHijo.println("fin");
            
            //Imprimo lo que se pide en la tarea
            System.out.println("******************************************\n"
                                + "*        PSP - Tarea Individual 1        *\n"
                                + "******************************************\n"
                                + "*           Juan Jimenez Perez           *\n"
                                + "******************************************\n"
                                + "*                47619383E               *");
                                 
        }
        catch(IOException ex){
            System.out.println("ERROR: No se pudo solicitar una tabla.");
        }
        
    }
    
}
