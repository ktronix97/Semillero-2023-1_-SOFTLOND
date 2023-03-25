package cuenta;
import excepciones.*;

/***
 * Clase abstracta para los diferentes tipos de cuentas bancarias
 * @author dalia
 * @version 1.0
 */

public abstract class CuentaBancaria {
    protected String numeroCuenta;
    protected double saldo;
    protected String propietario;
    protected int numRetiros;
	private int numDepositos;

    public CuentaBancaria(String numeroCuenta, double saldo, String propietario) {
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.saldo = saldo;
        this.numRetiros = 0;
        this.numDepositos = 0;
    }

    
    /***
     * Método para depositar dinero en la cuenta.
     * @param monto cantidad a depositar.
     * @throws MontoNegativoException si la cantidad de dinero ingresada es negativa.
     */
    public void depositar(double monto) throws MontoNegativoException {
        if (monto <= 0.0) {
            throw new MontoNegativoException("No deposita un valor correcto, repita la operación");
        }
        numDepositos ++;
        saldo += monto;
    }
    
    /***
     * Método Abstracto porque hay diferentes tipos de cuentas las cuales tienen diferentes 
     * condiciones y promociones para el usuario.
     * @param monto
     * @throws MaximoRetirosException
     * @throws SaldoInsuficienteException
     * @throws MontoNegativoException
     */
    public abstract void retirar(double monto) throws MaximoRetirosException, SaldoInsuficienteException, MontoNegativoException;

    /***
     * Método para realizar operaciones comunes de todas las cuentas al retirar
     * @param monto
     */
    public void realizarRetiro(double monto) {
        saldo -= monto;
        numRetiros++;
    }
    
    
    // Obtener el número de retiros de una cuenta.
    public int getNumRetiros() {
		return numRetiros;
	}

    // Obtener el número de depositos de una cuenta.
	public int getNumDepositos() {
		return numDepositos;
	}
	
	 // Regresa el número de cuenta para el usuario.
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    // Obtiene la cantidad de saldo disponible en la cuenta.
    public double getSaldo() {
        return saldo;
    }

    // Obtiene el titular de la cuenta.
    public String getPropietario() {
        return propietario;
    }
    
    
}


  