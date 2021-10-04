/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1psphijo;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 *
 * @author juanj
 */
public class Tarea1PSPHijo {

    public static void main(String[] args) {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String numTabla = reader.readLine();
            while(!numTabla.equals("fin")){
                //Recorremos con el núm que nos haya llegado las tablas multiplicando del 1 al 10.
                for (int i = 1; i <= 10; i++) {
                    //Imprimimos por pantalla los valores
                    System.out.println(numTabla + "x" + i + " = " + (Integer.parseInt(numTabla) * i));
                    
                }
                //volvemos a leer el siguiente num para ver que no sea "fin".
                numTabla = reader.readLine();
               
            }  
           
             
        }
        catch(Exception ex){
            System.out.println("Fallo procesando el dato");
            
        }
        
        
    
}
    
}

