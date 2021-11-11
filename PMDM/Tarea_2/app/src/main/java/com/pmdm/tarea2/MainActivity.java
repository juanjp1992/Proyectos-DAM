package com.pmdm.tarea2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Inicializo los componentes con los que trabajaré
    EditText txtMensaje = null;
    EditText txtTelefono = null;
    TextView txtDatosAlumno = null;
    Button btnEstadoConexion = null, btnEnviarSMS = null;
    WebView inputWeb = null;

    //Creo una constante estática para poder utilizarlo al pedir permisos.
    static final int ID_PETICION_PERMISO_SEND_SMS = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Utilizando el método findViewById los busco y me los traigo para poder usarlos.
        txtDatosAlumno = findViewById(R.id.txtDatosAlumno);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnEstadoConexion = findViewById(R.id.btnEstadoConexion);
        btnEnviarSMS = findViewById(R.id.btnEnviarSMS);
        inputWeb = findViewById(R.id.inputWeb);

        //Enlazo los botones con el OnClickListener.
        btnEstadoConexion.setOnClickListener(this);
        btnEnviarSMS.setOnClickListener(this);

        //Escribo y Cargo los datos desde SharedPreferences.
        escrituraLecturaSPref("Juan", "Jimenez Perez", "47619383E");

        //Cargo la página web
        cargarWeb("https://www.foc.es");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //En el caso de pulsar el botón btnEstadoConexión.
            case R.id.btnEstadoConexion:
                //Compruebo el estado de la conexión usando este método.
                estadoConexion(this);
                break;
            //En el caso de pulsar en el botón btnEnviarSMS.
            case R.id.btnEnviarSMS:
                if(!txtTelefono.getText().toString().isEmpty() && !txtMensaje.getText().toString().isEmpty()){
                    try {
                        //Comprueba que sea un valor numérico
                         int comprobacionNum = Integer.parseInt(txtTelefono.getText().toString());

                        //En el caso de serlo, envia el SMS.
                        enviarSMS(txtTelefono.getText().toString(), txtMensaje.getText().toString());

                    }
                    //En el caso de que no sea un valor mostrará el toast.
                    catch(NumberFormatException ex){
                        Toast.makeText(this, "El número de teléfono, debe ser tipo numérico.", Toast.LENGTH_LONG).show();
                    }
                }
                //En el caso de tener los campos vacios aparecerán estos mensajes.
                else{
                    //Si es el txtTelefono de teléfono devolverá este mensaje.
                    if(txtTelefono.getText().toString().isEmpty()){
                        Toast.makeText(this, "No olvides el número de Teléfono.", Toast.LENGTH_LONG).show();
                    }
                    //Si es el txtMensaje devolverá este mensaje.
                    if(txtMensaje.getText().toString().isEmpty()){
                        Toast.makeText(this, "No olvides escribir tu mensaje.", Toast.LENGTH_LONG).show();
                    }
                    //Si ambos campos están vacios devolverá este mensaje.
                    if(txtTelefono.getText().toString().isEmpty() && txtMensaje.getText().toString().isEmpty()){
                        Toast.makeText(this, "No olvides rellenar los campos", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    //Método que se encarga de guardar los datos en el sharedPreferences
    // para después recuperar los datos y escribir el el TextView
    public void escrituraLecturaSPref(String nombre, String apellidos, String DNI){
        //Abrimos las SharedPreferences
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        //Escribimos los datos en el SharedPreferences.
        edit.putString("Nombre", nombre);
        edit.putString("Apellidos", apellidos);
        edit.putString("DNI", DNI);

        //Hacemos los cambios permanentes
        edit.commit();

        //Los recupero de nuevo del SharedPreferences y los añado al TextView "txtDatosAlumno"
        txtDatosAlumno.setText(sp.getString("Nombre", "") + " " +
                               sp.getString("Apellidos", "") + " " +
                               sp.getString("DNI", ""));
    }

    //Método creado para cargar la página web.
    public void cargarWeb(String web){
        /*Marcamos el cliente web que usará, para que no
         use el por defecto del dispositivo*/
        inputWeb.setWebViewClient(new WebViewClient());

        //Cargamos la url de la web.
        inputWeb.loadUrl(web);
    }

    //Método para comprobar el estado de la conexión del dispositivo
    public static void estadoConexion(Context context){
        //Llamamos al gestor de conectividad. ConnectivyManager
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if(connectivityManager != null){
            //Pedimos la red actual y le damos permiso en el AndroidManifest.xml
            Network redActual = connectivityManager.getActiveNetwork();
            //Si la red actual no es nula entraría aquí.
            if(redActual != null){
                //Obtenemos las propiedades de la red.
                NetworkCapabilities propiedadesRed = connectivityManager.getNetworkCapabilities(redActual);

                if(propiedadesRed != null){
                    //Preguntamos si está conectado al WIFI.
                    if(propiedadesRed.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                        Toast.makeText(context, "Hay conectividad Wifi", Toast.LENGTH_LONG).show();
                    }

                    //Preguntamos si está conectado a los datos móviles.
                    if(propiedadesRed.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                        Toast.makeText(context, "Hay conectividad por Datos Móviles", Toast.LENGTH_LONG).show();
                    }

                    //Preguntamos si está conectado por cable de red.
                    if(propiedadesRed.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                        Toast.makeText(context, "Hay conectividad por cable de red", Toast.LENGTH_LONG).show();
                    }
                }
            }
            //Si no hay conectividad, enseñaría este toast.
            else{
                Toast.makeText(context, "No hay conectividad", Toast.LENGTH_LONG).show();
            }

        }

    }

    //Método para enviar SMS
    public void enviarSMS(String numTelf, String msg) {

        //Comprobamos si tenemos permisos para enviar SMS.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {

            //Instanciamos SMSManager
            SmsManager smsManager = SmsManager.getDefault();
            try {
                //Comprobamos si el msg es mayor de 140 caracteres.
                if (msg.length() > 140) {
                    //Dividimos el mensaje si así fuera, y lo metemos en un ArrayList.
                    ArrayList<String> listaMsg = smsManager.divideMessage(msg);
                    //Ahora recorremos el arraylist y enviamos tantos mensajes como fragmentos de texto haya.
                    for (String fragmentoTexto : listaMsg) {
                        smsManager.sendTextMessage(numTelf, null, fragmentoTexto, null, null);
                    }
                    //Mostramos mensaje de que se ha enviado correctamente.
                    Toast.makeText(this, "Se ha enviado correctamente.", Toast.LENGTH_LONG).show();

                    //Una vez acabe, limpiamos los campos.
                    txtTelefono.setText("");
                    txtMensaje.setText("");
                } else {

                        //Envio el mensaje.
                        smsManager.sendTextMessage(numTelf, null, msg, null, null);
                        //Una vez acabe, limpiamos los campos.
                        txtTelefono.setText("");
                        txtMensaje.setText("");
                        //Muestro mensaje de que se ha enviado correctamente.
                        Toast.makeText(this, "Se ha enviado correctamente.", Toast.LENGTH_LONG).show();

                }
            }
            catch (Exception ex) {
                    //En el caso de fallar enviaría este mensaje.
                    Toast.makeText(this, "No se ha podido enviar el sms.", Toast.LENGTH_LONG).show();
            }
        }
        //Si no tuvieramos permisos hacemos la solicitud.
        else{
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.SEND_SMS}, ID_PETICION_PERMISO_SEND_SMS);

        }
    }

    //Comprobamos las peticiones.
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permission, grantResult);

        switch (requestCode){
            case ID_PETICION_PERMISO_SEND_SMS:
                if(grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    //Envio los datos para enviar un sms.
                    enviarSMS(txtTelefono.getText().toString(), txtMensaje.getText().toString());
                }
                else{
                    //En el caso contrario, mostramos qu eno se ha concedido el permiso.
                    Toast.makeText(this, "Permiso No Concedido", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


}