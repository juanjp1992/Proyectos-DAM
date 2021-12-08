/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea3pspclient;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;


/**
 * @author Juan Jimenez Perez
 */
public class InterfazClient extends javax.swing.JFrame {

   /*Declaro un objeto de la clase conexión y el atributo chirimoyas
    que usaré para guardar momentaneamente el dato introducido.*/
    HiloCliente conexion; 
    int chirimoyas;
       
    //Constructor llamado al iniciar la app.
    public InterfazClient() {
        
        initComponents();
        
        /*Dejamos todos los botones inactivos excepto
          el de Conectar al Servidor.*/
        controlBotones(false, false, false, true);
        
        /*Inicializamos el objeto conexion y le pasamos el
        JTextArea encargado de los logs*/
        conexion = new HiloCliente(logs);
        
        /*Y utillizamos el método datosAlumno()
        para escribirlos en el log*/
        conexion.datosAlumno();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        logs = new javax.swing.JTextArea();
        btnConectarServidor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnAñadirChirimoyas = new javax.swing.JButton();
        btnRetirarChirimoyas = new javax.swing.JButton();
        btnStockChirimoyas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

        btnConectarServidor.setText("Conectar al Servidor");
        btnConectarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarServidor(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("CHIRIMOYA STORE");

        btnAñadirChirimoyas.setText("Añadir Chirimoyas");
        btnAñadirChirimoyas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirChirimoyas(evt);
            }
        });

        btnRetirarChirimoyas.setText("Retirar Chirimoyas");
        btnRetirarChirimoyas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetirarChirimoyas(evt);
            }
        });

        btnStockChirimoyas.setText("Stock Chirimoyas");
        btnStockChirimoyas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockChirimoyas(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(btnConectarServidor))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAñadirChirimoyas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRetirarChirimoyas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnStockChirimoyas)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConectarServidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAñadirChirimoyas)
                    .addComponent(btnRetirarChirimoyas)
                    .addComponent(btnStockChirimoyas))
                .addGap(5, 5, 5))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    //Método encargado de la conexión al servidor.
    private void conectarServidor(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarServidor
        
        try{
            //Capturamos la ip y el puerto con un JOptionPane.
            String ip = JOptionPane.showInputDialog(
                    this, 
                    "Introduce la IP.", 
                    "Conexión al Servidor", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            String puerto = JOptionPane.showInputDialog(
                    this, 
                    "Introduce el PUERTO.", 
                    "Conexión al Servidor", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            //Realizamos la conexión y recuperamos su estado en un boolean.
            boolean estadoConexion = conexion.conexionServer(ip, Integer.parseInt(puerto));
            
            /*Si se ha conectado correctamente, esto desbloqueará
            los botones Añadir Chirimoyas, Retirar Chirimoyas
            y Stock Chirimoyas.*/
            if(estadoConexion){
                controlBotones(true, true, true, false);
            }
        }
        //En caso de introducir NO numéricos en el puerto saltará el catch, y avisará de ello.
        catch(HeadlessException | NumberFormatException e){
            JOptionPane.showMessageDialog(
                    this, 
                    "Puerto incorrecto, recuerda que debes introducir valores numéricos", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_conectarServidor
    //Método encargado de captar el evento y de añadir Chirimoyas
    private void btnAñadirChirimoyas(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirChirimoyas
        
        try{
            //Recupero el valor con un JOptionPane y lo transformo en un int.
            chirimoyas = Integer.parseInt(JOptionPane.showInputDialog(
                this, 
                "Introduce el número de chirimoyas a AÑADIR.", 
                "Control de Stock", 
                JOptionPane.INFORMATION_MESSAGE));
            
            /*Enviamos una petición al servidor, 
            le enviamos la orden y que hacer con el valor*/
            conexion.enviarPeticionServer("añadir", String.valueOf(chirimoyas));
        }
        //En el caso de no introducir un valor numérico daría error y se avisaría.
        catch(HeadlessException | NumberFormatException e){
            
            JOptionPane.showMessageDialog(
                    this, 
                    "Recuerda que debes introducir valores numéricos.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAñadirChirimoyas
    //Método encargado de captar el evento y de retirar Chirimoyas
    private void btnRetirarChirimoyas(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetirarChirimoyas
        try{
            //Recupero el valor con un JOptionPane y lo transformo en un int.
            chirimoyas = Integer.parseInt(JOptionPane.showInputDialog(
                    this, 
                    "Introduce el número de chirimoyas a RETIRAR.", 
                    "Control de Stock", 
                    JOptionPane.INFORMATION_MESSAGE));
            
            /*Enviamos una petición al servidor, 
            le enviamos la orden y que hacer con el valor*/
            conexion.enviarPeticionServer("retirar", String.valueOf(chirimoyas));
        }
        //En el caso de no introducir un valor numérico daría error y se avisaría.
        catch(HeadlessException | NumberFormatException e){
            JOptionPane.showMessageDialog(
                    this,
                    "Recuerda que debes introducir valores numéricos.",
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRetirarChirimoyas
    
    //Método encargado de captar el evento y de pedir el Stock Chirimoyas
    private void stockChirimoyas(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockChirimoyas
        /*Enviamos una petición al servidor, 
        le pedimos que nos informe del stock.*/
        conexion.enviarPeticionServer("stock", "");
    }//GEN-LAST:event_stockChirimoyas
    
    //Método encargado de activar y desactivar los botones.
    private void controlBotones(boolean añadir, boolean retirar, 
            boolean stock, boolean conectar){
        this.btnAñadirChirimoyas.setEnabled(añadir);
        this.btnRetirarChirimoyas.setEnabled(retirar);
        this.btnStockChirimoyas.setEnabled(stock);
        this.btnConectarServidor.setEnabled(conectar);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAñadirChirimoyas;
    private javax.swing.JButton btnConectarServidor;
    private javax.swing.JButton btnRetirarChirimoyas;
    private javax.swing.JButton btnStockChirimoyas;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea logs;
    // End of variables declaration//GEN-END:variables
}
