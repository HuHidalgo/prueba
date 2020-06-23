package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaPsicologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoPsicologico;

public interface IConsultaExamenMedicoPsicologicoService {
	public List<ConsultaPsicologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoPsicologico criterioBusquedaAdministrativaExamenMedicoPsicologico);
}