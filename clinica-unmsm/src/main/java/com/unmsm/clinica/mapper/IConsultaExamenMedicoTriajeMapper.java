package com.unmsm.clinica.mapper;

import java.util.List;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaTriajeAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoTriaje;

public interface IConsultaExamenMedicoTriajeMapper {
	public List<ConsultaTriajeAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoTriaje criterioBusquedaAdministrativaTriaje);
}