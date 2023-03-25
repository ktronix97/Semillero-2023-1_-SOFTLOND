package reproductor.formatos.video;

import reproductor.ReproductorMusica;

/**
 * Clase con los atributos generales de cualquier tipo de vídeo. 
 * @author dalia
 * @version 1.0
 */

public abstract class Video implements ReproductorMusica {
	
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
	
	public Video(int duracion, double tamano, String creador, String tipoVideo) {
		this.duracion = duracion;
		this.tamano = tamano;
		this.creador = creador;
		this.tipoVideo = tipoVideo;
	}
	
}
