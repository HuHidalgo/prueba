package com.unmsm.clinica.controller.movimiento.rest;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.movimiento.ExamenMedicoPsicologico;
import com.unmsm.clinica.service.IExamenMedicoPsicologicoService;
import com.unmsm.clinica.service.excepcion.BadRequestException;
import com.unmsm.clinica.utilitario.ConstantesGenerales;
import com.unmsm.clinica.utilitario.ValidatorUtil;
import com.unmsm.clinica.validacion.grupo.accion.IActualizacion;
import com.unmsm.clinica.validacion.grupo.accion.IRegistro;

@RequestMapping("/examenmedico/psicologia")
public @RestController class ExamenMedicoPsicologicoRestController {
	private @Autowired IExamenMedicoPsicologicoService examenMedicoPsicologicoService;

	@PostMapping
	public String registrarExamenMedicoPsicologico(
			@Validated({ IRegistro.class, Default.class }) @RequestBody ExamenMedicoPsicologico examenMedicoPsicologico,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.examenMedicoPsicologicoService.registrarExamenMedicoPsicologico(examenMedicoPsicologico);
		return ConstantesGenerales.REGISTRO_EXITOSO;
	}

	@PutMapping
	public String actualizarExamenMedicoPsicologico(@Validated({ IActualizacion.class,
			Default.class }) @RequestBody ExamenMedicoPsicologico examenMedicoPsicologico, Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.examenMedicoPsicologicoService.actualizarExamenMedicoPsicologico(examenMedicoPsicologico);
		return ConstantesGenerales.ACTUALIZACION_EXITOSA;
	}

	@DeleteMapping
	public String eliminarExamenMedicoPsicologico(
			@Validated(IActualizacion.class) @RequestBody ExamenMedicoPsicologico examenMedicoPsicologico,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.examenMedicoPsicologicoService.eliminarExamenMedicoPsicologico(examenMedicoPsicologico);
		return ConstantesGenerales.ELIMINACION_EXITOSA;
	}
}