package com.unmsm.clinica.service.excepcion;

public class SimpException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SimpException(String mensaje) {
		super(mensaje);
	}
}