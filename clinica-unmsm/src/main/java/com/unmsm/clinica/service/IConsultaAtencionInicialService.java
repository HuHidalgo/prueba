package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.consulta.ConsultaAtencionInicial;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAtencionInicial;

public interface IConsultaAtencionInicialService {
	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialLaboratorioPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda);

	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialPsicologiaPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda);

	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialRadiologiaPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda);

	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialTriajePorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda);

	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialOdontologiaPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda);

	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialMedicinaGeneralPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda);
}