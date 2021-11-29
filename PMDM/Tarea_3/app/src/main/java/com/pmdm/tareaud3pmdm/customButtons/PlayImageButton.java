package com.pmdm.tareaud3pmdm.customButtons;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;

import com.pmdm.tareaud3pmdm.MainActivity;
import com.pmdm.tareaud3pmdm.R;

public class PlayImageButton extends AppCompatImageButton {
    //Creo un boolean para tener un control del estado del botón.
    private boolean estaReproduciendo = false;

    //Instancio la clase main para poder usar sus métodos.
    MainActivity main;

    //Creo un constructor.
    public PlayImageButton(@NonNull Context context) {
        super(context);

        //Le aplico la imagen que tendrá inicialmente el botón.
        setImageResource(R.drawable.play);

        //Le pongo el fondo de color blanco.
        setBackgroundColor(Color.WHITE);

        //Lo enlazo OnClickListener.
        setOnClickListener(click);

        //Inicializo el objeto de la clase main.
        main = (MainActivity) context;
    }

    //Creo un objeto de la clase OnClickListener para controlar los eventos de click.
    OnClickListener click = new OnClickListener() {
        //Si estaba reproduciendo y hacemos click...
        @Override
        public void onClick(View v) {
            if(estaReproduciendo){
                //Pongo el boolean a false.
                estaReproduciendo = false;
                //Le ponemos la imagen "play" de nuevo.
                setImageResource(R.drawable.play);
                //Usamos método de la clase main para pausar la grabación.
                main.pausar();

            }
            //Si no estaba reproduciendo y hacemos click...
            else{
                //Ponemos el boolean a true.
                estaReproduciendo = true;
                //Le ponemos la imagen "pause" de nuevo.
                setImageResource(R.drawable.pause);
                //Y usamos el método de la clase main para iniciar la reproducción.
                main.reproducir();
            }
        }
    };

    //Y devolvemos los getters y setters.
    public boolean isEstaReproduciendo() {
        return estaReproduciendo;
    }

    public void setEstaReproduciendo(boolean estaReproduciendo) {
        this.estaReproduciendo = estaReproduciendo;
    }
}
