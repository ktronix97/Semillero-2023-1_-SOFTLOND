/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.repositorios;

import org.sqlite.JDBC;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import persistencia.excepciones.*;

import persistencia.entidades.*;

/**
 *
 * @author Dalia
 */
public class RepositorioCuenta implements IRepositorio<CuentaBancaria> {

    protected String baseDatos;

    /**
     *
     */
    public RepositorioCuenta() {
        try {
            this.baseDatos = "jdbc:sqlite:database.db";
            DriverManager.registerDriver(new JDBC());
            this.conexion();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Conecta a un servidor local
     */
    public void conexion() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.baseDatos);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }
    }

    /**
     * Se desconecta de un servidor local
     *
     * @param connection
     */
    protected void desconectar(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Ejecuta una consulta dada
     *
     * @param query
     */
    public void ejecutaConsulta(String query) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.baseDatos);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(20);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }
    }

    /**
     * Borra un dato segun una ID
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        int response = 0;
        Connection connection = null;
        String sql = "DELETE FROM cuentas WHERE id =" + id;
        try {
            connection = DriverManager.getConnection(this.baseDatos);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            response = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }

    }

    /**
     * Crea una tabla
     */
    @Override
    public void createTable() {
        this.ejecutaConsulta("DROP TABLE IF EXISTS cuentas");
        this.ejecutaConsulta("CREATE TABLE IF NOT EXISTS CUENTAS(\n"
                + "idCuenta INTEGER PRIMARY KEY NOT NULL,"
                + "tipoCuenta TEXT NOT NULL,"
                + "propietario TEXT NOT NULL,"
                + "saldo REAL NOT NULL,"
                + "numRetiros INTEGER NOT NULL,"
                + "numDepositos INTEGER NOT NULL)");
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws CuentaInexistente
     */
    public CuentaBancaria selectById(int id) throws SQLException, CuentaInexistente {
        CuentaAhorro cuenta = null;
        Connection connection = null;
        String sql = "SELECT * FROM cuentas WHERE id = ?";
        try {
            connection = DriverManager.getConnection(super.toString());
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDouble(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cuenta = new CuentaAhorro(
                        rs.getString("numero"),
                        rs.getInt("saldo"),
                        rs.getString("propietario")
                );
                cuenta.setNumRetiros(rs.getInt("retiros"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.close();
        }
        if (cuenta == null) {
            throw new CuentaInexistente("La cuenta no existe");
        } else {
            return cuenta;
        }
    }

    @Override
    public void desconectar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return
     */
    @Override
    public List<CuentaBancaria> enListar() {
        Connection connection = null;
        List<CuentaBancaria> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuentas";
        try {
            connection = DriverManager.getConnection(this.baseDatos);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                CuentaAhorro cuenta = new CuentaAhorro(
                        rs.getString("numero"),
                        rs.getInt("saldo"),
                        rs.getNString("propíetario")
                );
                cuenta.setNumRetiros(rs.getInt("retiros"));
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }
        System.out.println(cuentas);
        return cuentas;

    }

    @Override
    public CuentaBancaria selectById(String numerodecuenta) throws CuentaInexistente {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(CuentaBancaria entity, int id) throws CuentaInexistente {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertar(CuentaBancaria cuenta) {
        Connection connection = null;
        String sql = "INSERT INTO cuentas(idCuenta,tipoCuenta,Propietario,Saldo,NumRetiros,numDepositos)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            connection = DriverManager.getConnection(this.baseDatos);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, cuenta.getNumeroCuenta());
            pstmt.setString(2, cuenta.getTipo().toString());
            pstmt.setString(3, cuenta.getPropietario());
            pstmt.setDouble(4, cuenta.getSaldo());
            pstmt.setDouble(5, cuenta.getNumRetiros());
            pstmt.setDouble(6, cuenta.getNumDepositos());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Se ha creado exitosamente el ");
            this.desconectar(connection);
        }
    }

}
