package reproductor;

public class Wav implements ReproductorMusica{
	
	private int duracion;
	private double tamano;
	private String artista;
	private String compositor;
	private String generoMusical;
	
	
	public Wav(int duracion, double tamano, String artista, String composicion, String generoMusical) {
		this.duracion = duracion;
		this.tamano = tamano;
		this.artista = artista;
		this.compositor = composicion;
		this.generoMusical = generoMusical;
	}


	@Override
	public void reproducir() {
		System.out.println( "Reproduciendo wav");
		
	}
	
	

}
