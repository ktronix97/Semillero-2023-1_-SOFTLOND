package Inmuebles;

public class CasaIndipendiente extends CasaUrbana{
	
	protected static double valorArea = 3000000;

	public CasaIndipendiente(int identificadorInmobiliario, int area, String direccion, int numeroHabitaciones,
			int numeroBanos, int numeroPisos) {
		super(identificadorInmobiliario, area, direccion, numeroHabitaciones, numeroBanos, numeroPisos);
		
	}
		
}
