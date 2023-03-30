package persistencia.repositorios;

 import java.util.List;

import persistencia.entidades.CuentaBancaria;
import persistencia.excepciones.*;
 
 
 
public interface IRepositorio<T> {
	void conexion();
	void desconectar();
	void createTable();
	void ejecutaConsulta(String query);
	void insertar(T entity);
	List<T> enListar();
	CuentaBancaria selectById(String numerodecuenta) throws CuentaInexistente;
	void update(T entity , int id) throws CuentaInexistente;
	void delete(int id) throws CuentaInexistente;
}
