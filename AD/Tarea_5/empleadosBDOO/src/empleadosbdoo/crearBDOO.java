/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosbdoo;


import java.util.ArrayList;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

/**
 *
 * @author Juan Jimenez Perez
 */
public class crearBDOO {

    public static void main(String[] args) {
        //Abrimos la conexion y automáticamente nos creará el archivo si no existe.
        ODB odb = ODBFactory.open("gestionLaboral2122.obj");
        
        //Creo un arraylist de objetos
        ArrayList<Empleados> lista = new ArrayList<Empleados>();
        
        //Creo objetos con cada empleado y sus datos.
        Empleados emp1 = new Empleados(1, "Juan", "Jimenez Perez", "Desarrollador", 1750);
        Empleados emp2 = new Empleados(2, "Manuel", "Martínez", "Contable", 1200);
        Empleados emp3 = new Empleados(3, "Silvia", "Marzo", "Desarrollador", 1600);
        Empleados emp4 = new Empleados(4, "Miguel", "Cerezo", "Analista", 1500);
        Empleados emp5 = new Empleados(5, "Sara", "Toro", "Gerente", 1800);
        
        //Los añado al arraylist.
        lista.add(emp1);
        lista.add(emp2);
        lista.add(emp3);
        lista.add(emp4);
        lista.add(emp5);
        
        //Escribimos los datos en la BD.
        for (Empleados emp: lista) {
            odb.store(emp);
        }
        
        //Liberamos recursos
        odb.close();
    }
    
}
