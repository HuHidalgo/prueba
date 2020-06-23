package com.unmsm.clinica.controller.mantenimiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.unmsm.clinica.model.mantenimiento.Alumno;
import com.unmsm.clinica.service.IAlumnoService;
import com.unmsm.clinica.service.excepcion.BadRequestException;
import com.unmsm.clinica.utilitario.ConstantesGenerales;
import com.unmsm.clinica.utilitario.ValidatorUtil;
import com.unmsm.clinica.validacion.grupo.secuencia.ISecuenciaValidacionActualizacion;
import com.unmsm.clinica.validacion.grupo.secuencia.ISecuenciaValidacionEliminacion;
import com.unmsm.clinica.validacion.grupo.secuencia.ISecuenciaValidacionRegistro;

@Audit(tipo = Tipo.Alumno, datos = Dato.Alumno)
@RequestMapping("/mantenimiento/alumno")
public @RestController class AlumnoRestController {
	private @Autowired IAlumnoService alumnoService;

	@Audit(accion = Accion.Consulta, comentario = Comentario.ConsultaTodos)
	@GetMapping(params = "accion=buscarAlumno")
	public List<Alumno> buscarTodos(Alumno criterioBusqueda) {
		//System.out.println(criterioBusqueda);
		return this.alumnoService.buscarAlumno(criterioBusqueda);
	}

	@Audit(accion = Accion.Registro, comentario = Comentario.Registro)
	@PostMapping
	public Alumno registrarAlumno(@Validated(ISecuenciaValidacionRegistro.class) @RequestBody Alumno alumno,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.alumnoService.registrarAlumno(alumno);
		return this.alumnoService.buscarPorCodigoAlumnoTipoAlumno(alumno.getCodigoAlumno(), alumno.getTipoAlumno());
	}

	@Audit(accion = Accion.Actualizacion, comentario = Comentario.Actualizacion)
	@PutMapping
	public Alumno actualizarAlumno(@Validated(ISecuenciaValidacionActualizacion.class) @RequestBody Alumno alumno,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.alumnoService.actualizarAlumno(alumno);
		return this.alumnoService.buscarPorCodigoAlumnoTipoAlumno(alumno.getCodigoAlumno(), alumno.getTipoAlumno());
	}

	@Audit(accion = Accion.Eliminacion, comentario = Comentario.Eliminacion)
	@DeleteMapping
	public String eliminarAlumno(@Validated(ISecuenciaValidacionEliminacion.class) @RequestBody Alumno alumno,
			Errors error) {
		if (error.hasErrors()) {
			throw new BadRequestException(ValidatorUtil.obtenerMensajeValidacionError(error));
		}
		this.alumnoService.eliminarAlumno(alumno);
		return ConstantesGenerales.ELIMINACION_EXITOSA;
	}
}