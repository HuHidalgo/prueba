package com.unmsm.clinica.controller.mantenimiento.rest;

import java.util.List;

import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.aspecto.anotacion.Audit;
import com.unmsm.clinica.aspecto.enumeracion.Accion;
import com.unmsm.clinica.aspecto.enumeracion.Comentario;
import com.unmsm.clinica.aspecto.enumeracion.Dato;
import com.unmsm.clinica.aspecto.enumeracion.Tipo;
import com.unmsm.clinica.model.mantenimiento.MultiTabDet;
import com.unmsm.clinica.service.IMultiTabDetService;
import com.unmsm.clinica.service.excepcion.BadRequestException;
import com.unmsm.clinica.utilitario.ConstantesGenerales;
import com.unmsm.clinica.utilitario.ValidatorUtil;
import com.unmsm.clinica.validacion.IdTabla;
import com.unmsm.clinica.validacion.grupo.accion.IActualizacion;
import com.unmsm.clinica.validacion.grupo.accion.IRegistro;

@Audit(tipo = Tipo.MulTabDet, datos = Dato.MultiTabDet)
@RequestMapping("/mantenimiento/multiTabDet")
public @RestController class MultiTabDetRestController {
	private @Autowired IMultiTabDetService multiTabDetService;

	@Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
	@GetMapping(params = "accion=buscarTodos")
	public List<MultiTabDet> buscarTodos() {
		return multiTabDetService.buscarTodos();
	}

	@Audit(accion = Accion.Consulta, comentario = Comentario.Consulta)
	@GetMapping("/multiTabCab/{idTabla}")
	public List<MultiTabDet> buscarPorIdTabla(@IdTabla(existe = true) @PathVariable int idTabla) {
		return multiTabDetService.buscarPorIdTabla(idTabla);
	}

	@Audit(accion = Accion.Registro, comentario = Comentario.Registro)
	@PostMapping
	public ResponseEntity<?> registrarMultiTabDet(
			@Validated({ Default.class, IRegistro.class }) @RequestBody MultiTabDet multiTabDet, Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		multiTabDetService.registrarMultiTabDet(multiTabDet);
		return ResponseEntity.ok(ConstantesGenerales.REGISTRO_EXITOSO);
	}

	@Audit(accion = Accion.Actualizacion, comentario = Comentario.Actualizacion)
	@PutMapping
	public ResponseEntity<?> actualizarMultiTabDet(
			@Validated({ Default.class, IActualizacion.class }) @RequestBody MultiTabDet multiTabDet, Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		multiTabDetService.actualizarMultiTabDet(multiTabDet);
		return ResponseEntity.ok(ConstantesGenerales.ACTUALIZACION_EXITOSA);
	}

	@Audit(accion = Accion.Eliminacion, comentario = Comentario.Eliminacion)
	@DeleteMapping
	public ResponseEntity<?> eliminarMultiTabDet(@Validated(IActualizacion.class) @RequestBody MultiTabDet multiTabDet,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		multiTabDetService.eliminarMultiTabDet(multiTabDet);
		return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
	}
}