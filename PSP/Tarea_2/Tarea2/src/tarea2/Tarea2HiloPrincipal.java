/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author juanj
 */
public class Tarea2HiloPrincipal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File path = new File("tablasMultiplicar.txt");
        
        if(!path.exists()){
            try{
                path.createNewFile();
            }
            catch(IOException ex){
                System.out.println("ERROR: Fallo al crear el fichero.");
            }
        }
        
        FileWriter fw = null;
        
        try{
            fw = new FileWriter(path);
            
            fw.write("******************************************\n");
            fw.write("*        PSP - Tarea Individual 2        *\n");
            fw.write("******************************************\n");
            fw.write("*           Juan Jimenez Perez           *\n");
            fw.write("******************************************\n");
            fw.write("*                47619383E               *\n");
        
        }
        
        catch(IOException ex){
            System.out.println("ERROR: Fallo escribiendo en el archivo.");
        }
        
        fw.close();
        
    }
    
    
}
