/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

import java.util.Random;

/**
 *
 * @author juanj
 */
public class JavaApplication9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random();
        
        String valores = "";
        
        
        
        for (int i = 0; i < 10; i++) {
            valores = "";
            for (int j = 0; j < 8; j++) {
                valores = valores + String.valueOf(r.nextInt(10));
            }
            System.out.println(valores);
        }
    }
    
}
