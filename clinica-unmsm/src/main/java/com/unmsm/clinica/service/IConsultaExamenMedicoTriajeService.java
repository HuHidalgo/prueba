package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaTriajeAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoTriaje;

public interface IConsultaExamenMedicoTriajeService {
	public List<ConsultaTriajeAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoTriaje criterioBusquedaAdministrativaTriaje);
}