/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularioDatos;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juanj
 */
public class FormularioDatosControllerTest {
    
    /**
     * Test of supCircunferencia method, of class FormularioDatosController.
     */
    @Test
    public void testSupCircunferencia() {
        System.out.println("supCircunferencia");
        double r = 5.0;
        double expResult = 78.53981633974483;
        double result = FormularioDatosController.supCircunferencia(r);
        System.out.println(result);
        assertEquals(expResult, result, 0.0);
        
    }

    
    
}
