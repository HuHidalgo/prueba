package com.unmsm.clinica.model.reporte;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteAtencionDiaria {
	private Integer numeroRegistro;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "EST")
	private Date fechaGeneracionNumeroRegistro;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "EST")
	private Date soloFechaGeneracionNumeroRegistro;

	private String codigoAlumno;
	private String tipoAlumno;
	private String descripcionTipoAlumno;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private String resultado;
	private String descripcionResultado;
	private Integer codigoFacultad;
	private String descripcionFacultad;
	private Integer codigoEscuela;
	private String descripcionEscuela;
	private Integer idCampania;
	private int cantidad;
	private int cantidadObservados;
	private int cantidadNoObservados;

	private Double hemoglobina;
	private String descripcionResultadoHemoglobina;
	private String idRPR;
	private String descripcionRPR;
	private String dilucion;
	private String idHemograma;
	private String descripcionHemograma;
}