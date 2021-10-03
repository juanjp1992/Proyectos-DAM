/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea1psppadre;

import java.io.BufferedReader;
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
            String line;
            String cadena = reader.readLine();
            
            while(!cadena.equals("fin")){
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Entra en el for");
                    salidaHijo.println("3");
                    salidaHijo.flush();
                    System.out.println("hace flush");
                    
                    line = entradaHijo.readLine();
                    while(line.equals("-1")){
                        System.out.println(line);
                        line = entradaHijo.readLine();
                    }
                   
                    
                   
                    
                    System.out.println("PRUEBA INTERNA");
                    
                    cadena = reader.readLine();
                }
                salidaHijo.print("fin");
            }
            salidaHijo.print("fin");
        }
        catch(Exception ex){
            
        }
        System.out.println("salio");
    }
    
}
