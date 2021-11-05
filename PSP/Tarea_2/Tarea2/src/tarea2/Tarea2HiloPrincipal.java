
package tarea2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase principal del proyecto Tarea2
 * @author Juan Jimenez Perez
 */
public class Tarea2HiloPrincipal {
    
    //Creo el arraylist donde almacenaré todos los hilos.
    ArrayList<Tablas> HilosTablas = new ArrayList<Tablas>();
    
    /**
     * Método main de la clase.
     */
    public static void main(String[] args){
        //Creo un objeto de la clase Tarea2HiloPrincipal.
        Tarea2HiloPrincipal programa = new Tarea2HiloPrincipal();
        
        //Llamo al método iniciar.
        programa.iniciar();
    }
    
    /**
     * Método que se encarga de iniciar todos los hilos según la 
     * prioridad que se especifica en la tarea.
     */
    public void iniciar(){
        //Creo un for para crear 10 hilos cada uno con una tabla del 1 al 10.
        for (int i = 1; i <= 10; i++) {
            //Creo hilos para cada tabla
            
            //Si tocan estas tablas, creará un hilo y con el segundo parámetro se le asigna la prioridad baja.
            if(i == 1 || i == 2 || i == 10 ){
                HilosTablas.add(new Tablas(i, 1));
            }
            //Si tocan estas tablas, creará un hilo y con el segundo parámetro se le asigna la prioridad normal.
            if(i == 3 || i == 4 || i == 5 || i == 6){
                HilosTablas.add(new Tablas(i, 5));
            }
            //Si tocan estas tablas, creará un hilo y con el segundo parámetro se le asigna la prioridad máxima.
            if(i == 7 || i == 8 || i == 9 ){
                HilosTablas.add(new Tablas(i, 10));
            }
        }
        
        //Escribo los datos del alumno.
        infoAlumno();
        
        //Y ahora haciendo uso de los objetos/hilos almacenados en el array los inicio todos.
        for (int i = 0; i < HilosTablas.size(); i++) {
            Tablas hiloTabla = HilosTablas.get(i);
            
            hiloTabla.start();
        }
        
    }
    
    /**
     * Se encarga de escribir en el archivo la información del alumno que
     * se especifica en la tarea.
     */
    public void infoAlumno(){
        //Creo la ruta del archivo.
        File path = new File("tablasMultiplicar.txt");
        
        //Le digo que si no existe ya que me lo cree.
        if(!path.exists()){
            try{
                path.createNewFile();
            }
            catch(IOException ex){
                System.out.println("ERROR: Fallo al crear el fichero.");
            }
        }
        
        //Creo un FileWriter para poder escribir en el fichero.
        FileWriter fw = null;
        
        //Escribo en el fichero los datos que se especificaban en la tarea.
        try{
            fw = new FileWriter(path);
            
            fw.write("******************************************\n");
            fw.write("*        PSP - Tarea Individual 2        *\n");
            fw.write("******************************************\n");
            fw.write("*           Juan Jimenez Perez           *\n");
            fw.write("******************************************\n");
            fw.write("*                47619383E               *\n");
            fw.write("******************************************\n");
            fw.write("Resultado de las operaciones:\n");
            
            //Cierro el flujo de datos.
            fw.close();
        }
        
        catch(IOException ex){
            System.out.println("ERROR: Fallo escribiendo en el archivo.");
        }
    }
    
    
}
