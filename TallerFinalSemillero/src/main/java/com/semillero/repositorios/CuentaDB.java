package com.semillero.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.semillero.entidades.Cuenta;

public class CuentaDB implements Repositorio {
    private String cadenaConexion;
    public CuentaDB(){
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
                    "CREATE TABLE  if NOT EXISTS CUENTAS(\n" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "NUMERO_CUENTA TEXT NOT NULL UNIQUE,\n" +
                    "SALDO REAL INTEGER NOT NULL,\n" +
                    "TIPO_CUENTA TEXT NOT NULL,\n" +
                    "ID_USUARIO INTEGER NOT NULL,\n" +
                    "FOREIGN KEY(ID_USUARIO) REFERENCES USUARIO(ID)\n" +
                    ");\n";

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
            Cuenta cuentas = (Cuenta) objeto;
            String sentenciaSql = "INSERT INTO CUENTAS (NUMERO_CUENTA, SALDO , TIPO_CUENTA,ID_USUARIO) " +
            " VALUES('" + cuentas.getNumeroCuenta() + "', " + cuentas.getSaldo()
                     + ",'" + cuentas.getTipo()
                     + "', " + cuentas.getId_usuario() + ");";
            System.out.println(sentenciaSql);
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String numeroCuenta) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "DELETE FROM Cuentas WHERE NUMERO_CUENTA = '" + numeroCuenta + "';";
            System.out.println(sentenciaSql);
            java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Object objeto) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Cuenta cuentas = (Cuenta) objeto;
            String sentenciaSql = "UPDATE Cuentas SET NUMERO_CUENTA, = '" + cuentas.getNumeroCuenta() + "', SALDO REAL = '"
                    + cuentas.getSaldo() + "', TIPO_CUENTA = " + cuentas.getTipo() + ", ID_USUARIO = '"
                    + cuentas.getId_usuario() + "' WHERE NUMERO_CUENTA = '" + cuentas.getNumeroCuenta() + "';";
                    java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    @Override
    public Object buscar(String NUMERO_CUENTA) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSQL = "SELECT * FROM Cuentas WHERE NUMERO_CUENTA = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, NUMERO_CUENTA);
            ResultSet resultadoConsulta = sentencia.executeQuery();
            if (resultadoConsulta != null && resultadoConsulta.next()) {
                Cuenta cuentas = null;
                int id = resultadoConsulta.getInt("id");
                String numeroCuenta = resultadoConsulta.getString("NUMERO_CUENTA");
                Integer saldo = resultadoConsulta.getInt("SALDO_REAL");
                String tipoCuenta = resultadoConsulta.getString("TIPO_CUENTA");
                Integer idCuenta = resultadoConsulta.getInt("ID_USUARIO");
                
                cuentas = new Cuenta(id, numeroCuenta, saldo, idCuenta, tipoCuenta);
                
                return cuentas;
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        return null;
    }

    @Override
    public List<?> listar() {
        List<Cuenta> cuentas = new ArrayList<Cuenta>();

        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            String sentenciaSql = "SELECT * FROM CUENTAS";
            PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
            ResultSet resultadoConsulta = sentencia.executeQuery();

            if (resultadoConsulta != null) {
                while (resultadoConsulta.next()) {
                    Cuenta cuenta = null;
                    int id = resultadoConsulta.getInt("id");
                    String numeroCuenta = resultadoConsulta.getString("NUMERO_CUENTA");
                Integer saldo = resultadoConsulta.getInt("SALDO");
                String tipoCuenta = resultadoConsulta.getString("TIPO_CUENTA");
                Integer idUsuario = resultadoConsulta.getInt("ID_USUARIO");

                    cuenta = new Cuenta( id, numeroCuenta, saldo, idUsuario, tipoCuenta);
                    cuentas.add(cuenta);
                }
              return cuentas;
  
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        }
        
        return null;
    }

    @Override
    public void actualizarId(Object objeto, String id) {
        try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
            Cuenta cuentas = (Cuenta) objeto;
            String sentenciaSql = "UPDATE Cuentas SET NUMERO_CUENTA = '" + cuentas.getNumeroCuenta() + "', SALDO REAL = "
                    + cuentas.getSaldo() + ", TIPO_CUENTA = '" + cuentas.getTipo()  + "' WHERE id = " + id
                    + ";";
                    java.sql.Statement sentencia = conexion.createStatement();
            sentencia.execute(sentenciaSql);
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e);
        } catch (Exception e) {
            System.err.println("Error " + e.getMessage());
        }
    }

        
        
    
}
