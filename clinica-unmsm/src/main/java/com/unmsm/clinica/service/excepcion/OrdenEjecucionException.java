package com.unmsm.clinica.service.excepcion;

public class OrdenEjecucionException extends SimpException {
	private static final long serialVersionUID = 1L;

	public OrdenEjecucionException(String mensaje) {
		super(mensaje);
	}
}