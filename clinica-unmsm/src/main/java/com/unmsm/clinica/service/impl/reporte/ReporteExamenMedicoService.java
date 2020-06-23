package com.unmsm.clinica.service.impl.reporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IReporteExamenMedicoMapper;
import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteExamenMedico;
import com.unmsm.clinica.model.reporte.ReporteExamenMedicoIngresante;
import com.unmsm.clinica.model.reporte.ReporteExamenMedicoRegular;
import com.unmsm.clinica.service.IReporteExamenMedicoService;
import com.unmsm.clinica.service.excepcion.ValorNoEncontradoException;
import com.unmsm.clinica.utilitario.ConstantesExcepciones;

@Service
public class ReporteExamenMedicoService implements IReporteExamenMedicoService {
	private @Autowired IReporteExamenMedicoMapper reporteExamenMedicoMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public ReporteExamenMedicoRegular buscarReporteExamenMedicoRegular(
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico) {
		List<ReporteExamenMedicoRegular> reportesExamenMedicoRegular = reporteExamenMedicoMapper
				.buscarReporteExamenMedicoRegular(criterioBusquedaReporteExamenMedico);
		if (reportesExamenMedicoRegular.isEmpty()) {
			throw new ValorNoEncontradoException(ConstantesExcepciones.NO_EXISTE_REPORTE_EXAMEN_MEDICO_REGULAR);
		}
		return reportesExamenMedicoRegular.get(0);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ReporteExamenMedicoIngresante> buscarReporteExamenMedicoIngresante(
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico) {
		List<ReporteExamenMedicoIngresante> reportesExamenMedicoIngresante = reporteExamenMedicoMapper
				.buscarReporteExamenMedicoIngresante(criterioBusquedaReporteExamenMedico);
		if (reportesExamenMedicoIngresante.isEmpty()) {
			throw new ValorNoEncontradoException(ConstantesExcepciones.NO_EXISTE_REPORTE_EXAMEN_MEDICO_INGRESANTE);
		}
		return reportesExamenMedicoIngresante;
	}
}