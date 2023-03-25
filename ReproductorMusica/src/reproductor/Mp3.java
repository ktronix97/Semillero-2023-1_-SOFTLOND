package reproductor;

public class Mp3 implements ReproductorMusica{
	
	private int duracion;
	private double tamano;
	private String artista;
	private String compositor;
	private String generoMusical;
	
	
	
	public Mp3(int duracion, double tamano, String artista, String composicion, String generoMusical) {
		this.duracion = duracion;
		this.tamano = tamano;
		this.artista = artista;
		this.compositor = composicion;
		this.generoMusical = generoMusical;
	}



	@Override
	public void reproducir() {
		System.out.println( "Reproduciendo mp3");
		
	}
}
