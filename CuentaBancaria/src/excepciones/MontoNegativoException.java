package excepciones;

/*** 
 * MontoNegativoException es una excepcion que hereda de Exception que se muestra al cliente 
 * si el monto de dinero ingresado es negativo.
 * @author dalia
 * @version 1.0
 */

public class MontoNegativoException extends Exception {
	
	public MontoNegativoException(String mensaje) {
		super(mensaje);
	}
}
