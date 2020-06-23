package com.unmsm.clinica.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unmsm.clinica.mapper.IReporteAtencionDiariaRegistradosMapper;
import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteAtencionDiaria;
import com.unmsm.clinica.model.reporte.ReporteAtencionDiaria;
import com.unmsm.clinica.service.IReporteAtencionDiariaRegistradosService;

@Service
public class ReporteAtencionDiariaRegistradosService implements IReporteAtencionDiariaRegistradosService {
	private @Autowired IReporteAtencionDiariaRegistradosMapper reporteAtencionDiariaMapper;

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
