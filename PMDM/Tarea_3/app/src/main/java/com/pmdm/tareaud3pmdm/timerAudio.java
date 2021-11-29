package com.pmdm.tareaud3pmdm;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.TextView;

public class timerAudio extends Thread{
    //Creo los atributos
    private boolean estadoHilo;
    private MediaPlayer mp;
    private MainActivity main;

    //Creo el constructor el cual recuperaremos el objeto MediaPlayer y su contexto.
    public timerAudio( MediaPlayer mp, Context context) {
        //Inicializamos main.
        main = (MainActivity) context;
        //Ponemos a true el estadoHilo para que pueda funcionar.
        this.estadoHilo = true;
        //Guardamos MediaPlayer.
        this.mp = mp;
        //Iniciamos el hilo directamente.
        start();
    }
    //Aquí está el método que arrancaría el hilo.
    public void run(){
        //Si el estado del hilo es true empezará a dar vueltas aquí.
        while(estadoHilo){
            //Y usaremos el método recuentoTiempo.
            recuentoTiempo(mp.getCurrentPosition(), mp.getDuration());

        }
    }
    //Método encargado de formatear el timer del reproductor.
    public void recuentoTiempo(int actual, int duracion){
        StringBuffer strActual = new StringBuffer();
        StringBuffer strDuracion = new StringBuffer();
        //Hago los cálculos tanto para la posición actual como para la duración total.
        int hoursAct = (int) (actual / (1000 * 60 * 60));
        int minutesAct = (int) ((actual % (1000 * 60 * 60)) / (1000 * 60));
        int secondsAct = (int) (((actual % (1000 * 60 * 60)) % (1000 * 60)) / 1000);
        int hoursDur = (int) (duracion / (1000 * 60 * 60));
        int minutesDur = (int) ((duracion % (1000 * 60 * 60)) / (1000 * 60));
        int secondsDur = (int) (((duracion % (1000 * 60 * 60)) % (1000 * 60)) / 1000);
        //Lo formateamos.
        strActual.append(String.format("%02d", hoursAct)).append(":")
           .append(String.format("%02d", minutesAct)).append(":")
           .append(String.format("%02d", secondsAct));

        strDuracion.append(String.format("%02d", hoursDur)).append(":")
                .append(String.format("%02d", minutesDur)).append(":")
                .append(String.format("%02d", secondsDur));

        //Recupero el TextView "tiempo" del main.
        TextView tiempo = main.tiempo;
        //Y le pasamos la posición actual / y la duración total.
        tiempo.setText(strActual.toString() + " / " + strDuracion.toString());

    }

    //Y aquí los getters y setters.
    public boolean isEstadoHilo() {
        return estadoHilo;
    }

    public void setEstadoHilo(boolean estadoHilo) {
        this.estadoHilo = estadoHilo;
    }

    public MediaPlayer getMp() {
        return mp;
    }

    public void setMp(MediaPlayer mp) {
        this.mp = mp;
    }

    public MainActivity getMain() {
        return main;
    }

    public void setMain(MainActivity main) {
        this.main = main;
    }
}
