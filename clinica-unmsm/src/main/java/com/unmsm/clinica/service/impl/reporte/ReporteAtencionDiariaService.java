package com.unmsm.clinica.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IReporteAtencionDiariaMapper;
import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteAtencionDiaria;
import com.unmsm.clinica.model.reporte.ReporteAtencionDiaria;
import com.unmsm.clinica.service.IReporteAtencionDiariaService;
import com.unmsm.clinica.utilitario.StringsUtils;

@Service
public class ReporteAtencionDiariaService implements IReporteAtencionDiariaService {
	private @Autowired IReporteAtencionDiariaMapper reporteAtencionDiariaMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ReporteAtencionDiaria> buscarAtencionesDiarias(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		String verbo = StringsUtils.concatenarCadena(criterioBusquedaReporteAtencionDiaria.getIdTipoExamenMedico(), "_",
				criterioBusquedaReporteAtencionDiaria.getTipoReporte());
		criterioBusquedaReporteAtencionDiaria.setVerbo(verbo);
		return reporteAtencionDiariaMapper.buscarAtencionesDiarias(criterioBusquedaReporteAtencionDiaria);
	}

	@Override
	public List<ReporteAtencionDiaria> buscarAtencionesDiariasLaboratorio(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		return this.reporteAtencionDiariaMapper
				.buscarAtencionesDiariasLaboratorio(criterioBusquedaReporteAtencionDiaria);
	}

	@Override
	public List<ReporteAtencionDiaria> buscarAtencionesDiariasRadiologia(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		return this.reporteAtencionDiariaMapper
				.buscarAtencionesDiariasRadiologia(criterioBusquedaReporteAtencionDiaria);
	}

	@Override
	public List<ReporteAtencionDiaria> buscarAtencionesDiariasPsicologia(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		return this.reporteAtencionDiariaMapper
				.buscarAtencionesDiariasPsicologia(criterioBusquedaReporteAtencionDiaria);
	}
}
