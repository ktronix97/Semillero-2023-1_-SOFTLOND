package persistencia.gui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import persistencia.entidades.*;
import persistencia.entidades.CuentaBancaria.TipoCuenta;
import persistencia.excepciones.CuentaInexistente;
import persistencia.excepciones.MaximoRetirosException;
import persistencia.excepciones.MontoInvalidoException;
import persistencia.excepciones.SaldoInsuficienteException;
import persistencia.repositorios.RepositorioCuenta;
import persistencia.servicios.*;

/***
 * Menú del cliente para realizar las transacciones correspondientes a su Cuenta Bancaria.
 * @author dalia
 * @param <T> 
 */

public class GuiBanco<T> {

    private boolean running = true;
    private ServicioCuenta serviciosBanco;

    public GuiBanco() {
        serviciosBanco = new ServicioCuenta();
    }

    public void iniciar() throws Exception {
        System.out.println("Bienvenido al sistema de bancos");

        while (running) {
            System.out.println("1. Crear cuenta");
            System.out.println("2. Listar cuenta");
            System.out.println("3. Buscar cuenta");
            System.out.println("4. Eliminar cuenta");
            System.out.println("5. Realizar depósito");
            System.out.println("6. Realizar retiro");
            System.out.println("7. Realizar transferencia");
            System.out.println("8. Salir");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            seleccion(opcion);
        }

    }
    
    /***
     * Muestra al cliente el menú y realiza las operaciones indicadas.
     * @param seleccion
     * @throws Exception si la opción ingresada en el menú es inválida. 
     */

    private void seleccion(int seleccion) throws Exception {
        switch (seleccion) {
            case 1:
                crearCuenta();
                break;
            case 2:
                listarCuentas();
                break;
            case 3:
                buscarCuenta();
                break;
            case 4:
                realizarDeposito();
                break;
            case 5:
                realizarTranferencia();
                break;
            case 6:
                realizarRetiro();
                break;
            case 7:
                System.out.println("No funciona aun");
                break;
            case 8:
                salir();
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    private void realizarTranferencia() throws CuentaInexistente, MaximoRetirosException, SaldoInsuficienteException, MontoInvalidoException {
        System.out.println("Tranferir Dinero a Cuenta");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta destino: ");
        String numeroCuentaDestino = scanner.nextLine();
        System.out.println("numero de cuenta origen: ");
        String numeroCuentaOrigen = scanner.nextLine();
        System.out.println("Cantidad Tranferir ");
        Double monto = scanner.nextDouble();
        try {
            CuentaBancaria cuentaDestino = serviciosBanco.getCuenta(numeroCuentaDestino);
            CuentaBancaria cuentaOrigen = serviciosBanco.getCuenta(numeroCuentaOrigen);
            System.out.println("Cuenta origen encontrada");
            System.out.println(cuentaDestino);
            System.out.println("Cuenta destino encontrada");
            System.out.println(cuentaOrigen);
            
            if((cuentaDestino== null || cuentaOrigen== null ) ){
                    throw new CuentaInexistente("Operación Inválida cuentas no encontradas");

            }
            
            cuentaOrigen.retirar(monto);
            cuentaDestino.depositar(monto);
            
            serviciosBanco.updateCuenta(cuentaOrigen, numeroCuentaOrigen);
            serviciosBanco.updateCuenta(cuentaDestino, numeroCuentaDestino);
            
        } catch (SQLException ex) {
            Logger.getLogger(GuiBanco.class.getName()).log(Level.SEVERE, null, ex);
        }  
      
    }

    private void crearCuenta() {
        System.out.println("Crear Cuenta");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numerocuenta: ");
        String numerocuenta = scanner.nextLine();
        System.out.println("saldo: ");
        int saldo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("propietario: ");
        String propietario = scanner.nextLine();
        System.out.println("tipo de cuenta: ");
        System.out.println("1 para cuenta de Ahorro, 2 para cuenta corriente");
        String tipo = scanner.nextLine();
        
        if(tipo.equals("1")){
        CuentaBancaria cuenta = new CuentaAhorro(numerocuenta, saldo, propietario);
        serviciosBanco.saveCuenta(cuenta);
        
        }
        else if(tipo.equals("2")){
        CuentaBancaria cuenta = new CuentaCorriente(numerocuenta, saldo, propietario);
        serviciosBanco.saveCuenta(cuenta);
        }
        else{
            System.out.println("Selecciono incorrectamente");
        }
        
    }

    private void listarCuentas() {
        System.out.println("Listando cuentas");
        List<CuentaBancaria> cuentas = serviciosBanco.getCuentas();

        for (CuentaBancaria cuenta : cuentas) {
            System.out.println(cuenta.toString());
        }

    }

    private void buscarCuenta() {
        System.out.println("Buscar cuenta");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        try {
            CuentaBancaria cuentaencontrada = serviciosBanco.getCuenta(numerodecuenta);
            System.out.println("cuenta encontrada: " + cuentaencontrada.getNumeroCuenta());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void realizarDeposito() throws Exception {
        System.out.println("\n------ Realizar Depósito ------");
        System.out.print("Ingrese el número de cuenta: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        System.out.println("Ingrese el monto a depositar: ");
        double monto = scanner.nextDouble();      
        CuentaBancaria cuenta = serviciosBanco.getCuenta(numerodecuenta);
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero");
            return;
        }
        cuenta.depositar(monto);
        
        if (cuenta == null) {
            System.out.println("La cuenta no existe");
            return;
        }

        System.out.println("Nuevo saldo: " + cuenta.getSaldo());
        serviciosBanco.updateCuenta(cuenta, numerodecuenta);
        
    }

    private void realizarRetiro() throws Exception {
        System.out.println("--realizar Retiro------: ");
        System.out.print("Ingrese el número de cuenta: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        CuentaBancaria cuenta = serviciosBanco.getCuenta(numerodecuenta);

        if (cuenta == null) {
            System.out.println("La cuenta no existe.");
            return;
        }
        System.out.println("Ingrese la cantidad a retirar: ");
        int monto = scanner.nextInt();
        cuenta.retirar(monto);
        System.out.println("Se ha retirado $" + monto + " de la cuenta " + numerodecuenta);
        System.out.println("El saldo actual de la cuenta es $" + cuenta.getSaldo());
        serviciosBanco.updateCuenta(cuenta, numerodecuenta);

    }

    private void salir() {
        System.out.println("Gracias por usar la aplicacion");
        this.running = false;
    }
}
