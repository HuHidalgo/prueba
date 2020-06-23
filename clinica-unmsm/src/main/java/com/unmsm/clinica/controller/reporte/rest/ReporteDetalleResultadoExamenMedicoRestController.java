package com.unmsm.clinica.controller.reporte.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteDetalleResultadoAlumno;
import com.unmsm.clinica.model.movimiento.Antecedente;
import com.unmsm.clinica.model.reporte.ReporteDetalleResultadoAlumnoIngresante;
import com.unmsm.clinica.model.reporte.ReporteDetalleResultadoAlumnoRegular;
import com.unmsm.clinica.service.IReporteDetalleService;
import com.unmsm.clinica.service.excepcion.BadRequestException;
import com.unmsm.clinica.utilitario.ValidatorUtil;

@RequestMapping("/reporte/detalle")
public @RestController class ReporteDetalleResultadoExamenMedicoRestController {
	private @Autowired IReporteDetalleService reporteDetalleService;
	static List<ReporteDetalleResultadoAlumnoIngresante> listaIngresantes = new ArrayList<ReporteDetalleResultadoAlumnoIngresante>();

	@GetMapping(value = "/resultado/examenMedico/regular", params = "accion=buscar")
	public List<ReporteDetalleResultadoAlumnoRegular> buscarResultadoExamenMedicoRegular(
			@Validated CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		return reporteDetalleService.buscarResultadoExamenMedicoRegular(criterioBusquedaReporteDetalleResultadoAlumno);
	}

	@GetMapping(value = "/resultado/examenMedico/ingresante", params = "accion=buscar")
	public List<ReporteDetalleResultadoAlumnoIngresante> buscarResultadoExamenMedicoIngresante(
			@Validated CriterioBusquedaReporteDetalleResultadoAlumno criterioBusquedaReporteDetalleResultadoAlumno,
			Errors error) {
		listaIngresantes = new ArrayList<ReporteDetalleResultadoAlumnoIngresante>();
		List<ReporteDetalleResultadoAlumnoIngresante> Ingresantes = new ArrayList<ReporteDetalleResultadoAlumnoIngresante>();
		List<ReporteDetalleResultadoAlumnoIngresante> Ingresantes2 = new ArrayList<ReporteDetalleResultadoAlumnoIngresante>();
		ReporteDetalleResultadoAlumnoIngresante reporte = new ReporteDetalleResultadoAlumnoIngresante();
		String codigoAlumno = "";
		int flag = 0;
		Ingresantes = reporteDetalleService
				.buscarResultadoExamenMedicoIngresante(criterioBusquedaReporteDetalleResultadoAlumno);

		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}

		if (!criterioBusquedaReporteDetalleResultadoAlumno.getCodigoAlumno().equals("")) {
			for (ReporteDetalleResultadoAlumnoIngresante ingresante : Ingresantes) {
				if (flag == 0) {
					reporte = ingresante;
					flag = 1;
				}
				if (ingresante.getIdTipoAntecedente() != null) {
					if (ingresante.getIdTipoAntecedente().equals("B")) {
						reporte.setAlergia(((reporte.getAlergia() == null) ? "" : reporte.getAlergia())
								+ ((reporte.getAlergia() == null || reporte.getAlergia() == "") ? "" : ", ")
								+ ((ingresante.getDescCie10() == null) ? "" : ingresante.getDescCie10()));
					}
					if (ingresante.getIdTipoAntecedente().equals("A")) {
						reporte.setDiagnostico(((reporte.getDiagnostico() == null) ? "" : reporte.getDiagnostico())
								+ ((reporte.getDiagnostico() == null) ? "" : ", ")
								+ ((ingresante.getIdCie10() == null) ? "" : ingresante.getIdCie10()));
					}
				}
			}
			if (reporte.getDiagnostico() != null) {
				reporte.setResultado("C");
			} else {
				reporte.setResultado("N");
			}

			if (reporte.getAlergia() == null || reporte.getAlergia().equals("")) {
				reporte.setAlergia("No Presenta");
			}
			Ingresantes2.add(reporte);
		} else {
			for (ReporteDetalleResultadoAlumnoIngresante ingresante : Ingresantes) {
				if (!ingresante.getCodigoAlumno().equals(codigoAlumno)) {
					if (flag == 0) {
						if (reporte.getCodigoAlumno() != null) {
							if (reporte.getDiagnostico() != null) {
								reporte.setResultado("C");
							} else {
								reporte.setResultado("N");
							}

							if (reporte.getAlergia() == null || reporte.getAlergia().equals("")) {
								reporte.setAlergia("No Presenta");
							}
							Ingresantes2.add(reporte);
						}
						reporte = ingresante;
					}
					if (ingresante.getIdTipoAntecedente() != null) {
						if (ingresante.getIdTipoAntecedente().equals("B")) {
							reporte.setAlergia(((reporte.getAlergia() == null) ? "" : reporte.getAlergia())
									+ ((reporte.getAlergia() == null || reporte.getAlergia().equals("")) ? "" : ", ")
									+ ((ingresante.getDescCie10() == null) ? "" : ingresante.getDescCie10()));
						}
						if (ingresante.getIdTipoAntecedente().equals("A")) {
							reporte.setDiagnostico(((reporte.getDiagnostico() == null) ? "" : reporte.getDiagnostico())
									+ ((reporte.getDiagnostico() == null) ? "" : ", ")
									+ ((ingresante.getIdCie10() == null) ? "" : ingresante.getIdCie10()));
						}
					}
					codigoAlumno = ingresante.getCodigoAlumno();
				} else {
					if (ingresante.getIdTipoAntecedente() != null) {
						if (ingresante.getIdTipoAntecedente().equals("B")) {
							reporte.setAlergia(((reporte.getAlergia() == null) ? "" : reporte.getAlergia())
									+ ((reporte.getAlergia() == null || reporte.getAlergia().equals("")) ? "" : ", ")
									+ ((ingresante.getDescCie10() == null) ? "" : ingresante.getDescCie10()));
						}
						if (ingresante.getIdTipoAntecedente().equals("A")) {
							reporte.setDiagnostico(((reporte.getDiagnostico() == null) ? "" : reporte.getDiagnostico())
									+ ((reporte.getDiagnostico() == null) ? "" : ", ")
									+ ((ingresante.getIdCie10() == null) ? "" : ingresante.getIdCie10()));
						}
					}
				}
			}
			if (reporte.getDiagnostico() != null) {
				reporte.setResultado("C");
			} else {
				reporte.setResultado("N");
			}

			if (reporte.getAlergia() == null || reporte.getAlergia().equals("")) {
				reporte.setAlergia("No Presenta");
			}
			Ingresantes2.add(reporte);
		}
		if (Ingresantes.isEmpty())
			return Ingresantes;
		else {
			for (ReporteDetalleResultadoAlumnoIngresante ingresante : Ingresantes2) {
				if (ingresante.getFechaAtencionExamenLaboratorio() != null
						&& ingresante.getFechaAtencionExamenRadiologico() != null
						&& ingresante.getFechaAtencionExamenPsicologico() != null
						&& ingresante.getFechaAtencionExamenOdontologia() != null
						&& ingresante.getFechaAtencionExamenMedicoGeneral() != null) {
					listaIngresantes.add(ingresante);
				}
			}
			return Ingresantes2;
		}
	}

	public List<ReporteDetalleResultadoAlumnoIngresante> listarAlumnosIngresantes() {
		return listaIngresantes;
	}
}