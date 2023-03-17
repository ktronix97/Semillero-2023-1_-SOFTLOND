package cuenta;

public class CuentaAhorro extends Cuenta{

	private boolean activa;
	
	public CuentaAhorro(float saldo, float tasaAnual) {
		super(saldo, tasaAnual);
		if(saldo<10000) {
			this.activa = false;
		}
		else {
			this.activa = true;
		}
	}
	
	@Override
	public void consignar(float cantidad) {
		if(this.activa) {
		super.consignar(cantidad);
		}
	}
	
	@Override
	public void retirar(float cantidad) {
		if(this.activa) {
		super.retirar(cantidad);
		}
	}
	
	@Override
	public void extractoMensual() {
		super.extractoMensual(); 
		
		if (numeroRetiros > 4) {
			comisionMensual += (numeroRetiros - 4) * 1000;
			}	
			
		if ( saldo < 10000 ) {
			activa = false;
		}
	}
	
	public void imprimir() {
		System.out.println("Saldo = $ " + saldo);
		System.out.println("Comision mensual = $ " +
		comisionMensual);
		System.out.println("Número de transacciones = " +
		(numeroConsignaciones + numeroRetiros));
	
		}
	
	

}
