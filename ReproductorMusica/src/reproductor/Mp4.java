package reproductor;

public class Mp4 implements ReproductorMusica{
	
	private int duracion;
	private double tamano;
	private String creador;
	private String tipoVideo;
	
	
	
	public Mp4(int duracion, double tamano, String creador, String tipoVideo) {
		this.duracion = duracion;
		this.tamano = tamano;
		this.creador = creador;
		this.tipoVideo = tipoVideo;
	}



	@Override
	public void reproducir() {
		System.out.println( "Reproduciendo video mp4");
	}
	
	
	
}
