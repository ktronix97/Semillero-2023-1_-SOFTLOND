package reproductor.formatos.audio;

/***
 * Clase de un formato tipo audio llamada Wav con cambios en la función reprodir.
 * @author dalia
 *
 */
public class Wav extends Audio{

	public Wav(int duracion, double tamano, String artista, String composicion, String generoMusical) {
		super(duracion, tamano, artista, composicion, generoMusical);
	}

	/***
	 * Método que reproduce audio wav en el reproductor de música.
	 */
	@Override
	public void reproducir() {
		System.out.println( "Reproduciendo wav");
		
	}
	
	

}
