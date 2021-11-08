package com.pmdm.tarea2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Inicializo los componentes con los que trabajaré
    EditText txtMensaje = null;
    TextView txtDatosAlumno = null;
    Button btnEstadoConexion = null;
    Button btnEnviarSMS = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Utilizando el método findViewById los busco y me los traigo para poder usarlos.
        txtDatosAlumno = findViewById(R.id.txtDatosAlumno);
        txtMensaje = findViewById(R.id.txtMensaje);
        btnEstadoConexion = findViewById(R.id.btnEstadoConexion);
        btnEnviarSMS = findViewById(R.id.btnEnviarSMS);

        //Enlazo los botones con el OnClickListener.
        btnEstadoConexion.setOnClickListener(this);
        btnEnviarSMS.setOnClickListener(this);

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

                break;
        }
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
}