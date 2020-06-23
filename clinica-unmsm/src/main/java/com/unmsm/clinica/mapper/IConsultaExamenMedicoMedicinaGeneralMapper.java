package com.unmsm.clinica.mapper;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaMedicinaGeneralAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral;

public interface IConsultaExamenMedicoMedicinaGeneralMapper {
	public List<ConsultaMedicinaGeneralAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral criterioBusquedaAdministrativaExamenMedicoMedicinaGeneral);
}