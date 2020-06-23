package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaMedicinaGeneralAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral;

public interface IConsultaExamenMedicoMedicinaGeneralService {
	public List<ConsultaMedicinaGeneralAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral criterioBusquedaAdministrativaExamenMedicoMedicinaGeneral);
}