package Inmuebles;

public class CasaConjuntoCerrado extends CasaUrbana{
	
	protected int valorAdministracion;
	protected static double valorArea = 2500000;
	protected boolean tienePiscina;
	protected boolean tieneCamposDeportivos;
	
	public CasaConjuntoCerrado(int identificadorInmobiliario, int area, String direccion, int numeroHabitaciones,
			int numeroBanos, int numeroPisos,int valorAdministracion, boolean tienePiscina,
			boolean tieneCamposDeportivos) {
		super(identificadorInmobiliario, area, direccion, numeroHabitaciones, numeroBanos, numeroPisos);
		this.valorAdministracion = valorAdministracion;
		this.tienePiscina = tienePiscina;
		this.tieneCamposDeportivos = tieneCamposDeportivos;
	}

	@Override
	void imprimir() {
		super.imprimir(); // Invoca al método imprimir de la clase padre
		System.out.println("Valor de la administración = " + this.valorAdministracion);
		System.out.println("Tiene piscina? = " + this.tienePiscina);
		System.out.println("Tiene campos deportivos? = " + this.tieneCamposDeportivos);
		System.out.println();
	}

	

}
 