package cuenta;
import excepciones.*;

/***
 * Clase de tipo de cuenta bancaria llamada CuentaCorriente aplica un máximo de 5 retiros al cliente, 
 * además de un porcentaje de 0.005% de deposito.
 * @author dalia
 *
 */
public class CuentaCorriente extends CuentaBancaria {
	
    private static final int MAX_RETIROS = 5;
    private static final double PORCENTAJE_DEPOSITO = 0.005;
    private int numDepositos;

    /***
     *
     * @param numeroCuenta
     * @param saldo
     * @param propietario
     */
    public CuentaCorriente(String numeroCuenta, double saldo, String propietario) {
        super(numeroCuenta,saldo, propietario);
        this.numDepositos = 0;
    }

    /***
     * Realiza retiro de dinero de la cuenta corriente, válidando que no exceda 
     * el número de retiros máximo para la cuenta y lanzando las siguientes excepciones si hay errores.
     * SaldoInsuficienteException se lanza si intenta retirar más del saldo que tiene en la cuenta.
     * MaximoRetirosException se lanza si sobrepasa el límite de retiros permitido.
     * MontoNegativoException si el monto de dinero ingresado es negativo.
     */
    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, MaximoRetirosException, MontoNegativoException {
        if (numRetiros >= MAX_RETIROS) {
            throw new MaximoRetirosException("se ha excedido el máximo de retiros disponibles");
        }
        if (monto <= 0.0) {
            throw new MontoNegativoException("Valor incorrecto, vuelva a realizar la operación");
       
        }        
        realizarRetiro(monto);

    }
    
}