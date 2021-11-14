
package gestionempleados2122;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Jimenez Perez
 */
public class DBHelper {
    /*Creo una objeto de la clase Connection, Statement y PreparedStatement
    y los especifico como null para poder usarlos en los diferentes métodos.*/ 
    Connection conexion = null;
    Statement sentencia = null;
    PreparedStatement sentenciaPreparada = null;
    
    public DBHelper(){
        /*Con esto hago que directamente se inicie la 
        conexión con la base de datos al crear el objeto*/
        conexionDB();
    }
    
    public void conexionDB(){
        try {
            //Cargo el driver para MySQL
            Class.forName("com.mysql.jdbc.Driver");
            
            //Hago la conexión con la BD.
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/tarea3ad", "root", "");
            
            //Si la conexión fuera un exito mostraría este mensaje.
            System.out.println("INFO: Conexión establecida con la Base de Datos");

            
        } catch (ClassNotFoundException | SQLException ex) {
            //Si fallase estableciendo conexión  o cargando el driver mostraría este mensaje.
            System.out.println("ERROR: Error en la conexión con la Base de Datos.");;
        }
    }
    
    public void DesconexionDB(){
        try {
            //Para cerrar la conexión meteré esto en un método para poder llamarlo luego.
            conexion.close();
            
            //Y si se cerró correctamente enseñará por consola este mensaje.
            System.out.println("INFO: Conexión se cerró correctamente.");
            
        } catch (SQLException ex) {
            //Si fallase cerrando la conexión mostraría este mensaje.
            System.out.println("ERROR: Error en la conexión con la Base de Datos.");
        }
    }
    
    public void estadoAutoCommit(){
        try {
            //Comprobacion de que esta en autocommit
            if(!conexion.getAutoCommit()){ 
                //Muestra un mensaje en el caso de que el AutoCommit esté desactivado.
                System.out.println("INFO: Modo Autocommit Desactivado, se procedera a activar el Modo Autocommit.");
                //Si estuviera desactivado esta sentencia lo activaría.
                conexion.setAutoCommit(true);
                //Mensaje avisando de que ya está activado.
                System.out.println("INFO: Modo Autocommit activado correctamente.");
            }
            else{
                //En el caso de que ya lo esté, mostraría este mensaje.
                System.out.println("INFO: La base de datos está en modo AUTOCOMMIT.");
            }
            
        } catch (SQLException ex) {
            //Si no lograse revisarlo por algún error, o si no lograse llevarlo a cabo daría este error.
            System.out.println("ERROR: Error revisando el Modo Autocommit.");
        }
    }
    
    public void crearTabla(){
        try{
        
           //Creo un objeto de la clase Statement que usaré para crear la tabla.
           sentencia = conexion.createStatement();
           
           //Lanzo la sentencia para crear la tabla Empleados.
            sentencia.executeUpdate("CREATE TABLE Empleados (" + 
            "codEmpleado INT PRIMARY KEY," + 
            "nombre VARCHAR(60)," +
            "apellidos VARCHAR(120)," +
            "puesto VARCHAR(50)," +
            "salario FLOAT (6,2))"); 
            
            //Muestro mensaje para avisar que se ha podido crear la tabla bien.
            System.out.println("INFO: La tabla Empleados se ha creado correctamente." );
            
            //Y por último libero los recursos 
            sentencia.close(); 
            
        } catch (SQLException ex) {
            //En el caso de no lograrlo daría este error.
            System.out.println("ERROR: Error creando la tabla Empleados." );
        }
    }
    
    public void insertarFilas(){
        try {
            
            //Creo un PreparedStatement con la sentencia sql con placeholders 
            sentenciaPreparada = conexion.prepareStatement("INSERT INTO Empleados VALUES (?,?,?,?,?)"); 
            
            //Creo una matriz bidimensional con todos los datos que meteré en al tabla.
            String [][] datos = 
            {{"1", "Juan", "Jimenez Perez", "Desarrollador", "1750"},
             {"2", "Manuel", "Martínez", "Contable", "1200"},
             {"3", "Silvia", "Marzo", "Desarrollador", "1600"},
             {"4", "Miguel", "Cerezo", "Analista", "1500"},
             {"5", "Sara", "Toro", "Gerente", "1800"}};        
            
            //Inicio una variable para usarla como contador de las Filas Insertadas.
            int filas = 0; 
            
            //Recorro con un for todos los datos y los voy añadiendo a la sentencia con los placeholders.
            for (int i = 0; i < datos.length; i++) {
                //A Cada valor le asignamos un placeholder con su indice correspondiente 
                sentenciaPreparada.setInt(1, Integer.parseInt(datos[i][0])); 
                sentenciaPreparada.setString(2, datos[i][1]); 
                sentenciaPreparada.setString(3, datos[i][2]);
                sentenciaPreparada.setString(4, datos[i][3]);
                sentenciaPreparada.setDouble(5, Double.parseDouble(datos[i][4]));
                
                //Una vez añadidos ejecuto la sentencia y recupero el número de filas añadidas.
                filas = filas + sentenciaPreparada.executeUpdate();
            }
            
            /*Una vez ejecutadas todas las sentencias mostraré un 
            mensaje en consola explicando el número de filas insertadas*/
            System.out.println("INFO: Insertadas " + filas + " filas.");
            
            //Liberamos recursos.
            sentenciaPreparada.close();
        } catch (SQLException ex) {
            //En el caso de no lograr insertar los datos, dará este mensaje de error.
            System.out.println("ERROR: No se ha podido insertar datos." );
        }
    }     
    
    public void consultarTabla(){
        try {
            //Creo un objeto de la clase Statement que usaré para realizar la consulta
            sentencia = conexion.createStatement();
            
            //Ejecuto la sentencia SELECT y la almaceno en un ResultSet
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM Empleados WHERE salario > 1500;"); 

            //Muestro una cabecera tabulada con los nombres de cada columna. 
             System.out.println("codEmpleado\tnombre\t\tapellidos\t\tpuesto\t\tsalario");
            
            /*Con while recupero todos los datos del ResultSet
            y los voy imprimiendo por consola*/
            while(resultado.next()){ 
                System.out.println(resultado.getInt(1)+"\t\t" 
                +resultado.getString(2)+"\t\t" 
                +resultado.getString(3)+"\t\t"
                +resultado.getString(4)+"\t\t"
                +resultado.getDouble(5)); 
            } 
            
            //Por último, libero los recursos.
            resultado.close(); 
            sentencia.close();
        } catch (SQLException ex) {
            /*En caso de error al recuperar los datos de la consulta
            se mostraría este mensaje*/
            System.out.println("ERROR: Error realizando la consulta." );
        }
         
    }
}
