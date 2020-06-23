package com.unmsm.clinica.model.criterio;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioBusquedaReporteAtencionDiaria {
	private String verbo;
	private String idTipoExamenMedico;
	private String codigoAlumno;
	private String idTurno;
	private String descripcionTurno;
	private String tipoReporte;
	private Integer idCampania;
	private Integer codigoFacultad;
	private String descripcionFacultad;

	private Integer inicioNumRegistro;
	private Integer finNumRegistro;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaInicio;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaFin;

	private String descripcionExamenMedico;
	private String descripcionCampania;
	private String descripcionFechaGeneracionNumeroRegistro;
}