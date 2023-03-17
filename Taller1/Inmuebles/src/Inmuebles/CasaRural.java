package Inmuebles;

public class CasaRural extends Casa{

	protected static double valorArea = 1500000;
	protected int distanciaCabera;
	protected int altitud;

	public CasaRural(int identificadorInmobiliario, int area, String direccion, int numeroHabitaciones, int numeroBanos,
			int numeroPisos, int distanciaCabera, int altitud) {
		super(identificadorInmobiliario, area, direccion, numeroHabitaciones, numeroBanos, numeroPisos);
		this.distanciaCabera = distanciaCabera;
		this.altitud = altitud;

	}
	
	@Override
	void imprimir() {
		super.imprimir(); // Invoca al método imprimir de la clase padre
		System.out.println("Distancia la cabecera municipal = " + this.numeroHabitaciones + " km.");
		System.out.println("Altitud sobre el nivel del mar = " + this.altitud + " metros.");
	}

}
