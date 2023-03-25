package excepciones;

/*** 
 * MontoNegativoException es una excepcion que hereda de Exception que se muestra al cliente 
 * si el monto de dinero ingresado es negativo.
 * @author dalia
 * @version 1.0
 */

public class MontoInvalidoException extends Exception {
	
	// Llama al constructor de Excepcion.
	public MontoInvalidoException(String mensaje) {
		super(mensaje);
	}
}
