package persistencia.excepciones;
/***
 * @CuentaInexistente es una excepcion que hereda de Exception que se muestra al cliente 
 * si ingresa los datos incorrectos y no se puede enocntrar la cuenta en la base de datos.
 * @author dalia
 * @version 1.0
 */

public class CuentaInexistente extends Exception {
	
	 public CuentaInexistente(String mensaje) {
			super(mensaje);
	}

}
