package excepciones;

/*** 
 * MontoNegativoException es una excepcion que hereda de Exception que se muestra al cliente 
 * si la cantidad a retirar sobrepasa el saldo actual de la cuenta.
 * @author dalia
 * @version 1.0
 */

public class SaldoInsuficienteException extends Exception{
	
	
	// Llama al constructor de Excepcion.
	public SaldoInsuficienteException(String mensaje) {
		super(mensaje);
	}

}
