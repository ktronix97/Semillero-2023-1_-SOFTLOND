package com.semillero.entidades;

public class Transacciones {
    protected int id;
    protected String fecha;
    protected String hora;
    protected String tipo_transaccion;
    protected double monto;
    protected int id_cuenta;
    protected String tipo_cuenta_destino;
 
    public Transacciones(int id, String fecha, String hora, String tipo_transaccion, double monto, int id_cuenta,
            String tipo_cuenta_destino) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo_transaccion = tipo_transaccion;
        this.monto = monto;
        this.id_cuenta = id_cuenta;
        this.tipo_cuenta_destino = tipo_cuenta_destino;
    }
    

	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getTipoTransaccion() {
        return tipo_transaccion;
    }
    public void setTipoTransaccion(String tipo_transaccion) {
        this.tipo_transaccion = tipo_transaccion;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public int getId_cuenta() {
        return id_cuenta;
    }
    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }
    public String getTipo_cuenta_destino() {
        return tipo_cuenta_destino;
    }
    public void setTipo_cuenta_destino(String tipo_cuenta_destino) {
        this.tipo_cuenta_destino = tipo_cuenta_destino;
    }
        
}
