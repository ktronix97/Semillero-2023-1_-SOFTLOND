package com.semillero.servicios;

import java.util.List;
import java.util.Map;

import com.semillero.entidades.Transacciones;
import com.semillero.repositorios.*;
import com.semillero.entidades.*;

public class TransaccionServicio {
    private TransaccionDB transaccionRepository;

    public TransaccionServicio() {
        transaccionRepository = new TransaccionDB();
    }

    public void crear(Map datos) throws Exception {
    	int id = (int) datos.get("id");
        String fecha = (String) datos.get("fecha");
        String hora = (String) datos.get("hora");
        String tipoTransaccion = (String) datos.get("tipoTransaccion");
        double monto = (double) datos.get("monto");
        int idCuenta = (int) datos.get("idCuenta");
        String tipoCuentaDestino = "";

        // Como el tipo de cuenta destino puede ser nulo debemos validar su valor
        if (datos.get("tipoCuentaDestino") == null){
            tipoCuentaDestino = "";
        } else {
            tipoCuentaDestino = (String) datos.get("tipoCuentaDestino");
        }

        Transacciones nuevaTransaccion = new Transacciones(id, fecha, hora, tipoTransaccion, monto, idCuenta, tipoCuentaDestino);

        // Hacemos unas ultimas validaciones para saber que tipo de transacción se desea realizar
        switch (tipoTransaccion){
            case "Depositar":
                try{
                    transaccionRepository.depositar(nuevaTransaccion);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "Retirar":
                try {
                    transaccionRepository.retirar(nuevaTransaccion);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case "Transferir":
                try{
                    transaccionRepository.transferir(nuevaTransaccion);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            default:
                throw new Exception("Tipo de transaccion no valida");
        }
    }

    public void eliminar(String id) {
        transaccionRepository.eliminar(id);
    }

    public void actualizar(Map datos) {

    }

    public List<Transacciones> listar() {
        return (List<Transacciones>) transaccionRepository.listar();
    }

    public List<Transacciones> listarPorId(String id){
        return (List<Transacciones>) transaccionRepository.buscar(id);
    }

    public Object buscar(String id) throws Exception {
        Object transaccion = transaccionRepository.buscar(id);
        if (transaccion != null){
            return (Cuenta) transaccion;
        } else {
            throw new Exception("No se encontro la transacción");
        }
    }
}