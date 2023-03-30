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
import persistencia.excepciones.*;

import persistencia.entidades.*;

/**
 *
 * @author Dalia
 */
public class RepositorioCuenta {

    protected String baseDatos;

    public RepositorioCuenta() {
        try {
        	this.baseDatos = "jdbc:sqlite:cuentas";
            DriverManager.registerDriver(new JDBC());
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
    
    
   public CuentaBancaria selectById(int id) throws SQLException, CuentaInexistente {
	   CuentaAhorro cuenta = null;
		Connection connection = null;
		String sql = "SELECT * FROM cuentas WHERE id = ?";
		try {
			connection = DriverManager.getConnection(super.toString());
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setDouble(1, id);

			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				cuenta = new CuentaAhorro(
					rs.getString("numero"),
					rs.getInt("saldo"),
					rs.getString("propietario")
				);
				cuenta.setNumRetiros(rs.getInt("retiros"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			connection.close();
		}
		if(cuenta == null) {
			throw new CuentaInexistente("La cuenta no existe");
		}else{
			return cuenta;
		}
   }


}
