package com.unmsm.clinica.mapper;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaPsicologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoPsicologico;

public interface IConsultaExamenMedicoPsicologicoMapper {
	public List<ConsultaPsicologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoPsicologico criterioBusquedaAdministrativaExamenMedicoPsicologico);
}