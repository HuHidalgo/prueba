package com.unmsm.clinica.model.criterio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriterioBusquedaReporteExamenMedico {
	private String codigoAlumno;
	private Integer idCampania;
	private Integer bloque;
}