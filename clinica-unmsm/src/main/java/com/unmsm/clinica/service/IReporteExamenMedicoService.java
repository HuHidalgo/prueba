package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteExamenMedico;
import com.unmsm.clinica.model.reporte.ReporteExamenMedicoIngresante;
import com.unmsm.clinica.model.reporte.ReporteExamenMedicoRegular;

public interface IReporteExamenMedicoService {
	public ReporteExamenMedicoRegular buscarReporteExamenMedicoRegular(
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico);

	public List<ReporteExamenMedicoIngresante> buscarReporteExamenMedicoIngresante(
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico);
}