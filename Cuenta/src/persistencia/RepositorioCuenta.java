/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Juan
 */
public class RepositorioCuenta {

    protected String baseDatos;

    public RepositorioCuenta() {
        try {
            DriverManager.registerDriver(new org.sqlite.JDBC());
            this.baseDatos = "jdbc:sqlite:cuentas";
            this.conexion();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void conexion() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.baseDatos);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                //System.out.println("Database connected: " + meta.getDatabaseProductName());
                //System.out.println("The driver name is " + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }
    }

    protected void desconectar(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

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

    public void delete(int id) {
        int response = 0;
        Connection connection = null;
        String sql = "DELETE FROM cuentas WHERE id ="+id;
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

    
    public void createTable(){
		this.ejecutaConsulta("DROP TABLE IF EXISTS cuentas");
		this.ejecutaConsulta("CREATE TABLE IF NOT EXISTS CUENTAS(\n"
                        + "idCuenta INTEGER PRIMARY KEY NOT NULL,"
                        + "tipoCuenta TEXT NOT NULL,"
                        + "propietario TEXT NOT NULL,"
                        + "saldo REAL NOT NULL,"
                        + "numRetiros INTEGER NOT NULL,"
                        + "numDepositos INTEGER NOT NULL)");
   }
    




}
