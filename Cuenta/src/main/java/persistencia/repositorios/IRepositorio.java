package persistencia.repositorios;

import java.sql.Connection;
import java.sql.SQLException;
 import java.util.List;

import persistencia.entidades.CuentaBancaria;
import persistencia.excepciones.*;
 
 /***
  * Repositorio general que tiene los métodos de una CuentaBancaria en la base de datos.
  * @author dalia
  * @param <T> 
  */
 
public interface IRepositorio<T> {
	void conexion();
	void desconectar(Connection connection);
	void createTable();
	void ejecutaConsulta(String query);
	void insertar(T entity);
	List<T> enListar();
	CuentaBancaria selectById(String numerodecuenta) throws SQLException, CuentaInexistente;
	void actualizar(T entity , String id) throws CuentaInexistente;
	void eliminar(int id) throws CuentaInexistente;
}
