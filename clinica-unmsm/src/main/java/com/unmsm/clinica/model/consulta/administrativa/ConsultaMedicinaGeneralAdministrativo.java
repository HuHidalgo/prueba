package com.unmsm.clinica.model.consulta.administrativa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaMedicinaGeneralAdministrativo {

	private String anio;
	private String codigoAlumno;
	private String tipoAlumno;
	private String descripcionTipoAlumno;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private String idEstadoExamenMedico;
	private String descripcionEstadoExamenMedico;
	private Integer idCampania;
	private String descripcionCampania;
	private int edad;
	private String idSexo;
	private String descripcionSexo;
	private String descripcionFacultad;
	private String descripcionEscuela;
	private Integer numeroRegistro;

	private String idCie10;
	private String idTipoAntecedente;
}
