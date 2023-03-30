package persistencia.servicios;

import java.sql.SQLException;
import java.util.List;

/***
 * Interfaz de Cuenta que tiene todos los servios que el banco ofrece al cliente.
 * @author dalia
 * @param <T> 
 */
public interface IServicioCuenta<T> {
	
	public void createDataBaseCuenta();
	public void saveCuenta(T cuenta);
	public List<T> getCuentas();
	public T getCuenta(String id) throws SQLException;
	public void elimarCuenta(String id);
	public void transferir(String idOrigen, String idDestino, double cantidad);
	public void retirar();
	public void depositar();

}
