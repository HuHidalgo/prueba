package com.unmsm.clinica.controller.reporte.exportacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unmsm.clinica.controller.excepcion.anotacion.Vista;
import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteExamenMedico;
import com.unmsm.clinica.model.reporte.ReporteDetalleResultadoAlumnoIngresante;
import com.unmsm.clinica.model.reporte.ReporteExamenMedicoIngresante;
import com.unmsm.clinica.service.IMedicoService;
import com.unmsm.clinica.service.IReporteExamenMedicoService;
import com.unmsm.clinica.utilitario.ConstantesGenerales;

@Vista
@RequestMapping("reporte/examenMedico")
public @Controller class ReporteExamenMedicoExportacionController {
	private @Autowired IMedicoService medicoService;
	private @Autowired IReporteExamenMedicoService reporteExamenMedicoService;

	@GetMapping(params = "accion=exportarRegular")
	public ModelAndView descargarReporteExamenMedicoRegular(ModelMap model, ModelAndView modelAndView,
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico) {
		Map<String, Object> params = new HashMap<>();
		params.put("reporte",
				reporteExamenMedicoService.buscarReporteExamenMedicoRegular(criterioBusquedaReporteExamenMedico));
		params.put("medico", medicoService.buscarDirector());
		params.put("fechaGeneracion", new Date());
		model.addAttribute(ConstantesGenerales.PARAM_REPORTE_PARAMETERS, params);
		model.addAttribute(ConstantesGenerales.PARAM_TEMPLATE, "reporteCertificadoMedicoRegulares");
		model.addAttribute(ConstantesGenerales.PARAM_NOMBRE_REPORTE, "Reporte de Certificado Médico Regulares");
		modelAndView = new ModelAndView("xdocView", model);
		return modelAndView;
	}

	@GetMapping(params = "accion=exportarIngresante")
	public ModelAndView descargarReporteExamenMedicoIngresante(ModelMap model, ModelAndView modelAndView,
			CriterioBusquedaReporteExamenMedico criterioBusquedaReporteExamenMedico) {
		List<ReporteExamenMedicoIngresante> Ingresantes = new ArrayList<ReporteExamenMedicoIngresante>();
		List<ReporteExamenMedicoIngresante> Ingresantes2 = new ArrayList<ReporteExamenMedicoIngresante>();
		ReporteExamenMedicoIngresante reporte = new ReporteExamenMedicoIngresante();
		int flag = 0;
		Ingresantes = reporteExamenMedicoService
				.buscarReporteExamenMedicoIngresante(criterioBusquedaReporteExamenMedico);
		for (ReporteExamenMedicoIngresante ingresante : Ingresantes) {
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
		if (reporte.getDiagnostico() != null)
			reporte.setResultado("C");
		else
			reporte.setResultado("N");

		if (reporte.getAlergia() == null || reporte.getAlergia().equals(""))
			reporte.setAlergia("No Presenta");

		Ingresantes2.add(reporte);
		Map<String, Object> params = new HashMap<>();
		params.put("reporte", Ingresantes2.get(0));
		params.put("medico", medicoService.buscarDirector());
		params.put("fechaGeneracion", new Date());
		model.addAttribute(ConstantesGenerales.PARAM_REPORTE_PARAMETERS, params);
		model.addAttribute(ConstantesGenerales.PARAM_TEMPLATE, "reporteCertificadoMedicoIngresantes");
		model.addAttribute(ConstantesGenerales.PARAM_NOMBRE_REPORTE, "Reporte de Certificado Médico Ingresantes");
		modelAndView = new ModelAndView("xdocView", model);
		return modelAndView;
	}
}