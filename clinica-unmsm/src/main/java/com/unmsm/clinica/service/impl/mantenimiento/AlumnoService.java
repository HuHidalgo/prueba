package com.unmsm.clinica.service.impl.mantenimiento;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IAlumnoMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.Alumno;
import com.unmsm.clinica.service.IAlumnoService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.ConstantesExcepciones;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class AlumnoService extends MantenibleService<Alumno> implements IAlumnoService {
	
	private @Autowired IAlumnoMapper alumnoMapper;

	private static final String CARGAR = "CARGAR";

	public AlumnoService(@Qualifier("IAlumnoMapper") IMantenibleMapper<Alumno> mapper) {
		super(mapper);
		this.alumnoMapper = (IAlumnoMapper) mapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Alumno> buscarAlumno(Alumno criterioBusqueda) {
		return this.alumnoMapper.buscarAlumno(criterioBusqueda);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Alumno buscarPorCodigoAlumnoTipoAlumno(String codigoAlumno, String tipoAlumno) {
		Alumno alumno = Alumno.builder().codigoAlumno(codigoAlumno).tipoAlumno(tipoAlumno).build();
		List<Alumno> alumnos = this.buscar(alumno, Verbo.GET);
		Validate.notEmpty(alumnos, ConstantesExcepciones.ALUMNO_NO_ENCONTRADO, codigoAlumno, tipoAlumno);
		return alumnos.get(0);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean existeAlumno(String codigoAlumno, String tipoAlumno) {
		Alumno alumno = Alumno.builder().codigoAlumno(codigoAlumno).tipoAlumno(tipoAlumno).build();
		return this.existe(alumno);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registrarAlumno(Alumno alumno) {
		this.registrar(alumno);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarAlumnos(List<Alumno> alumnos) {
		alumnos.stream().forEach(alumno -> this.registrar(alumno, CARGAR));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarAlumno(Alumno alumno) {
		this.actualizar(alumno);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarAlumno(Alumno alumno) {
		this.eliminar(alumno);
	}
}