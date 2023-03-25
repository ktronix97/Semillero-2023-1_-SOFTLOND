package cuenta;
import excepciones.*;
import java.util.HashMap;
import java.util.Map;

// Clase abstracta para las cuentas bancarias
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

    // Método para depositar en la cuenta
    public void depositar(double monto) throws MontoNegativoException {
        if (monto <= 0.0) {
            throw new MontoNegativoException("No deposita un valor correcto, repita la operación");
        }
        numDepositos ++;
        saldo += monto;
    }

    // Método abstracto para retirar de la cuenta
    public abstract void retirar(double monto) throws MaximoRetirosException, SaldoInsuficienteException, MontoNegativoException;

    // Método para realizar operaciones comunes al retirar
    public void realizarRetiro(double monto) {
        saldo -= monto;
        numRetiros++;
    }
    
    
    public int getNumRetiros() {
		return numRetiros;
	}

	public int getNumDepositos() {
		return numDepositos;
	}
	
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getPropietario() {
        return propietario;
    }
    
    
}


  