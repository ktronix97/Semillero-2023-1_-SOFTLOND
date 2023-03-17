package precio;

public class Precio {
	/***
	 * La clase precio nos devuelve el valor en pesos de una factura.
	 */
	private int valor;
	
    /***
     * 
     * @param valor retorna valor en pesos colombianos.
     */
	public Precio(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}
