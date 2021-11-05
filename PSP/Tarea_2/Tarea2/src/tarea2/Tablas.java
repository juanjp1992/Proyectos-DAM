
package tarea2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase encargada de la creción de los hilos para generar cada tabla.
 * @author Juan Jimenez Perez
 */
public class Tablas extends Thread {
    //Atributo del número de la tabla
    int numTabla;
    
    //Le añado el constructor.
    public Tablas(int numTabla, int prioridad){
        //Guardo el número pasado al constructor a su variable.
        this.numTabla = numTabla;
        
        //Le asigno una prioridad al hilo
        setPriority(prioridad);
    }
    
    /**
     * Método principal del hilo donde se centra la funcionalidad del hilo.
     */
    public void run(){
     /*Y llamamos a guardarEnFichero y le pasamos el 
     string para que lo escriba en nuestro fichero.*/
        guardarEnArchivo(generarTabla());
    }
    /**
     * Método encargado de generar y devolver todas las tablas.
     * @return Devuelve todas las tablas de Multiplicación.
     */
    public String generarTabla(){
        //Creo la variable tablaCompleta donde se almacenará toda la tabla.
        String tablaCompleta = null;
        
        //Le añado en encabezado.
        tablaCompleta = "\n- Tabla del " + numTabla + "\n";
        
        //Uso un for para añadirle todas las mutiplicaciones.
        for (int i = 1; i <= 10; i++) {
            tablaCompleta = tablaCompleta + "-> " + numTabla + " x " + i + " = "  + (numTabla*i) + "\n";
            
        }
        //Devuelvo el resultado generado.
        return tablaCompleta;
    }
    
    /**
     * Método que se encarga de guardar en el fichero los datos conseguidos,
     * en este caso las tablas generadas.

     * @param tablaCalculada Aquí se recibe ya la tabla formateada y preparada
     * para ser escrita en el fichero.
     */
    public synchronized void guardarEnArchivo(String tablaCalculada){
        //Creo la ruta hasta el fichero.
        File path = new File("tablasMultiplicar.txt");
        //Creo un FileWriter para poder escribir en el fichero.
        FileWriter fw = null;
        
        try{
            //Lo instancio y le asigno la ruta y "true" para que no sobreescriba.
            fw = new FileWriter(path, true);
            
            //Le paso la tabla ya preparada para escribir.
            fw.write(tablaCalculada);
            
            //Cierro el flujo.
            fw.close();
        }
        
        catch(IOException ex){
            System.out.println("ERROR: Fallo escribiendo en el archivo.");
        }
    }
    
}
