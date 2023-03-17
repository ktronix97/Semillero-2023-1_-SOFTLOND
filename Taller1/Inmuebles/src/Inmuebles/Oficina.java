package Inmuebles;

public class Oficina extends Local {

	protected static double valorArea = 3500000;
	protected boolean esGobierno;


	Oficina(int identificadorInmobiliario, int area, String direccion, tipo tipoLocal, boolean esGobierno) {
		super(identificadorInmobiliario, area, direccion, tipoLocal);
		this.esGobierno = esGobierno;
 	}
	
	@Override
	void imprimir() {
		super.imprimir();
		System.out.println("Es oficina gubernamental = " + this.esGobierno);
		System.out.println();
	}

}
