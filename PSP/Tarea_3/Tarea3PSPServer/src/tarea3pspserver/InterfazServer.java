/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea3pspserver;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 * @author Juan Jimenez Perez
 */
public class InterfazServer extends javax.swing.JFrame {
    HiloServidor conexion;
    
    //Constructor llamado al iniciar la app.        
    public InterfazServer() {
        initComponents();
        
        //Iniciamos con ambos botones activos.
        controlBotones(true, true); 
        
        //Inicializo un objeto de la clase HiloServidor.
        conexion = new HiloServidor(logs);
        
        /*Creamos un boolean y un while, los cuales son los encargado
        de pedir al iniciar la app el stock inicial de chirimoyas que
        tendrá la aplicación del Servidor, hasta que no lo escribamos
        no nos dejará avanzar.*/
        boolean stockChirimoyas = true;
        while(stockChirimoyas){
            try {
                //Pregunto por el Stock que tendremos inicialmente.
                conexion.setChirimoyas(
                        Integer.parseInt(
                                JOptionPane.showInputDialog(
                                    this, 
                                    "Introduce un stock inicial de Chirimoyas.", 
                                    "Introduce", 
                                    JOptionPane.INFORMATION_MESSAGE)));

                stockChirimoyas = false;
            //En el caso de no introducir un valor numérico esto daría un error.
            } catch (HeadlessException | NumberFormatException e) {
                JOptionPane.showMessageDialog(
                            this, 
                            "Debes introducir un valor numérico para el Stock de Chirimoyas.", 
                            "Valor Incorrecto", 
                            JOptionPane.ERROR_MESSAGE);
            }
        }
       
        //Imprimo por consola los datos del alumno.
        conexion.datosAlumno();
        
        //Muestro el número de chirimoyas introducidos inicialmente en stock.
        logs.append("+ CHIRIMOYAS EN STOCK: " + conexion.getChirimoyas() + "\n");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        logs = new javax.swing.JTextArea();
        btnServidorEnEscucha = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnStockChirimoyas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chirimoya Store");
        setBackground(new java.awt.Color(255, 153, 51));
        setForeground(new java.awt.Color(255, 153, 51));
        setResizable(false);

        logs.setEditable(false);
        logs.setBackground(new java.awt.Color(204, 204, 204));
        logs.setColumns(20);
        logs.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        logs.setForeground(new java.awt.Color(102, 102, 102));
        logs.setLineWrap(true);
        logs.setRows(5);
        logs.setMargin(new java.awt.Insets(5, 15, 0, 15));
        jScrollPane1.setViewportView(logs);

        btnServidorEnEscucha.setText("Servidor en Escucha");
        btnServidorEnEscucha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ServidorEnEscucha(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("ALMACEN");

        btnStockChirimoyas.setText("Stock Chirimoyas");
        btnStockChirimoyas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarEstadoAlmacen(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
                        .addComponent(btnServidorEnEscucha))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStockChirimoyas)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnServidorEnEscucha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStockChirimoyas)
                .addGap(5, 5, 5))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //Método encargado de poner el servidor en escucha al pulsar el botón.
    private void ServidorEnEscucha(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ServidorEnEscucha
        try{
            //Primero preguntaremos por el puerto por el que escucharemos.
            int puerto = Integer.parseInt(
                                JOptionPane.showInputDialog(
                                    this, 
                                    "Introduce el PUERTO por el cual escuchar...", 
                                    "Elige un puerto", 
                                    JOptionPane.INFORMATION_MESSAGE));
            
            try{
                //Inicializamos un objeto de la clase HiloServidor con el puerto seleccionado.
                conexion = new HiloServidor(puerto, logs);
                
                /*Desactivamos el botón de servidor en escucha 
                y dejamos únicamente el botón de Stock Chirimoyas*/
                controlBotones(false, true);

            }
            catch(Exception e){
                //Mensaje avisando de que ha habido un error al intentar poner el servidor en escucha.
                JOptionPane.showMessageDialog(this, "Error al poner el Servidor en Escucha.", 
                                                "Error", 
                                                JOptionPane.ERROR_MESSAGE);
            }  
        }
        catch(HeadlessException | NumberFormatException e){
            //Error al introducir un valor no numérico para el puerto.
            JOptionPane.showMessageDialog(this, "Valor incorrecto, debes introducir un valor numérico.", 
                                                "Error", 
                                                JOptionPane.ERROR_MESSAGE);
        }           
    }//GEN-LAST:event_ServidorEnEscucha
    //Método que se encarga de consultar el stock actual.
    private void ConsultarEstadoAlmacen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarEstadoAlmacen
        //Si preguntamos pulsando al botón por las chirimoyas en stock mostaría este mensaje.
        logs.append("+ CHIRIMOYAS EN STOCK: " + conexion.getChirimoyas() + "\n");
    }//GEN-LAST:event_ConsultarEstadoAlmacen
    
    //Método encargado de bloquear y desbloquear los botones de la interfaz.
    private void controlBotones(boolean escucha, boolean estado){
        this.btnServidorEnEscucha.setEnabled(escucha);
        this.btnStockChirimoyas.setEnabled(estado);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnServidorEnEscucha;
    private javax.swing.JButton btnStockChirimoyas;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea logs;
    // End of variables declaration//GEN-END:variables
}
