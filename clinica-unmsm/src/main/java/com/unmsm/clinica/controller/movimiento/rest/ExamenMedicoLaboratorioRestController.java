package com.unmsm.clinica.controller.movimiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.movimiento.ExamenMedicoLaboratorio;
import com.unmsm.clinica.service.IExamenMedicoLaboratorioService;
import com.unmsm.clinica.service.excepcion.BadRequestException;
import com.unmsm.clinica.utilitario.ConstantesGenerales;
import com.unmsm.clinica.utilitario.ValidatorUtil;
import com.unmsm.clinica.validacion.grupo.IGeneracionNumReg;
import com.unmsm.clinica.validacion.grupo.alumno.IIngresante;
import com.unmsm.clinica.validacion.grupo.alumno.IRegular;

@RequestMapping("/examenmedico/laboratorio")
public @RestController class ExamenMedicoLaboratorioRestController {
	private @Autowired IExamenMedicoLaboratorioService examenMedicoLaboratorioService;

	@PostMapping(params = "accion=generar")
	public Integer generarNumeroRegistro(
			@Validated(IGeneracionNumReg.class) @RequestBody ExamenMedicoLaboratorio examenMedicoLaboratorio,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		return this.examenMedicoLaboratorioService.generarNumeroRegistro(examenMedicoLaboratorio);
	}

	@GetMapping(params = "accion=buscarNumeroRegistroGenerado")
	public List<ExamenMedicoLaboratorio> buscarPorEstadoExamenMedicoNumeroRegistroGenerado() {
		return this.examenMedicoLaboratorioService
				.buscarPorEstadoExamenMedico(ConstantesGenerales.ESTADO_NUMERO_REGISTRO_GENERADO);
	}

	@PostMapping(params = { "accion=registrar", "tipoAlumno=R" })
	public void registrarExamenMedicoRegularLaboratorio(
			@Validated(IRegular.class) @RequestBody ExamenMedicoLaboratorio examenMedicoLaboratorio, Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.examenMedicoLaboratorioService.registrarExamenMedicoRegularLaboratorio(examenMedicoLaboratorio);
	}

	@PostMapping(params = { "accion=registrar", "tipoAlumno=I" })
	public void registrarExamenMedicoIngresanteLaboratorio(
			@Validated(IIngresante.class) @RequestBody ExamenMedicoLaboratorio examenMedicoLaboratorio, Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.examenMedicoLaboratorioService.registrarExamenMedicoIngresanteLaboratorio(examenMedicoLaboratorio);

	}
}