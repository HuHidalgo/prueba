package com.unmsm.clinica.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IReporteDetalleMapper;
import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteDetalleResultadoAlumno;
import com.unmsm.clinica.model.reporte.ReporteDetalleResultadoAlumnoIngresante;
import com.unmsm.clinica.model.reporte.ReporteDetalleResultadoAlumnoRegular;
import com.unmsm.clinica.service.IReporteDetalleService;

@Service
public class ReporteDetalleService implements IReporteDetalleService {
	private @Autowired IReporteDetalleMapper reporteDetalleMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ReporteDetalleResultadoAlumnoRegular> buscarResultadoExamenMedicoRegular(
			CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno) {
		return reporteDetalleMapper.buscarResultadoExamenMedicoRegular(criterioBusquedaReporteDetalleResultadoAlumno);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ReporteDetalleResultadoAlumnoIngresante> buscarResultadoExamenMedicoIngresante(
			CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno) {
		return reporteDetalleMapper
				.buscarResultadoExamenMedicoIngresante(criterioBusquedaReporteDetalleResultadoAlumno);
	}
}