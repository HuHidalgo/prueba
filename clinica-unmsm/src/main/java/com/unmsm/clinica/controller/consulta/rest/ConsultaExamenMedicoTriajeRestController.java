package com.unmsm.clinica.controller.consulta.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaTriajeAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoTriaje;
import com.unmsm.clinica.model.movimiento.ExamenMedicoTriaje;
import com.unmsm.clinica.service.IConsultaExamenMedicoTriajeService;
import com.unmsm.clinica.service.IExamenMedicoTriajeService;

@RequestMapping("/consulta/triaje")
public @RestController class ConsultaExamenMedicoTriajeRestController {
	private @Autowired IConsultaExamenMedicoTriajeService consultaTriajeService;
	private @Autowired IExamenMedicoTriajeService examenMedicoTriajeService;

	@GetMapping(value = "/administrativa", params = "accion=buscarPorCriterioBusquedaAdministrativa")
	public List<ConsultaTriajeAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoTriaje criterioBusquedaAdministrativaTriaje) {
		return consultaTriajeService.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaTriaje);
	}

	@GetMapping(params = "accion=buscarResultadoRegularPorNumeroRegistro")
	public List<ExamenMedicoTriaje> buscarResultadoRegularPorNumeroRegistro(
			CriterioBusquedaAdministrativaExamenMedicoTriaje criterioBusqueda) {
		return examenMedicoTriajeService.buscarResultadoRegularPorNumeroRegistro(criterioBusqueda.getNumeroRegistro());
	}

}