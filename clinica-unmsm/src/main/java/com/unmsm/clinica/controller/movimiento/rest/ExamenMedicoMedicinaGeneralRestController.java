package com.unmsm.clinica.controller.movimiento.rest;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.movimiento.ExamenMedicoMedicinaGeneral;
import com.unmsm.clinica.model.movimiento.ExamenMedicoOdontologico;
import com.unmsm.clinica.service.IExamenMedicoMedicinaGeneralService;
import com.unmsm.clinica.service.excepcion.BadRequestException;
import com.unmsm.clinica.utilitario.ConstantesGenerales;
import com.unmsm.clinica.utilitario.ValidatorUtil;
import com.unmsm.clinica.validacion.grupo.accion.IActualizacion;
import com.unmsm.clinica.validacion.grupo.accion.IRegistro;

@RequestMapping("/examenmedico/medicinaGeneral")
public @RestController class ExamenMedicoMedicinaGeneralRestController {
	private @Autowired IExamenMedicoMedicinaGeneralService examenMedicoMedicinaGeneralService;

	@PostMapping
	public String registrarExamenMedicoMedicinaGeneral(
			@Validated({ IRegistro.class,
					Default.class }) @RequestBody ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.examenMedicoMedicinaGeneralService.registrarExamenMedicoMedicinaGeneral(examenMedicoMedicinaGeneral);
		return ConstantesGenerales.REGISTRO_EXITOSO;
	}

	@PutMapping
	public String actualizarExamenMedicoMedicinaGeneral(
			@Validated({ IActualizacion.class,
					Default.class }) @RequestBody ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.examenMedicoMedicinaGeneralService.actualizarExamenMedicoMedicinaGeneral(examenMedicoMedicinaGeneral);
		return ConstantesGenerales.ACTUALIZACION_EXITOSA;
	}
}