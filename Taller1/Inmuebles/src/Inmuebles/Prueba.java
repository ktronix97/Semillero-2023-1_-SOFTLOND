package Inmuebles;

public class Prueba {

	public static void main(String[] args) {
		ApartamentoFamiliar apartamentoFamiliar = new	ApartamentoFamiliar(103067,120,"Nogales carr35A#10D80",3,2,200000);
				System.out.println("Datos apartamento");
				apartamentoFamiliar.calcularPrecioVenta(apartamentoFamiliar.valorArea);
				apartamentoFamiliar.imprimir();
				System.out.println("Datos apartamento");
				Apartaestudio aptestudio1 = new
				Apartaestudio(12354,50,"Avenida del rio 34#15-21",1,1);
				aptestudio1.calcularPrecioVenta(Apartaestudio.valorArea);
				aptestudio1.imprimir();

	}

}
