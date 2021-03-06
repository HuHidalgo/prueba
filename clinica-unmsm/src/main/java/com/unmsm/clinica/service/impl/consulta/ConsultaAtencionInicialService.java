package com.unmsm.clinica.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IConsultaAtencionInicialMapper;
import com.unmsm.clinica.model.consulta.ConsultaAtencionInicial;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAtencionInicial;
import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteAtencionDiaria;
import com.unmsm.clinica.model.reporte.ReporteAtencionDiaria;
import com.unmsm.clinica.service.IConsultaAtencionInicialService;

@Service
public class ConsultaAtencionInicialService implements IConsultaAtencionInicialService {
	private @Autowired IConsultaAtencionInicialMapper consultaAtencionInicialMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialLaboratorioPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda) {
		return this.consultaAtencionInicialMapper
				.buscarConsultaAtencionInicialLaboratorioPorCriterioBusqueda(criterioBusqueda);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialPsicologiaPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda) {
		return this.consultaAtencionInicialMapper
				.buscarConsultaAtencionInicialPsicologiaPorCriterioBusqueda(criterioBusqueda);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialRadiologiaPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda) {
		return this.consultaAtencionInicialMapper
				.buscarConsultaAtencionInicialRadiologiaPorCriterioBusqueda(criterioBusqueda);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialTriajePorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda) {
		return this.consultaAtencionInicialMapper
				.buscarConsultaAtencionInicialTriajePorCriterioBusqueda(criterioBusqueda);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialOdontologiaPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda) {
		return this.consultaAtencionInicialMapper
				.buscarConsultaAtencionInicialOdontologiaPorCriterioBusqueda(criterioBusqueda);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaAtencionInicial> buscarConsultaAtencionInicialMedicinaGeneralPorCriterioBusqueda(
			CriterioBusquedaAtencionInicial criterioBusqueda) {
		return this.consultaAtencionInicialMapper
				.buscarConsultaAtencionInicialMedicinaGeneralPorCriterioBusqueda(criterioBusqueda);
	}
}