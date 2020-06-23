package com.unmsm.clinica.model.reporte;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReporteDetalleResultadoAlumnoIngresante {
	private String codigoAlumno;
	private String tipoAlumno;
	private String descripcionTipoAlumno;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private String idSexo;
	private String descripcionSexo;
	private int edad;
	private Integer codigoFacultad;
	private String descripcionFacultad;
	private Integer codigoEscuela;
	private String descripcionEscuela;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "EST")
	private Date fechaAtencionExamenLaboratorio;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "EST")
	private Date fechaAtencionExamenRadiologico;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "EST")
	private Date fechaAtencionExamenPsicologico;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "EST")
	private Date fechaAtencionExamenOdontologia;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "EST")
	private Date fechaAtencionExamenMedicoGeneral;
	private String idGrupoSanguineo;
	private String descGrupoSanguineo;
	private String idRPR;
	private String descripcionRPR;
	private String dilucion;
	private String idFactorRh;
	private String descFactorRh;
	private String idResultadoRadiologico;
	private String descripcionResultadoRadiologico;
	private String idResultadoPsicologico;
	private String descripcionResultadoPsicologico;

	private String idCie10;
	private String descCie10;
	private String idTipoAntecedente;

	private String diagnostico;
	private String alergia;
	private String resultado;
}