/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1psppadre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author juanj
 */
public class Tarea1PSPPadre {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Process hijo = null;
        
        try{
            hijo = new ProcessBuilder("java","-jar","hijo.jar").start();
            System.out.println("Se ha creado el proceso hijo");
            
            BufferedReader entradaHijo = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
            PrintStream salidaHijo = new PrintStream(hijo.getOutputStream());
           
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("PRUEBAAAAA");
            String line = "inicio";
            String cadena = "inicio";
            int num = 1;
            for (int i = 1; i <= 10; i++) {
               salidaHijo.println(i);
                while(!line.equals("")){
                        salidaHijo.flush();
                        System.out.println(line);
                        line = entradaHijo.readLine();

                } 
                line = entradaHijo.readLine();
            }
            
            
            /*
            while(!cadena.equals("fin")){

                for (num = 1; num <= 10; num++) {
                    salidaHijo.println(String.valueOf(num));
                    
 
                
          
                while(!line.equals("")){
                    salidaHijo.flush();
                    System.out.println(line);
                    line = entradaHijo.readLine();

                }
                if(num == 10){
                    salidaHijo.print("fin");
                    cadena = "fin";

                }
                }
            }*/
        }
        catch(IOException ex){
            
        }
        
        System.out.println("salio");
    }
    
}
