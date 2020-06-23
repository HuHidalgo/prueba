package com.unmsm.clinica.controller.reporte.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.criterio.CriterioBusquedaReporteAtencionDiaria;
import com.unmsm.clinica.model.reporte.ReporteAtencionDiaria;
import com.unmsm.clinica.service.IReporteAtencionDiariaRegistradosService;

@RequestMapping("/reporte/atencion/diaria/registrados")
public @RestController class ReporteAtencionDiarioRegistradosRestController {
	private @Autowired IReporteAtencionDiariaRegistradosService reporteAtencionDiariaService;

	@GetMapping(value = "/laboratorio", params = "accion=buscar")
	public List<ReporteAtencionDiaria> buscarAtencionesDiariasLaboratorio(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		return reporteAtencionDiariaService.buscarAtencionesDiariasLaboratorio(criterioBusquedaReporteAtencionDiaria);
	}

	@GetMapping(value = "/radiologia", params = "accion=buscar")
	public List<ReporteAtencionDiaria> buscarAtencionesDiariasRadiologia(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		return reporteAtencionDiariaService.buscarAtencionesDiariasRadiologia(criterioBusquedaReporteAtencionDiaria);
	}

	@GetMapping(value = "/psicologia", params = "accion=buscar")
	public List<ReporteAtencionDiaria> buscarAtencionesDiariasPsicologia(
			CriterioBusquedaReporteAtencionDiaria criterioBusquedaReporteAtencionDiaria) {
		return reporteAtencionDiariaService.buscarAtencionesDiariasPsicologia(criterioBusquedaReporteAtencionDiaria);
	}
}