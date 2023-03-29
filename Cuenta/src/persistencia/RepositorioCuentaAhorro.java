/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import cuenta.CuentaCorriente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class RepositorioCuentaAhorro  extends RepositorioCuenta {
     public RepositorioCuentaAhorro() {
        super();
    }

    public void inserta(CuentaCorriente cuenta) {
        Connection connection = null;
        String sql = "INSERT INTO cuentas(idCuenta,tipoCuenta, propietario,saldo, numRetiros,numDepositos)"
                + "VALUES("
                + cuenta.getNumeroCuenta() + ","
                + "'" + "ahorro" + "',"
                + "'" + cuenta.getPropietario() + "',"
                + cuenta.getSaldo() + ","
                + cuenta.getNumRetiros() + ","
                + cuenta.getNumDepositos()
                + ")";
        try {
            connection = DriverManager.getConnection(super.baseDatos);
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.desconectar(connection);
        }
    }

    public List<CuentaCorriente> enlistar() {
        Connection connection = null;
        List<CuentaCorriente> cuentas = new ArrayList<>();
        String sql = "SELECT * FROM cuentas";
        try {
            connection = DriverManager.getConnection(super.baseDatos);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //Organizar
            while (rs.next()) {
                CuentaCorriente cuenta = new CuentaCorriente(
                        rs.getInt("idCuenta"),
                        rs.getInt("saldo"),
                        rs.getNString("propíetario")
                );
                cuenta.setNumRetiros(rs.getInt("numRetiros"));
                cuenta.setNumDepositos(rs.getInt("numDepositos"));
                cuentas.add(cuenta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            super.desconectar(connection);
        }
        return cuentas;
    }



    //Modificar update/////////////////////////////////////////////////
    public void update(CuentaCorriente entity, int id) {
        int response = 0;
        Connection connection = null;
        String sql = "UPDATE FROM cuentas WHERE id ="+id;
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
    
}
