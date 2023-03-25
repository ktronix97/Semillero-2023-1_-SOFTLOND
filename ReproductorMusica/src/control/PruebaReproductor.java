package control;

import reproductor.formatos.audio.*;
import reproductor.formatos.video.*;

/***
 * Clase con datos de prueba para verificar el funcionamiento del programa ReproductorMusica
 * @author dalia
 * @version 1.0
 */
public class PruebaReproductor {

	/***
	 * Se crean diferentes ejemplos de audio y video para probar el reproductor. 
	 * @param args
	 */
	public static void main(String[] args) {
		Mp3 mp3 = new Mp3(3,150,"Ricardo Arjona","NN","Pop");
		mp3.reproducir();
		Mp4 mp4 = new Mp4(5,500.0,"Bzrap","Musica");
        mp4.reproducir();
        Wav wav = new Wav(4,200,"Silvestre Dangon","NN","Ballenato");
        wav.reproducir();
	}

}
