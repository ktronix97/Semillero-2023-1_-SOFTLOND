import factura.Factura;
import precio.Precio;

public class Caja {



	public static void main(String[] args) {
		Precio precio = new Precio(20000);
		Factura factura = new Factura(precio.getValor(),"Dalia","Santiago");
		System.out.println(factura.imprimirFactura(precio));

	}

}
