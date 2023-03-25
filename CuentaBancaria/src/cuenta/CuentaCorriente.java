package cuenta;
import excepciones.*;


public class CuentaCorriente extends CuentaBancaria {
    private static final int MAX_RETIROS = 5;
    private static final double PORCENTAJE_DEPOSITO = 0.005;
    private int numDepositos;

    public CuentaCorriente(String numeroCuenta, double saldo, String propietario) {
        super(numeroCuenta,saldo, propietario);
        this.numDepositos = 0;
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, MaximoRetirosException, MontoNegativoException {
        if (numRetiros >= MAX_RETIROS) {
            throw new MaximoRetirosException("se ha excedido el máximo de retirnos disponibles");
        }
        if (monto <= 0.0) {
            throw new MontoNegativoException("Valor incorrecto, vuelva a realizar la operación");
       
        }        
        realizarRetiro(monto);

    }
    
}