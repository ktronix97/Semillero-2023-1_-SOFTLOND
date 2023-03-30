package persistencia.servicios;

import java.sql.SQLException;
import java.util.List;
import persistencia.repositorios.*;
import persistencia.entidades.CuentaBancaria;
import persistencia.excepciones.*;

public class ServicioCuenta<T> implements IServicioCuenta {

    private RepositorioCuenta cuentaRepository;

    /**
     * Crea el servicio
     *
     * @param cuentaRepository
     */
    public ServicioCuenta() {
        this.cuentaRepository = new RepositorioCuenta();
    }

    /**
     * Genera una tabla para la cuenta
     */
    public void createDataBaseCuenta() {
        this.cuentaRepository.createTable();
    }

    /**
     * Salva una cuenta
     *
     * @param cuenta
     */
    public void saveCuenta(CuentaBancaria cuenta) {
        this.cuentaRepository.insertar(cuenta);
    }

    /**
     * Retorna una lista con todas las cuentas creadas
     *
     * @return
     */
    public List<CuentaBancaria> getCuentas() {
        List<CuentaBancaria> cuentas = this.cuentaRepository.enListar();



        return cuentas;
    }

    /**
     * Obtiene una cuenta especifica segun el numero de cuenta
     *
     * @param numerodecuenta
     * @return
     */
    public CuentaBancaria getCuenta(String numerodecuenta) throws SQLException {
        try {
            return this.cuentaRepository.selectById(numerodecuenta);
        } catch (CuentaInexistente e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza una cuenta especifica
     *
     * @param cuenta
     * @param id
     */
    public void updateCuenta(CuentaBancaria cuenta, String id) {
        try {
            this.cuentaRepository.actualizar(cuenta, id);
        } catch (CuentaInexistente e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Remueve una cuenta por Id
     *
     * @param id
     */
    public void removeCuenta(int id) {
        try {
            this.cuentaRepository.eliminar(id);
        } catch (CuentaInexistente e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveCuenta(Object cuenta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void elimarCuenta(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void transferir(String idOrigen, String idDestino, double cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void retirar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void depositar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
