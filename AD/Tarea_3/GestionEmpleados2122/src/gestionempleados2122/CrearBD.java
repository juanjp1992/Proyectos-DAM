
package gestionempleados2122;

/**
 *
 * @author Juan Jimenez Perez
 */
public class CrearBD {
    
    public static void main(String[] args) {
        /*Por no repetir, he añadido la conexión de la base de datos
        al constructor de la clase DBHelper*/
        DBHelper conexion = new DBHelper();
        
        /*Comprobamos si la base de datos está en Modo AutoCommit y si no
        la activamos de manera automática*/
        conexion.estadoAutoCommit();
        
        //Creo la tabla Empleados usando el método "crearTabla()".
        conexion.crearTabla();
        
        //Cerramos usando el objeto conexion de la clase DBHelper.
        conexion.DesconexionDB();
        
    }
    
}
