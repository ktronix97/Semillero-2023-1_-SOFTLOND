package reproductor.formatos.video;
import reproductor.*;

/**
 * Clase de formato vídeo llamada Mp4
 * @author dalia
 * @version 1.0
 *
 */
public class Mp4 implements ReproductorMusica{
	
	private int duracion;
	private double tamano;
	private String creador;
	private String tipoVideo;
	
	
	/***
	 * 
	 * @param duracion en minitos
	 * @param tamano en KB
	 * @param creador
	 * @param tipoVideo
	 */
	
	public Mp4(int duracion, double tamano, String creador, String tipoVideo) {
		this.duracion = duracion;
		this.tamano = tamano;
		this.creador = creador;
		this.tipoVideo = tipoVideo;
	}


	/***
	 * Método que reproduce vídeo mp4 en el reproductor de música.
	 */
	@Override
	public void reproducir() {
		System.out.println( "Reproduciendo video mp4");
	}
	
	
	
}
