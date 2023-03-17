package Inmuebles;

/**
* Esta clase modela un inmueble de vivienda con atributos como el número de habitaciones 
* y el número de baños que posee.
* @author dalia
* @version 1.0
*/
public class InmuebleVivienda extends Inmueble {

	protected int numeroHabitaciones;
	protected int numeroBanos;

	public InmuebleVivienda(int identificadorInmobiliario, int area, String direccion, int numeroHabitaciones, int numeroBanos) {
			super(identificadorInmobiliario, area, direccion); /* Invoca al
			constructor de la clase padre */
			this.numeroHabitaciones = numeroHabitaciones;
			this.numeroBanos = numeroBanos;
	}
	
	/***
	 * Método que muestra los datos de un inmueble normal los datos propios de la una vivienda.
	 */
	@Override
	void imprimir() {
		super.imprimir(); // Invoca al método imprimir de la clase padre
		System.out.println("Número de habitaciones = " + this.numeroHabitaciones);
		System.out.println("Número de baños = " + this.numeroBanos);
	}

}
