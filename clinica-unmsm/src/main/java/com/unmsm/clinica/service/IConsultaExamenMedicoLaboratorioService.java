package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaLaboratorioAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoLaboratorio;

public interface IConsultaExamenMedicoLaboratorioService {
	public List<ConsultaLaboratorioAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoLaboratorio criterioBusquedaAdministrativaExamenMedicoLaboratorio);
}