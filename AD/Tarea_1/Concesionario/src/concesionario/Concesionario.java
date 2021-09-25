/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionario;

import java.util.Scanner;

/**
 *
 * @author joan-
 */
public class Concesionario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int inputNumCoches = 0;
        
        
        Coche c = new Coche();
        
        System.out.println("Introduce el número de coches que quieres recibi:");
        
        
        try{
            inputNumCoches = Integer.parseInt(entrada.nextLine());
            
            for(int i = 0; i < inputNumCoches; i++){
                System.out.println("Introduce la Marca del Coche " + (i+1));
                c.setMarca(entrada.nextLine());
                
                System.out.println("Introduce el Modelo del Coche " + (i+1));
                c.setModelo(entrada.nextLine());
                
                System.out.println("Introduce el Año del Coche " + (i+1));
                c.setAño(Integer.parseInt(entrada.nextLine()));
                
                System.out.println("Introduce la Matricula del Coche " + (i+1));
                c.setMatricula(entrada.nextLine());
            }
        }
        catch(NumberFormatException ex){
            System.out.println("El valor introducido es incorrecto, vuelve a intentarlo.");
        }
        
                
    }
    
}
