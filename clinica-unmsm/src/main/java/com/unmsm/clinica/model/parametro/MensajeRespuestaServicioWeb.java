package com.unmsm.clinica.model.parametro;

import lombok.Data;

@Data
public class MensajeRespuestaServicioWeb {
	private int codigoRespuesta;
	private int cantidadRegistros;
	private String descripcion;
}