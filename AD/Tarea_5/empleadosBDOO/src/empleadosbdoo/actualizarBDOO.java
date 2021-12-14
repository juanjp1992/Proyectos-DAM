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
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 * @author Juan Jimenez Perez
 */
public class actualizarBDOO {
    public static void main(String[] args) {
        //Abrimos la conexion con la BD.
        ODB odb = ODBFactory.open("gestionLaboral2122.obj");

        //Ponemos una condición a la consulta.
        ICriterion condicion = Where.lt("salario", 1600);
        
        //Y se lo pasamos a un objeto IQuery.
        IQuery query = new CriteriaQuery(Empleados.class, condicion);

        //Los almacenamos en una lista
        Objects<Empleados> lista = odb.getObjects(query);

        //Inicializo un objeto Empleados.
        Empleados emp = null;

            //Mientras haya datos recuperados dará vueltas en el bucle
            while(lista.hasNext()){

                //Lo añado al objeto Empleados
                emp = lista.next();
                
                //Recuperamos el salario y le añadimos 200.
                emp.setSalario(emp.getSalario() + 200);
                
                //Y guardo de nuevo el objeto.
                odb.store(emp);
            }
            
            //Libreramos recursos.
            odb.close();
    }
}
