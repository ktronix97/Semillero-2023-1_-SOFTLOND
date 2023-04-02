package com.semillero.entidades;

public   class Cuentas {
    protected int idCuenta;
	protected String numeroCuenta;
	protected  double saldo;
	protected  Integer idUsuario;
	protected String tipo;

    public Cuentas( int idCuenta, String numeroCuenta, double saldo, Integer idUsuario, String tipo) {
        this.idCuenta = idCuenta;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.idUsuario = idUsuario;
        this.tipo = tipo;
    }

    public void depositar(double monto) {
        double montoConAdicional = monto;
        // Solo se aplica el adicional para los primeros dos depósitos.
        if (this.saldo == 0 && monto > 0) {
            montoConAdicional += (monto * 0.005);
        } else if (this.saldo > 0 && monto > 0) {
            montoConAdicional += (monto * 0.005);
            this.saldo += montoConAdicional;
        }
        this.saldo += monto;
    }

    public void retirar(double monto) {
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
        }
    }
	
    public void setId_cuenta(int id_cuenta) {
        this.idCuenta = id_cuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Integer getId_usuario() {
        return idUsuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.idUsuario = id_usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}


	
	
}
