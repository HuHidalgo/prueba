package com.unmsm.clinica.mapper;

import java.util.List;

import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteDetalleResultadoAlumno;
import com.unmsm.clinica.model.reporte.ReporteDetalleResultadoAlumnoIngresante;
import com.unmsm.clinica.model.reporte.ReporteDetalleResultadoAlumnoRegular;

public interface IReporteDetalleMapper {
	public List<ReporteDetalleResultadoAlumnoRegular> buscarResultadoExamenMedicoRegular(
			CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno);

	public List<ReporteDetalleResultadoAlumnoIngresante> buscarResultadoExamenMedicoIngresante(
			CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno);
}