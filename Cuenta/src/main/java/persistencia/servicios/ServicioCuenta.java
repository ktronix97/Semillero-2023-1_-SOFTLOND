package persistencia.servicios;

import java.util.List;
import persistencia.repositorios.*;
import persistencia.entidades.CuentaBancaria;
import persistencia.excepciones.*;

public class ServicioCuenta<T>{
	
	private IRepositorio<T> cuentaRepository;

	public ServicioCuenta(IRepositorio<T> cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public void createDDL() {
		this.cuentaRepository.createTable();
	}

	public void saveCuenta(T cuenta) {
		this.cuentaRepository.insertar(cuenta);
	}
	
	public List<T> getCuentas() {
		List<T> cuentas = this.cuentaRepository.enListar();
		return cuentas;
	}

	public CuentaBancaria getCuenta(String numerodecuenta) {
		try {
			return this.cuentaRepository.selectById(numerodecuenta);
		} catch (CuentaInexistente e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public void updateCuenta(T cuenta, int id) {
		try {
			this.cuentaRepository.update(cuenta, id);
		} catch (CuentaInexistente e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeCuenta(int id) {
		try {
			this.cuentaRepository.delete(id);
		} catch (CuentaInexistente e) {
			System.out.println(e.getMessage());
		}
	}

}
