package cuenta;

public class Cuenta {

    protected float saldo;
    protected int numeroConsignaciones = 0;
    protected int numeroRetiros = 0;
    protected float tasaAnual;
    protected float comisionMensual = 0;

    public Cuenta(float saldo, float tasaAnual) {
        this.saldo = saldo;
        this.tasaAnual = tasaAnual;
    }

    /**
     *
     * @param cantidad La cantidad de dinero a consignar
     */
    public void consignar(float cantidad) {
        this.saldo = this.saldo + cantidad;
    }

    /**
     *
     * @param cantidad La cantidad de dinero a retirar
     */
    public void retirar(float cantidad) {
        if ((this.saldo - cantidad) > 0) {
            //Si la cantidad a retirar es la suficiente, se hace la operacion
            this.saldo = this.saldo - cantidad;
        } else {
            //En caso contrario se da un aviso de que no hay suficiente dinero
            System.err.print("dinero insuficiente para realizar la transacción");
        }
    }

    /**
     * Calcula el interes mensual de la cuenta por medio de la tasa Anual
     */
    public void calcularInteresMensual() {
        double tasaInteresMensual = this.tasaAnual / 12;
        float interesMensual = (float) (this.saldo * tasaInteresMensual);
        this.saldo += interesMensual;
    }

    /**
     * Calcula el extracto mensual del usuario
     */
    public void extractoMensual() {
        this.saldo = this.saldo - this.comisionMensual;
        calcularInteresMensual();
    }

    /**
     * Imprime en cada linea el saldo de la cuenta, el numero de consignaciones,
     * la tasa anual y la comision mensual
     */
    public void imprimirInformacionCuenta() {
        System.out.println("La cuenta tiene un saldo de : " + this.saldo + "$");
        System.out.println("Se han realizado : " + this.numeroConsignaciones + "consignaciones");
        System.out.println("La cuenta tiene una tasa Anual del : " + this.tasaAnual);
        System.out.println("La cuenta tiene una comision mensual de :" + this.comisionMensual);

    }

    /**
     *
     * @return
     */
    public float getSaldo() {
        return saldo;
    }

}
