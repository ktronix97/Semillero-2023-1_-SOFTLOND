package reproductor.formatos.audio;

/***
 * Clase de un formato tipo audio llamada mp3 con cambios en la función reprodir.
 * @author dalia
 *
 */
public class Mp3 extends Audio{
	

	public Mp3(int duracion, double tamano, String artista, String composicion, String generoMusical) {
		super(duracion, tamano, artista, composicion, generoMusical);
	}

	@Override
	public void reproducir() {
		System.out.println( "Reproduciendo mp3");
		
	}
}
