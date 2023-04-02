package com.semillero.exepciones;

public class CuentaInexistenteException extends Exception {

	public CuentaInexistenteException(String mensaje) {
		super(mensaje);
	}
}
