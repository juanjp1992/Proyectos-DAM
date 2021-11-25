/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea4ad;

/**
 *
 * @author FOC
 */
public class Tarea4AD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*Creo un objeto de la clase EmpleadosHIBERNATE 
        para hacer los diferentes pasos de la tarea*/
        EmpleadosHIBERNATE emp = new EmpleadosHIBERNATE();
        
        //Primero creo una nueva sesion
        emp.nuevaSesion();
        
        //Después inserto el empleado que se especificaba en la tarea
        emp.insertarEmpleado();
        
        //Seguidamente actualizo el elmpleado con el nuevo salario.
        emp.actualizarEmpleado();
        
        //Recupero los resultados de los empleados con el sueldo
        emp.consultarEmpleado();
        
        //Por último cierro la sesión.
        emp.cerrarSesion();

    }
    
}
