package com.unmsm.clinica.controller.mantenimiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.unmsm.clinica.model.mantenimiento.Persona;
import com.unmsm.clinica.service.IPersonaService;
import com.unmsm.clinica.service.excepcion.BadRequestException;
import com.unmsm.clinica.utilitario.ConstantesGenerales;
import com.unmsm.clinica.utilitario.ValidatorUtil;
import com.unmsm.clinica.validacion.grupo.secuencia.ISecuenciaValidacionActualizacion;
import com.unmsm.clinica.validacion.grupo.secuencia.ISecuenciaValidacionEliminacion;
import com.unmsm.clinica.validacion.grupo.secuencia.ISecuenciaValidacionRegistro;

@Audit(tipo = Tipo.Persona, datos = Dato.Persona)
@RequestMapping("/mantenimiento/persona")
public @RestController class PersonaRestController {
	private @Autowired IPersonaService personaService;

	@Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
	@GetMapping(params = "accion=buscarTodos")
	public List<Persona> buscarTodos() {
		return personaService.buscarTodos();
	}

	@Audit(accion = Accion.Registro, comentario = Comentario.Registro)
	@PostMapping
	public ResponseEntity<?> registrarPersona(
			@Validated(ISecuenciaValidacionRegistro.class) @RequestBody Persona persona, Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		personaService.registrarPersona(persona);
		return ResponseEntity.ok(personaService.buscarPorTipoDocumentoNumeroDocumento(persona.getIdTipoDocumento(),
				persona.getNumeroDocumento()));
	}

	@Audit(accion = Accion.Actualizacion, comentario = Comentario.Actualizacion)
	@PutMapping
	public ResponseEntity<?> actualizarPersona(
			@Validated(ISecuenciaValidacionActualizacion.class) @RequestBody Persona persona, Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		personaService.actualizarPersona(persona);
		return ResponseEntity.ok(personaService.buscarPorTipoDocumentoNumeroDocumento(persona.getIdTipoDocumento(),
				persona.getNumeroDocumento()));
	}

	@Audit(accion = Accion.Eliminacion, comentario = Comentario.Eliminacion)
	@DeleteMapping
	public ResponseEntity<?> eliminarPersona(
			@Validated(ISecuenciaValidacionEliminacion.class) @RequestBody Persona persona, Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		personaService.eliminarPersona(persona);
		return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
	}
}