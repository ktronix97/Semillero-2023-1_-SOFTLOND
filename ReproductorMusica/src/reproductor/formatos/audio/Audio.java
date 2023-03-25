package reproductor.formatos.audio;

import reproductor.ReproductorMusica;

/**
 * Clase con los atributos generales de cualquier tipo de audio. 
 * @author dalia
 * @version 1.0
 */

public abstract class Audio implements ReproductorMusica {
	
	private int duracion;
	private double tamano;
	private String artista;
	private String compositor;
	private String generoMusical;
	
	
	/***
	 * 
	 * @param duracion en minutos
	 * @param tamano en KB
	 * @param artista 
	 * @param compositor
	 * @param generoMusical
	 */
	
	public Audio(int duracion, double tamano, String artista, String compositor, String generoMusical) {
		this.duracion = duracion;
		this.tamano = tamano;
		this.artista = artista;
		this.compositor = compositor;
		this.generoMusical = generoMusical;
	}
	
}
