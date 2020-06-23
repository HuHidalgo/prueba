package com.unmsm.clinica.controller.reporte.exportacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteAtencionDiaria;
import com.unmsm.clinica.model.reporte.ReporteAtencionDiaria;
import com.unmsm.clinica.service.IReporteAtencionDiariaRegistradosService;
import com.unmsm.clinica.utilitario.ConstantesGenerales;
import com.unmsm.clinica.utilitario.ReporteUtilYarg;

@Vista
@RequestMapping("/reporte/atencion/diaria/registrados")
public @Controller class ReporteAtencionDiarioRegistradosExportacionController {
	private @Autowired IReporteAtencionDiariaRegistradosService reporteAtencionDiariaService;

	@GetMapping(value = "/laboratorio", params = "accion=exportar")
	public ModelAndView descargarReporteAtencionDiariaLaboratorio(ModelMap model, ModelAndView modelAndView,
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		List<ReporteAtencionDiaria> atencionDiariaDetalle = reporteAtencionDiariaService
				.buscarAtencionesDiariasLaboratorio(criterioBusquedaReporteAtencionDiaria);

		if (criterioBusquedaReporteAtencionDiaria.getInicioNumRegistro() == null
				&& criterioBusquedaReporteAtencionDiaria.getFinNumRegistro() == null) {
			criterioBusquedaReporteAtencionDiaria
					.setInicioNumRegistro(atencionDiariaDetalle.get(0).getNumeroRegistro());
			criterioBusquedaReporteAtencionDiaria
					.setFinNumRegistro(atencionDiariaDetalle.get(atencionDiariaDetalle.size() - 1).getNumeroRegistro());
		}

		Map<String, Object> params = new HashMap<>();
		params.put("param1", atencionDiariaDetalle);
		params.put("param2", criterioBusquedaReporteAtencionDiaria);

		model.addAttribute("rb_titulo", ReporteUtilYarg.buildReportBand("Titulo"));
		model.addAttribute("rb_criterioBusqueda",
				ReporteUtilYarg.buildReportBand("CriteriosBusqueda", "CriterioBusqueda", "parameter=param2 $", "json"));
		model.addAttribute("rb_total", ReporteUtilYarg.buildReportBand("Total"));
		model.addAttribute("rb_encabezado", ReporteUtilYarg.buildReportBand("Encabezado"));
		model.addAttribute("rb_datos", ReporteUtilYarg.buildReportBand("Datos", "Datos", "parameter=param1 $", "json"));

		model.addAttribute(ConstantesGenerales.PARAM_TEMPLATE, "reporteAtencionDiarioRegistradosLaboratorio");
		model.addAttribute(ConstantesGenerales.PARAM_NOMBRE_REPORTE, "Reporte de Atenciones Diarias de Laboratorio");
		model.addAttribute(ConstantesGenerales.PARAM_REPORTE_PARAMETERS, params);
		modelAndView = new ModelAndView("yargView", model);
		return modelAndView;
	}

	@GetMapping(value = "/radiologia", params = "accion=exportar")
	public ModelAndView descargarReporteAtencionDiariaRadiologia(ModelMap model, ModelAndView modelAndView,
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		List<ReporteAtencionDiaria> atencionDiariaDetalle = reporteAtencionDiariaService
				.buscarAtencionesDiariasRadiologia(criterioBusquedaReporteAtencionDiaria);

		if (criterioBusquedaReporteAtencionDiaria.getInicioNumRegistro() == null
				&& criterioBusquedaReporteAtencionDiaria.getFinNumRegistro() == null) {
			criterioBusquedaReporteAtencionDiaria
					.setInicioNumRegistro(atencionDiariaDetalle.get(0).getNumeroRegistro());
			criterioBusquedaReporteAtencionDiaria
					.setFinNumRegistro(atencionDiariaDetalle.get(atencionDiariaDetalle.size() - 1).getNumeroRegistro());
		}

		Map<String, Object> params = new HashMap<>();
		params.put("param1", atencionDiariaDetalle);
		params.put("param2", criterioBusquedaReporteAtencionDiaria);

		model.addAttribute("rb_titulo", ReporteUtilYarg.buildReportBand("Titulo"));
		model.addAttribute("rb_criterioBusqueda",
				ReporteUtilYarg.buildReportBand("CriteriosBusqueda", "CriterioBusqueda", "parameter=param2 $", "json"));
		model.addAttribute("rb_total", ReporteUtilYarg.buildReportBand("Total"));
		model.addAttribute("rb_encabezado", ReporteUtilYarg.buildReportBand("Encabezado"));
		model.addAttribute("rb_datos", ReporteUtilYarg.buildReportBand("Datos", "Datos", "parameter=param1 $", "json"));

		model.addAttribute(ConstantesGenerales.PARAM_TEMPLATE, "reporteAtencionDiarioRegistradosRadiologia");
		model.addAttribute(ConstantesGenerales.PARAM_NOMBRE_REPORTE, "Reporte de Atenciones Diarias de Radiologia");
		model.addAttribute(ConstantesGenerales.PARAM_REPORTE_PARAMETERS, params);
		modelAndView = new ModelAndView("yargView", model);
		return modelAndView;
	}

	@GetMapping(value = "/psicologia", params = "accion=exportar")
	public ModelAndView descargarReporteAtencionDiariaPsicologia(ModelMap model, ModelAndView modelAndView,
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		List<ReporteAtencionDiaria> atencionDiariaDetalle = reporteAtencionDiariaService
				.buscarAtencionesDiariasPsicologia(criterioBusquedaReporteAtencionDiaria);

		Map<String, Object> params = new HashMap<>();
		params.put("param1", atencionDiariaDetalle);
		params.put("param2", criterioBusquedaReporteAtencionDiaria);

		model.addAttribute("rb_titulo", ReporteUtilYarg.buildReportBand("Titulo"));
		model.addAttribute("rb_criterioBusqueda",
				ReporteUtilYarg.buildReportBand("CriteriosBusqueda", "CriterioBusqueda", "parameter=param2 $", "json"));
		model.addAttribute("rb_total", ReporteUtilYarg.buildReportBand("Total"));
		model.addAttribute("rb_encabezado", ReporteUtilYarg.buildReportBand("Encabezado"));
		model.addAttribute("rb_datos", ReporteUtilYarg.buildReportBand("Datos", "Datos", "parameter=param1 $", "json"));

		model.addAttribute(ConstantesGenerales.PARAM_TEMPLATE, "reporteAtencionDiarioRegistradosLaboratorio");
		model.addAttribute(ConstantesGenerales.PARAM_NOMBRE_REPORTE, "Reporte de Atenciones Diarias de Laboratorio");
		model.addAttribute(ConstantesGenerales.PARAM_REPORTE_PARAMETERS, params);
		modelAndView = new ModelAndView("yargView", model);
		return modelAndView;
	}
}