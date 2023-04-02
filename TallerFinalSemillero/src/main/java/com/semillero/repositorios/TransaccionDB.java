package com.semillero.repositorios;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.semillero.entidades.*;
import com.semillero.entidades.Transacciones;
import com.semillero.exepciones.RegistroInexistenteException;
import com.semillero.exepciones.SaldoInsuficienteException;
import com.semillero.servicios.*;
import com.semillero.repositorios.*;

import java.util.List;

public class TransaccionDB implements Repositorio{
	CuentaDB cuentaRepository;
    private String cadenaConexion;
    public TransaccionDB(){
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            cadenaConexion="jdbc:sqlite:banco.db";
            creartabla();
        } catch (Exception e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }
    }
    public  void creartabla() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            String cadenaConexion = "jdbc:sqlite:banco.db";
            String sql = 

                    "CREATE TABLE if NOT EXISTS TRANSACCIONES(\n" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "FECHA TEXT NOT NULL,\n" +
                    "HORA TEXT NOT NULL,\n" +
                    "TIPO_TRANSACCION TEXT NOT NULL,\n" +
                    "MONTO REAL INTEGER NOT NULL,\n" +
                    "ID_CUENTA INTEGER NOT NULL,\n" +
                    "TIPO_CUENTA_DESTINO TEXT,\n" +
                    "FOREIGN KEY(ID_CUENTA) REFERENCES CUENTAS(ID)\n" +
                    ");";

            Connection conexion = DriverManager.getConnection(cadenaConexion);
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);

        } catch (SQLException e) {
            System.err.println("Error de conexión con la base de datos: " + e);
        }
    }





    @Override
    public void guardar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Transacciones Transacciones = (Transacciones) objeto;
            String sentenciaSql = "INSERT INTO Transacciones (FECHA, HORA, TIPO_TRANSACCION,MONTO REAL,ID_CUENTA,TIPO_CUENTA_DESTINO) " +
            " VALUES('" + Transacciones.getFecha() + "', '" + Transacciones.getHora()
                         + "', '" + Transacciones.getTipoTransaccion() + "', " + Transacciones.getMonto()
                              + "," + Transacciones.getId_cuenta()
                              + ", '" + Transacciones.getTipo_cuenta_destino() + "');";
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }                   

    
    
   

    @Override
    public void eliminar(String identificacion) {
       
    }

    @Override
    public void actualizar(Object objeto) {
       
    }

    @Override
    public Object buscar(String ID_CUENTA) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM Transacciones WHERE ID_CUENTA = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, ID_CUENTA);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                Transacciones Transaccionesa = null;
                String FECHA = resultadoConsulta.getString("FECHA");
                String HORA = resultadoConsulta.getString("HORA");
                String TIPO_TRANSACCION = resultadoConsulta.getNString("TIPO_TRANSACCION");
                int MONTO = resultadoConsulta.getInt("MONTO");
                int TIPO_CUENTA_DESTINO = resultadoConsulta.getInt("TIPO_CUENTA_DESTINO");
                int ID_CUENTAResultado = resultadoConsulta.getInt("ID_CUENTA");
                

                

                Transaccionesa = new Transacciones(TIPO_CUENTA_DESTINO, FECHA, HORA, TIPO_TRANSACCION, MONTO, ID_CUENTAResultado, ID_CUENTA);
                return Transaccionesa;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
        List<Transacciones> Transaccioness = new ArrayList<Transacciones>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM Transaccionesa";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    Transacciones Transacciones = null;
                    String FECHA = resultadoConsulta.getString("FECHA");
                String HORA = resultadoConsulta.getString("HORA");
                String TIPO_TRANSACCION = resultadoConsulta.getNString("TIPO_TRANSACCION");
                int MONTO = resultadoConsulta.getInt("MONTO");
                int TIPO_CUENTA_DESTINO = resultadoConsulta.getInt("TIPO_CUENTA_DESTINO");
                int ID_CUENTAResultado = resultadoConsulta.getInt("ID_CUENTA");

                    Transacciones = new Transacciones(ID_CUENTAResultado, FECHA, HORA, TIPO_TRANSACCION, MONTO, ID_CUENTAResultado, TIPO_TRANSACCION);
                    Transaccioness.add(Transacciones);
                }
                return Transaccioness;
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
        
}
    public void depositar(Object objeto) throws RegistroInexistenteException {
        // Hacemos un parseo al objeto recibido
        Transacciones transaccion = (Transacciones) objeto;
        // Como la función buscar() recibe es un String, guardamos en una variable el valor del idCuenta
        String idCuenta = String.valueOf(transaccion.getId_cuenta());
		// Obtenemos los datos de la cuenta a la que va a afectar el saldo del monto recibido
        Cuenta cuentaTransacción = (Cuenta) cuentaRepository.buscar(idCuenta);
        // Validamos que la cuenta si exista
        if(cuentaTransacción != null){
            // Validamos la cantidad de depositos para darle el beneficio
            int cantidadDepositos = consultarCantidadTransacciones(transaccion);
            if (cantidadDepositos < 2){
                // Se adiciona un 0.5% más del valor depositado
                double bonoBienvenida = (transaccion.getMonto()*0.5)/100;
                // Actualizamos el valor del monto
                transaccion.setMonto(transaccion.getMonto() + bonoBienvenida);
                // Actualizamos el atributo saldo con el monto de la transacción
                cuentaTransacción.setSaldo(cuentaTransacción.getSaldo() + transaccion.getMonto());
                // Procedemos a hacer los registros en la base de datos
                guardar(transaccion);
                actualizar(cuentaTransacción);
            } else {
                cuentaTransacción.setSaldo(cuentaTransacción.getSaldo() + transaccion.getMonto());
                guardar(transaccion);
                actualizar(cuentaTransacción);
            }
        } else {
            throw new RegistroInexistenteException(idCuenta + ": Error, esta cuenta no existe");
        }
    }

    private int consultarCantidadTransacciones(Transacciones transaccion) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void retirar(Object objeto) throws RegistroInexistenteException, SaldoInsuficienteException {
        // Hacemos lo mismo que con la funcion depositar()
        Transacciones transaccion = (Transacciones) objeto;
        String idCuenta = String.valueOf(transaccion.getId_cuenta());
        Cuenta cuentaTransaccion = (Cuenta) cuentaRepository.buscar(idCuenta);
        // Validamos que la cuenta exista
        if (cuentaTransaccion != null){
            // Validamos el número de retiros
            int cantidadRetiros = consultarCantidadTransacciones(transaccion);
            if (cantidadRetiros < 3){
                //Validamos que el monto solicitado se puede retirar
                if (transaccion.getMonto() < cuentaTransaccion.getSaldo()){
                    cuentaTransaccion.setSaldo(cuentaTransaccion.getSaldo() - transaccion.getMonto());
                    guardar(transaccion);
                    actualizar(cuentaTransaccion);
                } else {
                    throw new SaldoInsuficienteException("No tiene fondos suficientes para hacer el retiro");
                }
            } else {
                // Como ha realizado más de 3 retiros restamos el 1% del valor que retira
                double cobroAdicional = (transaccion.getMonto()*1)/100;
                // Actualizamos el valor a retirar
                transaccion.setMonto(transaccion.getMonto()+cobroAdicional);

                //Validamos que el monto solicitado se puede retirar
                if (transaccion.getMonto() < cuentaTransaccion.getSaldo()){
                    cuentaTransaccion.setSaldo(cuentaTransaccion.getSaldo() - transaccion.getMonto());
                    guardar(transaccion);
                    actualizar(cuentaTransaccion);
                } else {
                    throw new SaldoInsuficienteException("No tiene fondos suficientes para hacer el retiro");
                }
            }
        } else {
            throw new RegistroInexistenteException(idCuenta + ": Error, esta cuenta no existe");
        }
    }

    public void transferir(Object objeto) throws RegistroInexistenteException, SaldoInsuficienteException {
        Transacciones transaccion = (Transacciones) objeto;
        String idCuentaTransaccion = String.valueOf(transaccion.getId_cuenta());
        Cuenta cuentaTransaccion = (Cuenta) cuentaRepository.buscar(idCuentaTransaccion);
        if (cuentaTransaccion != null){
            // Se haran unos cobros adicionales dependiendo de los tipos de cuentas
            if (cuentaTransaccion.getTipo() == "Ahorro" && cuentaTransaccion.getTipo() != transaccion.getTipo_cuenta_destino()){
                // Se hace un cobro adicional del 1.5 %
                double cobroAdicional = (transaccion.getMonto()*1.5)/100;
                // Actualizamos el valor a retirar
                transaccion.setMonto(transaccion.getMonto() + cobroAdicional);
                //Validamos que el monto solicitado se puede retirar
                if (transaccion.getMonto() < cuentaTransaccion.getSaldo()){
                    // Actualizamos el saldo en ambos objetos cuenta
                    cuentaTransaccion.setSaldo(cuentaTransaccion.getSaldo() - transaccion.getMonto());
                    guardar(transaccion);
                    // Actualizamos el saldo de las cuentas en la BD
                    actualizar(cuentaTransaccion);
                } else {
                    throw new SaldoInsuficienteException("No tiene fondos suficientes para hacer el retiro");
                }
            } else if (cuentaTransaccion.getTipo() == "Corriente" && cuentaTransaccion.getTipo() != transaccion.getTipo_cuenta_destino()) {
                // Se hace un cobro adicional del 2 %
                double cobroAdicional = (transaccion.getMonto()*2)/100;
                // Actualizamos el valor a retirar
                transaccion.setMonto(transaccion.getMonto() + cobroAdicional);
                //Validamos que el monto solicitado se puede retirar
                if (transaccion.getMonto() < cuentaTransaccion.getSaldo()){
                    // Actualizamos el saldo en ambos objetos cuenta
                    cuentaTransaccion.setSaldo(cuentaTransaccion.getSaldo() - transaccion.getMonto());
                    guardar(transaccion);
                    // Actualizamos el saldo de las cuentas en la BD
                    actualizar(cuentaTransaccion);
                } else {
                    throw new SaldoInsuficienteException("No tiene fondos suficientes para hacer el retiro");
                }
            } else {
                //Validamos que el monto solicitado se puede retirar
                if (transaccion.getMonto() < cuentaTransaccion.getSaldo()){
                    // Actualizamos el saldo en ambos objetos cuenta
                    cuentaTransaccion.setSaldo(cuentaTransaccion.getSaldo() - transaccion.getMonto());
                    guardar(transaccion);
                    // Actualizamos el saldo de las cuentas en la BD
                    actualizar(cuentaTransaccion);
                } else {
                    throw new SaldoInsuficienteException("No tiene fondos suficientes para hacer el retiro");
                }
            }
        } else {
            throw new RegistroInexistenteException(idCuentaTransaccion + ": Error, esta cuenta no existe");
        }
    }
}