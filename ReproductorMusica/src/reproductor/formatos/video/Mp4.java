package reproductor.formatos.video;
import reproductor.*;

/**
 * Clase de formato vídeo llamada Mp4
 * @author dalia
 * @version 1.0
 *
 */
public class Mp4 extends Video{
	
	
	public Mp4(int duracion, double tamano, String creador, String tipoVideo) {
		super(duracion, tamano, creador, tipoVideo);
	}	


	/***
	 * Método que reproduce vídeo mp4 en el reproductor de música.
	 */
	@Override
	public void reproducir() {
		System.out.println( "Reproduciendo video mp4");
	}
	
	
	
}
