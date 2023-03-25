package control;
import reproductor.*; 

public class PruebaReproductor {

	public static void main(String[] args) {
		Mp3 mp3 = new Mp3(3,150,"Ricardo Arjona","NN","Pop");
		mp3.reproducir();
		Mp4 mp4 = new Mp4(5,500.0,"Bzrap","Musica");
        mp4.reproducir();
        Wav wav = new Wav(4,200,"Silvestre Dangon","NN","Ballenato");
        wav.reproducir();
	}

}
