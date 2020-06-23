package com.unmsm.clinica.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IEscuelaMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.Escuela;
import com.unmsm.clinica.service.IEscuelaService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class EscuelaService extends MantenibleService<Escuela> implements IEscuelaService {
	@SuppressWarnings("unused")
	private IEscuelaMapper escuelaMapper;

	private static final String GET_FAC = "GET_FAC";

	public EscuelaService(@Qualifier("IEscuelaMapper") IMantenibleMapper<Escuela> mapper) {
		super(mapper);
		this.escuelaMapper = (IEscuelaMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Escuela> buscarTodos() {
		return this.buscar(new Escuela(), Verbo.GETS);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Escuela> buscarPorCodigoFacultad(Integer codigoFacultad) {
		Escuela escuela = Escuela.builder().codigoFacultad(codigoFacultad).build();
		return this.buscar(escuela, GET_FAC);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Escuela> buscarPorCodigoFacultadCodigoEscuela(Integer codigoFacultad, Integer codigoEscuela) {
		Escuela escuela = Escuela.builder().codigoFacultad(codigoFacultad).codigoEscuela(codigoEscuela).build();
		return this.buscar(escuela, Verbo.GET);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarEscuela(Escuela escuela) {
		this.registrar(escuela);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarEscuela(Escuela escuela) {
		this.actualizar(escuela);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarEscuela(Escuela escuela) {
		this.eliminar(escuela);
	}
}