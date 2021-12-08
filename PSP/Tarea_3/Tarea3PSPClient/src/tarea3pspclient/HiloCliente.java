/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea3pspclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Juan Jimenez Perez
 */
public class HiloCliente extends Thread{
    //ATRIBUTOS
    //Atributos utilizados en la clase.
    private boolean estadoHilo = true;
    private javax.swing.JTextArea logs;
    
    //Declaramos el socket
    Socket cliente;
    
    /*Declaramos un BufferedReader y PrintWriter 
    para enviar y recibir datos.*/
    BufferedReader recibir;
    PrintWriter enviar;
    
    //CONSTRUCTOR
    /*Declaramos el constructor que se traerá
    el JTextArea de la interfaz para utilizarlo*/
    public HiloCliente(javax.swing.JTextArea logs){
        this.logs = logs;
    }
    
    //Método que se iniciará al activar el Hilo.
    @Override
    public void run(){
        //Mientras no se desactive el hilo seguirá en el while.
        while(estadoHilo){
            //Método encargado de recibir mensajes de servidor.
            recepcionMsgServidor();
        }
    }
    
    //Método encargado de recuperar la ip y el puerto y conectarse al servidor.
    public boolean conexionServer(String ip, int puerto){
        //Creo booleano para detectar desde InterfazClient.java si funciono o no.
        boolean estadoConexion = true;
        try {
            //Mostramos mensaje de que estamos iniciando la conexión.
            logs.append("+ INFO: Iniciando conexión con " + ip + ":" + puerto + "\n");
            
            //Intentamos realizar la conexión.
            cliente = new Socket(ip, puerto);
            
            //Creamos los flujos de entrada y salida de datos.
            recibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            enviar = new PrintWriter(cliente.getOutputStream(), true);
            
            //Si lo consigue mostrará que se ha establecido conexión
            logs.append("+ INFO: Se ha establecido conexión con " + ip + ":" + puerto + "\n");
        
        } catch (IOException e) {
            //Si diera error al intentar establecer conexión.
            logs.append("# ERROR: Error al establecer conexión con " + ip + ":" + puerto + "\n");
        
            //Si no lo lograse lo pondría el hilo a "false" para que no se arrancase el hilo.
            estadoHilo = false;
            
            //El estado de la conexión es false al no haberse conectado con exito.
            estadoConexion = false;
        }
        
        //Arrancamos el hilo
        start();
        
        //Devolvemos el estado de la conexión.
        return estadoConexion;
    }
    
    /*Método encargado de recibir los datos y el tipo
    de petición que queremos pedirle al servidor.*/
    public void enviarPeticionServer(String orden, String msg){
        //Si buscamos añadir chirimoyas.
        if(orden.equals("añadir")){
            enviar.println(orden + ";" + msg);
            logs.append("> PETICIÓN A SERVER: AÑADE " + msg + " CHIRIMOYAS\n");
        }
        
        //Si buscamos retirar chirimoyas.
        if(orden.equals("retirar")){
            enviar.println(orden + ";" + msg);
            logs.append("> PETICIÓN A SERVER: RETIRAR " + msg + " CHIRIMOYAS\n");
        }
        
        //Si queremos pedirle el stock actual.
        if(orden.equals("stock")){
            enviar.println(orden);
             logs.append("> PETICIÓN A SERVER: CHIRIMOYAS EN STOCK\n");
        }
        
        //Nos aseguramos de enviar la petición.
        enviar.flush();
    }
    
    //En el caso de recibir mensajes de servidor se usaría este método.
    public void recepcionMsgServidor(){
        try {
            //Guardamos el dato en un string.
            String comando = recibir.readLine();
            
            //Y directamente lo escribimos en los logs.
            logs.append(comando + "\n");
        
        //Si tuvieramos algún error al recibir datos nos mostaría este mensaje.
        } catch (IOException e) {
            logs.append("# ERROR: Fallo al recibir mensaje del Servidor.\n");
        }
    }
    
    
    //Método encargado de mostrar los datos del Alumno
    public void datosAlumno(){
        logs.setText("******************************************************\n" +
                    "*   PSP - Tarea Individual 4 - Cliente / Servidor    *\n" +
                    "******************************************************\n" +
                    "*                 Juan Jimenez Perez                 *\n" +
                    "******************************************************\n" +
                    "*                     47619383E                      *\n" +
                    "******************************************************\n\n");
    }
    
    
}

