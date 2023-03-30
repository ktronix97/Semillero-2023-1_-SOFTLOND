package persistencia.entidades;

import persistencia.excepciones.*;
import persistencia.gui.TipoCuenta;

/**
 * *
 * Clase abstracta para los diferentes tipos de cuentas bancarias
 *
 * @author dalia
 * @version 1.0
 */
public abstract class CuentaBancaria {

    public enum TipoCuenta {
        AHORRO,
        CORRIENTE,
        OTRA
    }

    protected String numeroCuenta;
    protected double saldo;
    protected String propietario;
    protected int numRetiros;
    private int numDepositos;
    protected TipoCuenta tipo;

    public CuentaBancaria(String numeroCuenta, double saldo, String propietario) {
        this.numeroCuenta = numeroCuenta;
        this.propietario = propietario;
        this.saldo = saldo;
        this.numRetiros = 0;
        this.numDepositos = 0;
        this.tipo=TipoCuenta.OTRA;
    }

    public CuentaBancaria(String numeroCuenta, double saldo, String propietario, String tipo2) {
        super();
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.propietario = propietario;
        //this.tipo = tipo2;
    }

    /**
     * *
     * Método para depositar dinero en la cuenta.
     *
     * @param monto cantidad a depositar.
     * @throws MontoInvalidoException si la cantidad de dinero ingresada es
     * negativa.
     */
    public void depositar(double monto) throws MontoInvalidoException {
        if (monto <= 0.0) {
            throw new MontoInvalidoException("No deposita un valor correcto, repita la operación");
        }
        numDepositos++;
        saldo += monto;
    }

    /**
     * *
     * Método Abstracto porque hay diferentes tipos de cuentas las cuales tienen
     * diferentes condiciones y promociones para el usuario.
     *
     * @param monto
     * @throws MaximoRetirosException
     * @throws SaldoInsuficienteException
     * @throws MontoInvalidoException
     */
    public abstract void retirar(double monto) throws MaximoRetirosException, SaldoInsuficienteException, MontoInvalidoException;

    /**
     * *
     * Método para realizar operaciones comunes de todas las cuentas al retirar
     *
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

    public void setNumRetiros(int retiros) {
        this.numRetiros = retiros;
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

    public void setNumDepositos(int numDepositos) {
        this.numDepositos = numDepositos;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoCuenta tipo) {
        this.tipo = tipo;
    }


}
