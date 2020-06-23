package com.unmsm.clinica.controller.consulta.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaRadiologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoLaboratorio;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoRadiologico;
import com.unmsm.clinica.model.movimiento.ExamenMedicoRadiologico;
import com.unmsm.clinica.service.IConsultaExamenMedicoRadiologicoService;
import com.unmsm.clinica.service.IExamenMedicoRadiologicoService;

@RequestMapping("/consulta/radiologico")
public @RestController class ConsultaExamenMedicoRadiologicoRestController {
	private @Autowired IExamenMedicoRadiologicoService examenMedicoRadiologicoService;
	private @Autowired IConsultaExamenMedicoRadiologicoService consultaExamenMedicoRadiologicoService;

	@GetMapping(value = "/administrativa", params = "accion=buscarPorCriterioBusquedaAdministrativa")
	public List<ConsultaRadiologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoRadiologico criterioBusquedaAdministrativaExamenMedicoRadiologico) {
		return consultaExamenMedicoRadiologicoService
				.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoRadiologico);
	}

	@GetMapping(params = "accion=buscarResultadoRegularPorNumeroRegistroAnio")
	public List<ExamenMedicoRadiologico> buscarResultadoRegularPorNumeroRegistroAnio(
			CriterioBusquedaAdministrativaExamenMedicoLaboratorio criterioBusqueda) {
		return examenMedicoRadiologicoService.buscarResultadoRegularPorNumeroRegistroAnio(
				criterioBusqueda.getNumeroRegistro(), criterioBusqueda.getAnio());
	}
}