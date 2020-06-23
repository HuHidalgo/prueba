package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaOdontologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoOdontologico;

public interface IConsultaExamenMedicoOdontologicoService {
	public List<ConsultaOdontologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusquedaAdministrativaExamenMedicoOdontologico);
}