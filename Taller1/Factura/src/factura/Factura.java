package factura;

import precio.Precio;

public class Factura extends Precio {
	
	private String emisor;
	private String cliente;

	
	public Factura( int valor, String emisor, String cliente) {
		super(valor);
		this.emisor = emisor;
		this.cliente = cliente;
		
	}
	
    /**
     *
     * @param precioFactura el precio de la factura
     * @return Retorna en un string los datos del vendedor y el clinete
     */
    public String imprimirFactura(Precio precioFactura) {
		
		String datosFactura = "vendedor:"+" "+ this.emisor +" "+ "cliente:" +" " + this.cliente +" "+ "valor: " + precioFactura.getValor();
		return datosFactura; 
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
 
}
