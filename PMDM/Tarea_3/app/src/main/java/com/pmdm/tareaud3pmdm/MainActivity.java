package com.pmdm.tareaud3pmdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.pmdm.tareaud3pmdm.customButtons.PlayImageButton;
import com.pmdm.tareaud3pmdm.customButtons.RecordImageButton;
import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener{

    //Inicializo los componentes con los que trabajaré
    ImageButton btnRetroceder, btnAvanzar;
    TextView tiempo;
    LinearLayout linearBotones;

    //Instancio un mediaplayer para poder reproducir audio.
    MediaPlayer mp = null;

    //Instancio un MediaRecorder para poder grabar audio.
    MediaRecorder mr = null;

    //Instancio los botones para luego Inicializarlos.
    RecordImageButton recordImageButton;
    PlayImageButton playImageButton;

    //Clase encargada de la creación de hilos para el timer del reproductor.
    timerAudio ta;

    //Variable donde guardaré la posición al pausar.
    int posicionPausa = -1;

    //Creo una constante estática para poder utilizarlo al pedir permisos.
    static final int ID_PETICION_PERMISO_RECORD_AUDIO = 1234;

    //Ruta donde se grabarán los archivos
    String rutaGrabacion;
    File rutaArchivo;

    //Método que iniciará nada más arrancar la app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Si existe algún archivo antiguo lo primero que hará es asignar la ruta y borrarlo.
        rutaGrabacion = getFilesDir().getAbsolutePath() + File.separator + "Grabacion.3gp";
        rutaArchivo = new File(rutaGrabacion);

        if(rutaArchivo.exists()){
            rutaArchivo.delete();
        }

        //Recojo los diferentes componentes para poder utilizarlos en el código.
        linearBotones = findViewById(R.id.linearBotones);
        btnAvanzar = findViewById(R.id.btnAvanzar);
        btnRetroceder = findViewById(R.id.btnRetroceder);
        tiempo = findViewById(R.id.tiempo);

        //Los asociamos al listener para poder captar sus eventos de click.
        btnAvanzar.setOnClickListener(this);
        btnRetroceder.setOnClickListener(this);

        //Traigo los componentes personalizados para usarlos.
        recordImageButton = new RecordImageButton(this);
        playImageButton = new PlayImageButton(this);

        //Configo los botones para que al iniciar únicamente se pueda grabar.
        controlBotones(false, true, false, false);

        //Les configuro el padding y los margenes para poder alinearlos.
        recordImageButton.setPadding(0,0,0,0);
        playImageButton.setPadding(0,0,0,0);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(25, 0, 0, 0);
        btnAvanzar.setLayoutParams(lp);
        btnRetroceder.setLayoutParams(lp);
        recordImageButton.setLayoutParams(lp);
        playImageButton.setLayoutParams(lp);


        /*Añados los componentes personalizados a linearLayout
          añado a la vista pero eligiendo la posición en la
          que quiero que esté teniendo en cuenta los demás botones.*/
        linearBotones.addView(recordImageButton, 0);
        linearBotones.addView(playImageButton, 1);

        //Y al iniciar dejo el TextView vacio.
        tiempo.setText("");
    }

    //Con este método cargamos el archivo.
    public void cargarAudio(){
        //Iniciamos el objeto de la clase MediaPlayer
        mp =  new MediaPlayer();
        try {
            //Le pasamos la ruta.
            mp.setDataSource(rutaGrabacion);
            //Lo preparamos para reproducirlo.
            mp.prepare();
        } catch (IOException e) {
            Toast.makeText(this, "Error al cargar la grabación.", Toast.LENGTH_SHORT).show();
        }
    }

    //Método encargado de la reproducción al pulsar el botón.
    public void reproducir(){
        //Carga el audio
        cargarAudio();

        //Iniciamos la reproducción.
        mp.start();

        //En caso de volver del estado "pausa", llevará esa posición en el audio.
        if(posicionPausa != -1){
            mp.seekTo(posicionPausa);
        }
        //Cuando reproducimos le pasamos la escucha de ambos eventos.
        mp.setOnCompletionListener(this);
        mp.setOnPreparedListener(this);
    }

    //Método encargado de pausar la reproducción de audio.
    public void pausar(){
        //Antes de pausar guardo la posición en la que estaba.
        posicionPausa = mp.getCurrentPosition();

        //Y pausamos.
        mp.pause();

        //Y controlamos los botones que estarán activos y cuales no.
        controlBotones(false, true, true, false);
    }

    //Método encargado de avanzar los audios 5 seg al pulsar el botón.
    public void avanzar(){
        //Al pulsar el botón recupera la posición en el audio y le suma 5 seg.
        mp.seekTo(mp.getCurrentPosition()+5000);

        //Y mostramos un toast avisando de ello.
        Toast.makeText(this,  "Avanza 5 seg.", Toast.LENGTH_SHORT).show();
    }

    //Método encargado de retroceder los audios 5 seg al pulsar el botón.
    public void retroceder(){
        //Al pulsar el botón recupera la posición en el audio y le resta 5 seg.
        mp.seekTo(mp.getCurrentPosition()-5000);

        //Y mostramos un toast avisando de ello.
        Toast.makeText(this, "Retrocede 5 seg.", Toast.LENGTH_SHORT).show();
    }

    //Método encargado de iniciar la grabación
    public void iniciarGrabacion(){
        //Comprobamos si tenemos permisos realizar la grabación
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {

            //Si hubiera un hilo activo trabajando mostrando el tiempo lo pararía.
            pararHilo();

            //Como volvemos a grabar, reiniciamos la posición de pausado.
            posicionPausa = -1;

            //Mientras grabo desactivo los demás botones.
            controlBotones(false, true, false, false);

            //Inicializo MediaRecorder para empezar a usarlo.
            mr = new MediaRecorder();

            //Elijo de donde recibirá el sonido
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);

            //Le asigno el formato en el cual grabará tal como se pide.
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

            //Si el archivo existe, como vamos a grabar de nuevo lo borraremos primero.
            if(rutaArchivo.exists()){
                rutaArchivo.delete();
            }

            //Le paso la ruta donde se grabará al MediaRecorder.
            mr.setOutputFile(rutaGrabacion);

            //Le asignamos la codificación.
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            
            //Preparamos para grabar y si diera algún error liberariamos recursos.
            try {
                mr.prepare();
            } catch (IOException e) {
                mr.reset();
                mr.release();
                mr = null;
                Toast.makeText(this, "Error al iniciar la grabación.", Toast.LENGTH_SHORT).show();
            }
            //Iniciamos la grabación
            mr.start();

            //Avisamos de ello con un toast
            Toast.makeText(this, "Grabación en Curso...", Toast.LENGTH_SHORT).show();

            //En el textview añadimos el estado actual que es "grabando".
            tiempo.setText("Grabando...");
        }
        //En el caso de no haber permisos entraría aquí
        else{
            //Y solicitariamos los permisos con el siguiente método.
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.RECORD_AUDIO}, ID_PETICION_PERMISO_RECORD_AUDIO);
        }
    }

    //Se usaría este método para parar la grabación.
    public void pararGrabacion(){
        //Liberamos recursos.
        mr.stop();
        mr.reset();
        mr.release();
        mr = null;
        //Y mostramos un toast avisando de que se ha detenido la grabación.
        Toast.makeText(this, "Grabación Detenida", Toast.LENGTH_SHORT).show();

        //Restablezco los botones y dejo activos los necesarios para evitar errores.
        controlBotones(false, true, true, false);

        //Vacio el TextView una vez listo.
        tiempo.setText("");
    }

    //Método encargado de controlar el estado de los botones (bloqueados o no bloqueados).
    public void controlBotones(boolean retroceder, boolean grabar, boolean reproducir, boolean avanzar){
        //Activo y desactivo según lo indicado en los parámetros.
        btnRetroceder.setEnabled(retroceder);
        recordImageButton.setEnabled(grabar);
        playImageButton.setEnabled(reproducir);
        btnAvanzar.setEnabled(avanzar);

        /*Con los siguientes condicionales oscurezco los botones cuando están
         inactivos y les devuelvo el color en el caso contrario.*/

        //En el caso del boton retroceder...
        if(retroceder){
            //Limpio el filtro para que se vea activado.
            btnRetroceder.clearColorFilter();
        }else{
            //Le aplico filtro para que parezca desactivado el botón.
            btnRetroceder.setColorFilter(ContextCompat.getColor(this, R.color.disabledButton),
                    PorterDuff.Mode.SRC_ATOP);
        }

        //En el caso del botón grabar.
        if(grabar){
            //Limpio el filtro para que se vea activado.
            recordImageButton.clearColorFilter();

        }else{
            //Le aplico filtro para que parezca desactivado el botón.
            recordImageButton.setColorFilter(ContextCompat.getColor(this, R.color.disabledButton),
                    PorterDuff.Mode.SRC_ATOP);
        }

        //En el caso del botón reproducir.
        if(reproducir){
            //Limpio el filtro para que se vea activado.
            playImageButton.clearColorFilter();
        }else{
            //Le aplico filtro para que parezca desactivado el botón.
            playImageButton.setColorFilter(ContextCompat.getColor(this, R.color.disabledButton),
                    PorterDuff.Mode.SRC_ATOP);
        }

        //En el caso del botón avanzar.
        if(avanzar){
            //Limpio el filtro para que se vea activado.
            btnAvanzar.clearColorFilter();
        }else{
            //Le aplico filtro para que parezca desactivado el botón.
            btnAvanzar.setColorFilter(ContextCompat.getColor(this, R.color.disabledButton),
                    PorterDuff.Mode.SRC_ATOP);
        }
    }

    //Comprobamos las peticiones.
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResult){
        super.onRequestPermissionsResult(requestCode, permission, grantResult);

        switch (requestCode){
            case ID_PETICION_PERMISO_RECORD_AUDIO:
                if(grantResult[0] == PackageManager.PERMISSION_GRANTED){
                    //Inicio la Grabación.
                    iniciarGrabacion();
                }
                else{
                    //En el caso contrario, mostramos que no se ha concedido el permiso.
                    Toast.makeText(this, "Permiso No Concedido", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //Capturamos los eventos de click de los eventos.
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //En el caso del botón avanzar haría lo siguiente.
            case R.id.btnAvanzar:
                avanzar();
                break;
            //En el caso del botón retroceder haría lo siguiente.
            case R.id.btnRetroceder:
                retroceder();
                break;
        }
    }

    //Este método se activa al captar que el video ha terminado.
    @Override
    public void onCompletion(MediaPlayer mp) {
        //Se vuelve a poner el botón de Play.
        playImageButton.setImageResource(R.drawable.play);

        //Cuando acabe se vuelven a bloquear y desbloquear según la necesidad.
        controlBotones(false, true, true, false);

        /*Se reinicia cuando ha detectado que llego al final si pulsamos de nuevo,
        pero para que se reinicie el contador del hilo lo pongo manualmente*/
        mp.seekTo(0);
        //Muestra mensaje al llegar al final
        Toast.makeText(this, "Fin del audio.", Toast.LENGTH_SHORT).show();
    }

    //Este método se activa al captar que el video está listo para reproducir.
    @Override
    public void onPrepared(MediaPlayer mp) {
        /*Pararíamos primero el hilo antes de crear el siguiente
          que se encargaría del tiempo visualmente.*/
        pararHilo();

        //Inicio los botones de reproducción y desactivo el de grabación.
        controlBotones(true, false, true, true);

        //Creo un nuevo hilo para el tiempo.
        ta = new timerAudio( mp, this);
    }

    //Método encargado de parar los hilos.
    public void pararHilo(){
        try{
            //Pondremos el atributo usando un setter a false para pararlo
            ta.setEstadoHilo(false);
        }
        //En caso de no poder detener el hilo mostraría este mensaje.
        catch(Exception ex){
            System.out.println("Error al detener el hilo.");
        }
    }

    //Método encargado de captar cuando ponemos la app en segundo plano.
    protected void onPause() {
        super.onPause();
        //Si el audio está reproduciendo pausaría el audio.
        if(mp.isPlaying()){
            //Se vuelve a poner el botón de Play.
            playImageButton.setImageResource(R.drawable.play);

            //Pausamos el audio.
            pausar();
        }
    }
}