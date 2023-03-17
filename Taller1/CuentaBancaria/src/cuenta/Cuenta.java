package cuenta;

public class Cuenta {
	protected float saldo;
	protected int numeroConsignaciones = 0;
	protected int numeroRetiros = 0;
	protected float tasaAnual;
	protected float comisionMensual = 0;
	
	public Cuenta(float saldo, float  tasaAnual) {
		this.saldo = saldo;
		this.tasaAnual = tasaAnual;
	}
	
	public void consignar (float cantidad) {
		this.saldo = this.saldo + cantidad;
	}
	
	public void retirar (float cantidad) {
		if((this.saldo-cantidad)>0) {
		this.saldo = this.saldo - cantidad;
		}
		else {
			System.err.print("dinero insuficiente para realizar la transacción");
		}
	}
	
	public void calcularInteresMensual() {
		double tasaInteresMensual = this.tasaAnual/12;
		 float interesMensual =(float) (this.saldo * tasaInteresMensual);
	     this.saldo += interesMensual;
	}
	
	public void extractoMensual() {
          this.saldo = this.saldo - this.comisionMensual ;
          calcularInteresMensual(); 
	}
	
	public void imprimirInformacionCuenta() {
		System.out.println("La cuenta tiene un saldo de : "+ this.saldo + "$");
		System.out.println("Se han realizado : " + this.numeroConsignaciones + "consignaciones");
		System.out.println("La cuenta tiene una tasa Anual del : " + this.tasaAnual);
		System.out.println("La cuenta tiene una comision mensual de :" + this.comisionMensual);

	}
	
	public float getSaldo() {
		return saldo;
	}
	
}
