package com.unmsm.clinica.mapper;

import java.util.List;

import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteExamenMedico;
import com.unmsm.clinica.model.reporte.ReporteExamenMedicoIngresante;
import com.unmsm.clinica.model.reporte.ReporteExamenMedicoRegular;

public interface IReporteExamenMedicoMapper {
	public List<ReporteExamenMedicoRegular> buscarReporteExamenMedicoRegular(
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico);

	public List<ReporteExamenMedicoIngresante> buscarReporteExamenMedicoIngresante(
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico);
}