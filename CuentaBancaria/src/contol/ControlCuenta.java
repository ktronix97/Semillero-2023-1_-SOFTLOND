package contol;
import cuenta.*;
import excepciones.*;

import java.util.Scanner;

/***
 * Clase de pruebas de CuentaBancaria
 * @author dalia
 * @version 1.0
 */
public class ControlCuenta {

	    	public static void main(String[] args) throws SaldoInsuficienteException, MontoInvalidoException {
	    		// Crear una cuenta de ahorro con un saldo de 5000 unidades y propietaria Dalia
	            CuentaBancaria cuentaAhorro = new CuentaAhorro("0013", 5000, "Dalia");
	            
	            // Depositar 2000 pesos
	            try {
	            	cuentaAhorro.depositar(2000);
	            } catch (MontoInvalidoException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            // Depositar 0 pesos
	            try {
	            	cuentaAhorro.depositar(0);
	            } catch (MontoInvalidoException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            // Retirar 6000 pesos
	            try {
	            	cuentaAhorro.retirar(6000);
	            	cuentaAhorro.retirar(60);
	            	cuentaAhorro.retirar(60);
	            	cuentaAhorro.retirar(60);
	            } catch (MaximoRetirosException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            // Retirar 3000 pesos (saldo insuficiente)
	            try {
	            	cuentaAhorro.retirar(3000);
	            } catch ( MaximoRetirosException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            // Retirar 0 pesos
	            try {
	            	cuentaAhorro.retirar(0);
	            } catch (MaximoRetirosException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            System.out.println("------------------------------------------------");

	    		// Crear una cuenta de ahorro con un saldo de 5000 unidades y un propietario llamado Juan
	            CuentaBancaria cuentaCorriente = new CuentaCorriente("0022", 5000, "Dalia");
	            
	            // Depositar 2000 pesos
	            try {
	            	cuentaCorriente.depositar(2000);
	            } catch (MontoInvalidoException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            // Depositar 0 pesos
	            try {
	            	cuentaCorriente.depositar(0);
	            } catch (MontoInvalidoException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            // Retirar 1000 pesos 5 veces (Máximo cantidad de retiros)
	            try {
	            	cuentaCorriente.retirar(1000);
	            	cuentaCorriente.retirar(1000);
	            	cuentaCorriente.retirar(1000);
	            	cuentaCorriente.retirar(1000);
	            	cuentaCorriente.retirar(1000);
	            } catch (MaximoRetirosException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            // Retirar 1000 pesos (Sobrepasa el máximo de retiros posibles)
	            try {
	            	cuentaCorriente.retirar(1000);
	            } catch (MaximoRetirosException e) {
	                System.out.println(e.getMessage());
	            }
	            
	            
	            // Retirar 0 pesos
	            try {
	            	cuentaCorriente.retirar(0);
	            } catch (MaximoRetirosException e) {
	                System.out.println(e.getMessage());
	            }
	    	

	    
	}
}
