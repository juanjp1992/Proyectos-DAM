/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea3pspserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Juan Jimenez Perez
 */
public final class HiloServidor extends Thread{
   //Diferentes atributos utilizados en la clase.
    private int puerto;
    
    /*Creo un atributo estático para compartir 
    el valor entre los diferentes objetos.*/
    private static int chirimoyas;
    
    private boolean estadoHilo = true;
    
    private javax.swing.JTextArea logs;
    
    /*Creo objetos de la clase ServerSocket y Socket para 
    recibir la conexión y para poder enviar mensajes
    desde servidor.*/
    private ServerSocket servidor;
    private Socket cliente;
    
    //Creo los flujos para enviar y recibir datos.
    private BufferedReader recibir;
    private PrintWriter enviar;
    
    //Creamos un constructor que recoge el JTextArea del interfaz.
    public HiloServidor(javax.swing.JTextArea logs){
        this.logs = logs;
    }
    
    /*Creamos un constructor que recoge el JTextArea 
    del interfaz y además los logs*/
    public HiloServidor(int puerto, javax.swing.JTextArea logs){
        this.puerto = puerto;
        this.logs = logs;
        
        /*Como este constructor se creará al pulsar sobre el botón
        Servidor en espera, automáticamente activará este método para
        quedarse en espera de alguna solicitud de alguna aplicación cliente.*/
        servidorEnEspera();
    }
    
    //Método que se iniciará al activar el hilo servidor.
    @Override
    public void run(){
        /*Primero utilizamos este método que será el encargado de
        aceptar la solicitud de Conexión de la aplicación cliente*/
        aceptandoSolicitudConexion();
        //Creo un bucle que se reproducirá hasta que paremos el hilo.
        while(estadoHilo){
            //Y usaremos este método para recibir peticiones de Cliente.
            recepcionPeticionesCliente();
        }
    }
    
    /*Método encargado de dejar la aplicación servidor en espera
    a que reciba alguna conexión de alguna app cliente*/
    public void servidorEnEspera(){
        try {
            
            //Ponemos a la app servidor a escuchar.
            servidor = new ServerSocket(puerto);
            //Y avisamos de ello en los logs.
            logs.append("+ INFO: Servidor escuchando por el puerto " + puerto + ".\n");
            
             //Iniciamos el hilo.
            start();
        } 
        //En caso de no poder poner a escuchar daría el siguiente error.
        catch (IOException e) {
            logs.append("# ERROR: Error al escuchar el puerto " +  puerto + ".\n");
            //Si fallase, pondríamos a false el estado del  hilo para que se parase.
            estadoHilo = false;
        }
    }
    //Método encargado de aceptar la solicitud de Cliente.
    public void aceptandoSolicitudConexion(){
        try {
            //Aceptamos la solicitud de conexión de cliente.
            cliente = servidor.accept();
            //En el caso de que lo logre mostrará el siguiente mensaje en los logs.
            logs.append("+ INFO: Conexión establecida. " + cliente.toString() + ".\n");
            
            //Inicializamos el BufferedReader y PrintWriter para crear un flujo de datos.
            recibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            enviar = new PrintWriter(cliente.getOutputStream(), true);
            
           
        } 
        
        /*En caso de Error aceptando la solicitud o inicializando
        el BufferedReader y el Printwriter mostraría el siguiente catch.*/
        catch (IOException ex) {
            estadoHilo = false;
            logs.append("# ERROR: Error al aceptar la conexión de Cliente.\n");
        }
            
    }
    //Método encargado de recibir las peticiones de cliente y realizar lo oportuno.
    public void recepcionPeticionesCliente(){
         try {
                //Guardamos los datos recibidos en un string.
                String comando = recibir.readLine();
                
                //Si cliente pide saber el stock haría lo siguiente.
                if(comando.contains("stock")){
                    //enviaría un mensaje ya formateado con las chiromoyas actuales en stock.
                    enviar.println("> CHIRIMOYAS EN STOCK: " + chirimoyas);
                    //Además mostraré un mensaje avisando de que cliente ha pedido esta información.
                    logs.append("> CLIENTE PIDE CHIRIMOYAS EN STOCK: " + chirimoyas + "\n");
                    //Y aseguraré que envíe si aún no se ha llenado el buffer.
                    enviar.flush();
                }
                //Si el cliente pide añadir chirimoyas haría lo siguiente.
                if(comando.contains("añadir")){
                    //Recupero el dato, y me quedo únicamente con el número.
                    int añadir = Integer.parseInt(comando.replace("añadir;", "").trim());
                    
                    //Y se lo sumo a las chirimoyas actuales.
                    chirimoyas = chirimoyas + añadir;
                    
                    //Muestro las chirimoyas que añade cliente.
                    logs.append("> CLIENTE AÑADE: " + añadir + " CHIRIMOYAS\n");
                }
                
                //Si el cliente pide retirar chirimoyas haría lo siguiente.
                if(comando.contains("retirar")){
                    //Recupero la parte del mensaje necesaria, lo paso a número
                    int retirar = Integer.parseInt(comando.replace("retirar;", "").trim());
                    /*Si lo que queremos retirar quita más chirimoyas de las que tenemos,
                    enviaría el siguiente mensaje al cliente*/
                    if((chirimoyas - retirar)<0){
                        logs.append("# ERROR AL RETIRAR " + retirar + " CHIRIMOYAS\n# ACTUALMENTE EN STOCK: " + chirimoyas + "\n");
                        enviar.println("# ERROR AL RETIRAR " + retirar + " CHIRIMOYAS\n# ACTUALMENTE EN STOCK: " + chirimoyas);
                        enviar.flush();
                    }
                    
                    //y si tenemos suficientes chirimoyas haría lo siguiente.
                    else{
                        //Y se lo resto a las chirimoyas actuales.
                        chirimoyas = chirimoyas - retirar;
                        
                        //Muestro lo que cliente retira.
                        logs.append("> CLIENTE RETIRA: " + retirar + " CHIRIMOYAS\n");
                    }
     
                }    
            }
            /*En caso de recibir algún error al recibir la petición del cliente,
            mostraría el siguiente mensaje en los logs.*/
            catch (IOException | NumberFormatException e) {
                logs.append("# ERROR: Fallo al recibir petición de Cliente.\n");
            }
    }
    
    
    
    //Método encargado de mostrar los datos del Alumno.    
    public void datosAlumno(){
        logs.setText("****************************************************\n" +
                     "*  PSP - Tarea Individual 4 - Cliente / Servidor   *\n" +
                     "*****************************************************\n" +
                    "*                Juan Jimenez Perez                *\n" +
                    "****************************************************\n" +
                    "*                    47619383E                     *\n" +
                    "****************************************************\n\n");
    }

    //Getter y Setter de las chirimoyas.
    public int getChirimoyas() {
        return chirimoyas;
    }

    public void setChirimoyas(int chirimoyas) {
        this.chirimoyas = chirimoyas;
    }
    
    

}
