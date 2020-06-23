package com.unmsm.clinica.mapper;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaOdontologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoOdontologico;

public interface IConsultaExamenMedicoOdontologicoMapper {
	public List<ConsultaOdontologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusquedaAdministrativaExamenMedicoOdontologico);
}