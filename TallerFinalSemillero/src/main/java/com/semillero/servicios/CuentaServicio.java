package com.semillero.servicios;

import java.util.List;
import java.util.Map;

import com.semillero.entidades.Cuenta;
import com.semillero.entidades.Usuario;
import com.semillero.repositorios.CuentaDB;
import com.semillero.repositorios.Repositorio;

public class CuentaServicio {
    private static Repositorio CuentaRepositorio;

    public CuentaServicio() {
        CuentaRepositorio = new CuentaDB();
    }

    
    public void guardarCuenta(Map datos) {
    	
        String numeroCuenta = (String) datos.get("numero_cuenta");
        double saldo = (double) datos.get("saldo");
        Integer id_usuario = (Integer) datos.get("id_usuario");
        String tipo = (String) datos.get("tipo_cuenta");
        Cuenta newPerson = new Cuenta(0, numeroCuenta, saldo, id_usuario, tipo);
        CuentaRepositorio.guardar(newPerson);
    }

    public static List<Cuenta> listarCuenta() {
        
        return (List<Cuenta>) CuentaRepositorio.listar();
    }

    public Cuenta buscarcCuentas(String numeroCuenta) throws Exception {
        Object Cuenta = CuentaRepositorio.buscar(numeroCuenta);
        if (Cuenta == null) {
            throw new Exception("No se encontro la Cuenta");
        }
        return (Cuenta) Cuenta;
    }

    public void eliminarCuenta(String identificador) {
        CuentaRepositorio.eliminar(identificador);
    }

    public static void actualizarCuenta(Map datos) {
    	
        int idCuenta = (int) datos.get("id_cuentas");
        String numeroCuenta = (String) datos.get("numeroCuenta");
        int saldo = (int) datos.get("saldo");
        Integer id_usuario = (Integer) datos.get("id_usuario");
        String tipo = (String) datos.get("tipo");


        Cuenta newPerson = new Cuenta( idCuenta, numeroCuenta, saldo, id_usuario, tipo);
        CuentaRepositorio.actualizar(newPerson);
    }

    public void actualizarCuentaId(Map datos, String id) {
        int idCuenta = (int) datos.get("id_cuentas");
        String numeroCuenta = (String) datos.get("numeroCuenta");
        int saldo = (int) datos.get("saldo");
        Integer id_usuario = (Integer) datos.get("id_usuario");
        String tipo = (String) datos.get("tipo");

        Cuenta newPerson = new Cuenta( idCuenta, numeroCuenta, saldo, id_usuario, tipo);
        CuentaRepositorio.actualizarId(newPerson, id);
    }
}
