package com.unmsm.clinica.mapper;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaRadiologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoRadiologico;

public interface IConsultaExamenMedicoRadiologicoMapper {
	public List<ConsultaRadiologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoRadiologico criterioBusquedaAdministrativaExamenMedicoRadiologico);
}