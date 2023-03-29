package excepciones;

/*** 
 * MaximoRetirosException es una excepcion que hereda de Exception que se muestra al cliente 
 * si sobrepasa el límite de retiros permitido.
 * @author dalia
 * @version 1.0
 */

public class MaximoRetirosException extends Exception {
	
	// Llama al constructor de Excepcion.
	public MaximoRetirosException(String mensaje) {
		super(mensaje);
	}

}
