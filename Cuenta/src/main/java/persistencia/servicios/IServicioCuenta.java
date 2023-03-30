package persistencia.servicios;

import java.util.List;

public interface IServicioCuenta<T> {
	
	public void createDataBaseCuenta();
	public void saveCuenta(T cuenta);
	public List<T> getCuentas();
	public T getCuenta(int id);
	public void elimarCuenta(int id);
	public void transferir(int idOrigen, int idDestino, double cantidad);
	public void retirar();
	public void depositar();

}
