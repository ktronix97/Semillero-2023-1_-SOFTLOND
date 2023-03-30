package persistencia;

import persistencia.entidades.CuentaAhorro;
import persistencia.entidades.CuentaBancaria;
import persistencia.gui.GuiBanco;
import persistencia.repositorios.IRepositorio;

/***
 * Clase de pruebas.
 * @author dalia
 * @version 1.0
 */

public class Main {

	public static void main(String[] args) throws Exception {
	     IRepositorio repositoriocuenta ;

	        GuiBanco gui = new GuiBanco();
	        gui.iniciar();

	}

}
   