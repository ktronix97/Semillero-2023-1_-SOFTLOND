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
     * Se crea un repositorio que se conecta a la base de datos
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
    @Override
    public void desconectar(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * Ejecuta una consulta dada.
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
    public void eliminar(int id) throws CuentaInexistente {
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
     * Selecciona un usuario dado un Id especifico
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws CuentaInexistente
     */
    @Override
    public CuentaBancaria selectById(String id) throws SQLException, CuentaInexistente {

        CuentaAhorro cuenta = null;
        Connection connection = null;
        String sql = "SELECT * FROM cuentas WHERE idCuenta = ?";
        try {
            connection = DriverManager.getConnection(this.baseDatos);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cuenta = new CuentaAhorro(
                        rs.getString("idCuenta"),
                        rs.getInt("saldo"),
                        rs.getString("propietario")
                );
                cuenta.setNumRetiros(rs.getInt("numRetiros"));
                cuenta.setNumDepositos(rs.getInt("numDepositos"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }
        if (cuenta == null) {
            throw new CuentaInexistente("La cuenta no existe");
        } else {
            return cuenta;
        }
    }

    /**
     * Devuelve en una lista todos los datos de cuentas
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
                if (rs.getString("tipoCuenta").equalsIgnoreCase("Ahorro")) {

                    CuentaBancaria cuenta = new CuentaAhorro(
                            rs.getString("idcuenta"),
                            rs.getDouble("saldo"),
                            rs.getString("propietario")
                    );
                    cuenta.setNumRetiros(rs.getInt("numRetiros"));
                    cuentas.add(cuenta);
                } else if (rs.getString("tipoCuenta").equalsIgnoreCase("Corriente")) {

                    CuentaBancaria cuenta = new CuentaCorriente(
                            rs.getString("idcuenta"),
                            rs.getDouble("saldo"),
                            rs.getString("propietario")
                    );
                    cuenta.setNumRetiros(rs.getInt("numRetiros"));
                    cuentas.add(cuenta);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }
        return cuentas;

    }

    /**
     * Inserta una cuenta dada en la base de datos
     *
     * @param cuenta
     */
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
            System.out.println("Se ha creado exitosamente el usuario");
            this.desconectar(connection);
        }
    }

    @Override
    public void actualizar(CuentaBancaria cuenta, String id) throws CuentaInexistente {
        System.out.println("Dato");
        System.out.println(id);

        Connection connection = null;
        String sql = "UPDATE cuentas SET saldo=?, propietario=?, numretiros=? ,numdepositos=?   WHERE idCuenta=" + id;
        try {
            connection = DriverManager.getConnection(this.baseDatos);
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setDouble(1, cuenta.getSaldo());
            pstmt.setString(2, cuenta.getPropietario());
            pstmt.setInt(3, cuenta.getNumRetiros());
            pstmt.setInt(4, cuenta.getNumDepositos());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }

    }

}
