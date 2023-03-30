package persistencia.servicios;

import java.util.List;
import persistencia.repositorios.*;
import persistencia.entidades.CuentaBancaria;
import persistencia.excepciones.*;

public class ServicioCuenta<T> {

    private RepositorioCuenta cuentaRepository;

    /**
     * Crea el servicio
     * @param cuentaRepository
     */
    public ServicioCuenta() {
        this.cuentaRepository = new RepositorioCuenta();
    }

    /**
     *Genera una tabla para la cuenta
     */
    public void createDDL() {
        this.cuentaRepository.createTable();
    }

    /**
     * Salva una cuenta 
     * @param cuenta
     */
    public void saveCuenta(CuentaBancaria cuenta) {
        this.cuentaRepository.insertar(cuenta);
    }

    /**
     * Retorna una lista con todas las cuentas creadas
     * @return
     */
    public List<CuentaBancaria> getCuentas() {
        List<CuentaBancaria> cuentas = this.cuentaRepository.enListar();
        return cuentas;
    }

    /**
     * Obtiene una cuenta especifica segun el numero de cuenta
     * @param numerodecuenta
     * @return
     */
    public CuentaBancaria getCuenta(String numerodecuenta) {
        try {
            return this.cuentaRepository.selectById(numerodecuenta);
        } catch (CuentaInexistente e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza una cuenta especifica
     * @param cuenta
     * @param id
     */
    public void updateCuenta(CuentaBancaria cuenta, int id) {
        try {
            this.cuentaRepository.update(cuenta, id);
        } catch (CuentaInexistente e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remueve una cuenta
     * @param id
     */
    public void removeCuenta(int id) {
        try {
            this.cuentaRepository.delete(id);
        } catch (CuentaInexistente e) {
            System.out.println(e.getMessage());
        }
    }

}
