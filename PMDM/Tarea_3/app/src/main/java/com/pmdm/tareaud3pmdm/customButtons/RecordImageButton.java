package com.pmdm.tareaud3pmdm.customButtons;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;

import com.pmdm.tareaud3pmdm.MainActivity;
import com.pmdm.tareaud3pmdm.R;

public class RecordImageButton extends AppCompatImageButton {
    //Creo un boolean para tener un control del estado del botón.
    private boolean estaGrabando = false;

    //Instancio la clase main para poder usar sus métodos.
    MainActivity main;

    //Creo un constructor.
    public RecordImageButton(@NonNull Context context) {
        super(context);
        //Le aplico la imagen que tendrá inicialmente el botón.
        setImageResource(R.drawable.record);

        //Le pongo el fondo de color blanco.
        setBackgroundColor(Color.WHITE);

        //Lo enlazo OnClickListener.
        setOnClickListener(click);

        //Inicializo el objeto de la clase main.
        main = (MainActivity) context;
    }

    //Creo un objeto de la clase OnClickListener para controlar los eventos de click.
    OnClickListener click = new OnClickListener() {
        @Override
        public void onClick(View v) {
            //Si estaba grabando y hacemos click...
            if(estaGrabando){
                //Pongo el boolean a false.
                estaGrabando = false;
                //Le ponemos la imagen "record" de nuevo.
                setImageResource(R.drawable.record);
                //Usamos método de la clase main para parar la grabación.
                main.pararGrabacion();

            //Si no estaba grabando y hacemos click...
            }else{
                //Ponemos el boolean a true.
                estaGrabando = true;
                //Le ponemos la imagen "stop" de nuevo.
                setImageResource(R.drawable.stop);
                //Y usamos el método de la clase main para iniciar la grabación.
                main.iniciarGrabacion();
            }
        }
    };
    //Y devolvemos los getters y setters.
    public boolean isEstaGrabando() {
        return estaGrabando;
    }

    public void setEstaGrabando(boolean estaGrabando) {
        this.estaGrabando = estaGrabando;
    }
}
