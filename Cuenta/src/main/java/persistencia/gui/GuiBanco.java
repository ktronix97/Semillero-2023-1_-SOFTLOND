package persistencia.gui;

import java.util.List;
import java.util.Scanner;

import persistencia.entidades.*;
import persistencia.entidades.CuentaBancaria.TipoCuenta;
import persistencia.repositorios.RepositorioCuenta;
import persistencia.servicios.*;

public class GuiBanco<T> {

    private boolean running = true;
    private ServicioCuenta serviciosbanco;

    public GuiBanco() {
        serviciosbanco = new ServicioCuenta();
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

    private void seleccion(int seleccion) throws Exception {
        switch (seleccion) {
            case 1:
                crearPersona();
                break;
            case 2:
                listarPersonas();
                break;
            case 3:
                buscarPersona();
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

    private void realizarTranferencia() {
        // TODO Auto-generated method stub

    }

    private void crearPersona() {
        System.out.println("Crear banco");
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
        serviciosbanco.saveCuenta(cuenta);
        
        }
        else if(tipo.equals("2")){
        CuentaBancaria cuenta = new CuentaCorriente(numerocuenta, saldo, propietario);
        serviciosbanco.saveCuenta(cuenta);
        }
        else{
            System.out.println("Selecciono incorrectamente");
        }
        
        
        



    }

    private void listarPersonas() {
        System.out.println("Listando cuentas");
        List<CuentaBancaria> cuentas = serviciosbanco.getCuentas();

        for (CuentaBancaria cuenta : cuentas) {
            System.out.println(cuenta.getNumeroCuenta());
            System.out.println(cuenta.getSaldo());
        }
    }

    private void buscarPersona() {
        System.out.println("Buscar cuenta");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        try {
            CuentaBancaria cuentaencontrada = serviciosbanco.getCuenta(numerodecuenta);
            System.out.println("cuenta encontrada: " + cuentaencontrada.getNumeroCuenta());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void realizarDeposito() throws Exception {
        System.out.println("\n------ Realizar Depósito ------");
        System.out.print("Ingrese el número de cuenta: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        CuentaBancaria cuenta = serviciosbanco.getCuenta(numerodecuenta);
        if (cuenta == null) {
            System.out.println("La cuenta no existe");
            return;
        }
        System.out.print("Ingrese el monto a depositar: ");
        int monto = scanner.nextInt();
        if (monto <= 0) {
            System.out.println("El monto debe ser mayor que cero");
            return;
        }
        cuenta.depositar(monto);
        System.out.println("\nDepósito realizado exitosamente");
        System.out.println("Nuevo saldo: " + cuenta.getSaldo());
    }

    public void realizarRetiro() throws Exception {
        System.out.println("--realizar deposito------: ");
        System.out.print("Ingrese el número de cuenta: ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("numero de cuenta: ");
        String numerodecuenta = scanner.nextLine();
        CuentaBancaria cuenta = serviciosbanco.getCuenta(numerodecuenta);

        if (cuenta == null) {
            System.out.println("La cuenta no existe.");
            return;
        }
        System.out.println("Ingrese la cantidad a retirar: ");
        int monto = scanner.nextInt();;
        cuenta.retirar(monto);
        System.out.println("Se ha retirado $" + monto + " de la cuenta " + numerodecuenta);
        System.out.println("El saldo actual de la cuenta es $" + cuenta.getSaldo());

    }

    private void salir() {
        System.out.println("Salir");
        this.running = false;
    }
}
