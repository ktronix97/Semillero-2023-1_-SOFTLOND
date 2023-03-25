package cuenta;
import excepciones.*;


class CuentaAhorro extends CuentaBancaria {
    private static final double PORCENTAJE_DESCUENTO = 0.01;
    private static final int NUMERO_MAXIMO_RETIROS = 3;
    private static final int NUMERO_PRIMEROS_DEPOSITOS = 2;
    private int numDepositos;
    
    public CuentaAhorro(String numeroCuenta, double saldo, String propietario) {
        super(numeroCuenta,saldo, propietario);
        this.numDepositos = 0;
    }
    
    @Override
    public void retirar(double monto) throws SaldoInsuficienteException, MaximoRetirosException, MontoNegativoException {
        if (getNumRetiros() >= NUMERO_MAXIMO_RETIROS) {
            monto += monto * PORCENTAJE_DESCUENTO;
        }
        if (monto > getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar la transacción ");
        }
       
        super.depositar(monto);
    }
    
    @Override
    public void depositar(double monto) throws MontoNegativoException {
        if (numDepositos < NUMERO_PRIMEROS_DEPOSITOS) {
            super.depositar(monto + (monto * 0.005));
        } else {
            super.depositar(monto);
        }
        numDepositos++;
    }
}
