package cuenta;
import excepciones.*;

/***
 * Clase de tipo cuenta bancaria llamada CuentaAhorro que da diferentes tipos de descuento 
 * y límites a los usuarios que una cuenta corriente.
 * @author dalia
 * @version 1.0
 */


public class CuentaAhorro extends CuentaBancaria {
    private static final double PORCENTAJE_DESCUENTO = 0.01;
    private static final int NUMERO_MAXIMO_RETIROS = 3;
    private static final int NUMERO_PRIMEROS_DEPOSITOS = 2;
    private int numDepositos;
    
    public CuentaAhorro(String numeroCuenta, double saldo, String propietario) {
        super(numeroCuenta,saldo, propietario);
        this.numDepositos = 0;
    }
    
    /***
     * Método que recibe un monto para retirar dinero de la cuenta, con el límite de 3 retiros al día, 
     * porcentaje de descuento del 0.01%. para cada usuario.
     * @SaldoInsuficienteException se lanza si intenta retirar más del saldo que tiene en la cuenta.
     * @MaximoRetirosException se lanza si sobrepasa el límite de retiros permitido.
     * @MontoNegativoException si el monto de dinero ingresado es negativo.
     */
    
    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, MaximoRetirosException, MontoInvalidoException {
        if (getNumRetiros() >= NUMERO_MAXIMO_RETIROS) {
            monto += monto * PORCENTAJE_DESCUENTO;
        }
        if (monto > getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar la transacción ");
        }
       
        super.depositar(monto);
    }
    
    /***
     * Método que recibe un monto para agregar al saldo actual de la cuenta
     * aplicando saldo adicional del 0.05% en los primero dos depositos
     */
    
    @Override
    public void depositar(double monto) throws MontoInvalidoException {
        if (numDepositos < NUMERO_PRIMEROS_DEPOSITOS) {
            super.depositar(monto + (monto * 0.005));
        } else {
            super.depositar(monto);
        }
        numDepositos++;
    }
}
