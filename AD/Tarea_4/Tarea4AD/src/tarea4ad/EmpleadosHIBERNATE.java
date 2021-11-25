/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea4ad;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author FOC
 */
public class EmpleadosHIBERNATE {
    /*Creo objetos de las siguientes clases que usaremos para
    realizar todo el proceso de inserción, actualización y consulta*/
    SessionFactory sesionGeneral;
    Session sesion;
    Transaction tx; 
    
    /*Creo el método encargado de crear una nueva
    Sesión para la base de datos*/
    public void nuevaSesion(){
        //Creo un sesionFactory
        sesionGeneral = SessionFactorySingleton.getSessionFactory();
        
        //Abro una sesión
        sesion = sesionGeneral.openSession();

    }
    
    //Creo el método encargado de cerrar sesión y liberar memoria.
    public void cerrarSesion(){
        //Libero Memoria
        sesion.close();
        sesionGeneral.close();
    }
    
    /*Método encargado de crear un nuevo empleado con
    las especificaciones que se pedia en la tarea*/    
    public void insertarEmpleado(){
        try {
            //Asocio una transacción a la sesión.
            tx = sesion.beginTransaction();
            
            /*Creo un objeto Date para almacenar la fecha, la cual se
            la he pasado en milisegundos.*/
            Date hiredate = new Date();
            hiredate.setTime(1143154800000L);
            
            //Creo un objeto Employees con los datos que se piden en la tarea.
            Employees emp = new Employees(207, "Juan", "Jimenez Perez", "jujipe@gmail.com", "12345678", hiredate ,
                                          "SA_REP", new BigDecimal(15000), new BigDecimal(0.25), Short.parseShort("80"));
            
            //Guardamos el objeto en la bd.
            sesion.save(emp);
            
            //Hacemos los cambios permanentes con commit.
            tx.commit();
            
            //Mostramos mensaje avisando de que ha funcionado.
            System.out.println("INFO: Insertado en la BD con exito.");
        } catch (Exception e) {
            //En caso de error volvemos atrás con un rollback.
            tx.rollback();
            System.out.println("ERROR: Ha fallado al insertar datos en la Base de datos");
        }
    }
    /*Método encargado de consultar los empleados de la bd que cumplan
    la condición de que el salario sea mayor a 20000*/
    public void consultarEmpleado(){
        //Pido el empleado que quiero actualizar.
        Query q = sesion.createQuery("from Employees WHERE salary > 20000");
        
        //Pongo un try catch para que en caso de error vuelva al estado inicial.
        try {
            //Creo un list para almacenar lo que se ha recuperado.
            List<Employees> lista = q.list();
            //Creo la cabecera que se mostrará.
            System.out.println("Nombre\t\tApellido\t\tSalario");
            //Recorro ese listado
            for(Employees emp:lista){
                //Y muestro los resultados usando un println.
                System.out.println(emp.getFirstName() + "\t\t" + emp.getLastName() + "\t\t" + emp.getSalary());
            }
           
        } catch (Exception e) {
            System.out.println("ERROR: Fallo al realizar la consulta de datos de la base de datos.");
        }
    }
    //Método encargado de actualizar el salario del empleado creado anteriormente.
    public void actualizarEmpleado(){
        
        //Asocio una transacción a la sesión.
        tx = sesion.beginTransaction();
        
        //Pido el empleado que quiero actualizar.
        Query q = sesion.createQuery("from Employees WHERE id = 207");
        
        //Pongo un try catch para que en caso de error vuelva al estado inicial.
        try {
            //Creo un list para almacenar lo que se ha recuperado.
            List<Employees> lista = q.list();
            
            //Recorro ese listado
            for(Employees emp:lista){
                //Y le cambio el salario a la fila que encontro.
                emp.setSalary(new BigDecimal(30000));
                //Y actualizo la bd.
                sesion.update(emp);
            }
            
            //Y hacemos permanentes los cambios con commit.
            tx.commit();
            System.out.println("INFO: Actualizado en la BD con exito.");
           
        } catch (Exception e) {
            //En caso de fallo, vuelvo atrás con rollback.
            System.out.println("ERROR: No se ha podido actualizar los datos de la Base de Datos.");
            tx.rollback();
        }
    }
}
    
    

