/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosbdoo;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 * @author Juan Jimenez Perez
 */
public class consultarBDOO {
    public static void main(String[] args) {
        //Abrimos la conexion con la BD.
        ODB odb = ODBFactory.open("gestionLaboral2122.obj");

        //Consultamos todos los Empleados
        IQuery query = new CriteriaQuery(Empleados.class);

        //Los almacenamos en una lista
        Objects<Empleados> lista = odb.getObjects(query);

        //Inicializo un objeto Empleados.
        Empleados emp = null;
        
        //Mientras haya datos recuperados dará vueltas en el bucle
        while(lista.hasNext()){
            
            //Lo añado al objeto Empleados
            emp = lista.next();
            
            //Y muestro los datos por consola.
            System.out.println(
                "Cód. Empleado: " + emp.getCodEmpleado() + 
                " | Nombre: " + emp.getNombre() + 
                " | Apellidos: " + emp.getApellidos() + 
                " | Departamento: " + emp.getDepartamento() + 
                " | Salario: " + emp.getSalario() + " €");
            
        }
        //Liberamos recursos.
        odb.close();
    }
}
