package com.unmsm.clinica.controller.consulta.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.consulta.administrativa.ConsultaMedicinaGeneralAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral;
import com.unmsm.clinica.model.movimiento.ExamenMedicoMedicinaGeneral;
import com.unmsm.clinica.service.IConsultaExamenMedicoMedicinaGeneralService;
import com.unmsm.clinica.service.IExamenMedicoMedicinaGeneralService;

@RequestMapping("/consulta/medicinaGeneral")
public @RestController class ConsultaExamenMedicoMedicinaGeneralRestController {
	private @Autowired IExamenMedicoMedicinaGeneralService examenMedicoMedicinaGeneralService;
	private @Autowired IConsultaExamenMedicoMedicinaGeneralService consultaExamenMedicoMedicinaGeneralService;

	@GetMapping(value = "/administrativa", params = "accion=buscarPorCriterioBusquedaAdministrativa")
	public List<ConsultaMedicinaGeneralAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral criterioBusquedaAdministrativaExamenMedicoMedicinaGeneral) {
		return this.consultaExamenMedicoMedicinaGeneralService
				.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoMedicinaGeneral);
	}

	@GetMapping(params = "accion=buscarResultadoRegularPorNumeroRegistro")
	public List<ExamenMedicoMedicinaGeneral> buscarResultadoPorNumeroRegistro(
			CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral criterioBusqueda) {
		return examenMedicoMedicinaGeneralService
				.buscarResultadoPorNumeroRegistro(criterioBusqueda.getNumeroRegistro());
	}

}