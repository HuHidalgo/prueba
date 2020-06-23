package com.unmsm.clinica.model.criterio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral {
	private Integer numeroRegistro;
	private String anio;
	private String codigoAlumno;
	private String tipoAlumno;
	private String idEstadoExamenMedico;
	private Integer idCampania;
	private Integer codigoFacultad;
	private Integer codigoEscuela;

	private Integer tipoBusqueda;
	private String idCie10;
	private String idTipoAntecedente;

}