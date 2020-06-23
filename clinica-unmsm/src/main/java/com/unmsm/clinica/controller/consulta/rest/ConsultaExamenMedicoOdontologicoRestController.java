package com.unmsm.clinica.controller.consulta.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaOdontologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoOdontologico;
import com.unmsm.clinica.model.movimiento.ExamenMedicoOdontologico;
import com.unmsm.clinica.service.IConsultaExamenMedicoOdontologicoService;
import com.unmsm.clinica.service.IExamenMedicoOdontologicoService;

@RequestMapping("/consulta/odontologia")
public @RestController class ConsultaExamenMedicoOdontologicoRestController {
	private @Autowired IExamenMedicoOdontologicoService examenMedicoOdontologicoService;
	private @Autowired IConsultaExamenMedicoOdontologicoService consultaExamenMedicoOdontologicoService;

	@GetMapping(value = "/administrativa", params = "accion=buscarPorCriterioBusquedaAdministrativa")
	public List<ConsultaOdontologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusquedaAdministrativaExamenMedicoOdontologico) {
		return consultaExamenMedicoOdontologicoService
				.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoOdontologico);
	}

	@GetMapping(params = "accion=buscarResultadoRegularPorNumeroRegistro")
	public List<ExamenMedicoOdontologico> buscarResultadoPorNumeroRegistro(
			CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusqueda) {
		return examenMedicoOdontologicoService.buscarResultadoPorNumeroRegistro(criterioBusqueda.getNumeroRegistro());
	}
}