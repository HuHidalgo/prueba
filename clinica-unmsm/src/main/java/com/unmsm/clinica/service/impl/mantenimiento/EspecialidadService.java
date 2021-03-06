package com.unmsm.clinica.service.impl.mantenimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;

import com.unmsm.clinica.mapper.IEspecialidadMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.Especialidad;
import com.unmsm.clinica.service.IEspecialidadService;
import com.unmsm.clinica.service.excepcion.MantenimientoException;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.ConstantesExcepciones;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class EspecialidadService extends MantenibleService<Especialidad> implements IEspecialidadService {
	@SuppressWarnings("unused")
	private IEspecialidadMapper especialidadMapper;

	public EspecialidadService(@Qualifier("IEspecialidadMapper") IMantenibleMapper<Especialidad> mapper) {
		super(mapper);
		this.especialidadMapper = (IEspecialidadMapper) mapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Especialidad> buscarTodos() {
		return this.buscar(new Especialidad(), Verbo.GETS);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Especialidad> buscarPorId(int idEspecialidad) {
		Especialidad especialidad = Especialidad.builder().idEspecialidad(idEspecialidad).build();
		return this.buscar(especialidad, Verbo.GET);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean existeEspecialidad(int idEspecialidad) {
		Especialidad especialidad = Especialidad.builder().idEspecialidad(idEspecialidad).build();
		return this.existe(especialidad);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int registrarEspecialidad(Especialidad especialidad) {
		Preconditions.checkNotNull(especialidad, ConstantesExcepciones.ESPECIALIDAD_NOT_NULL);
		List<Especialidad> especialidades = this.registrarAutoIncrementable(especialidad);
		if (!especialidades.isEmpty() && especialidades.get(0).getIdEspecialidad() != null) {
			return especialidades.get(0).getIdEspecialidad();
		} else {
			throw new MantenimientoException(ConstantesExcepciones.ERROR_REGISTRO);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarEspecialidad(Especialidad especialidad) {
		this.actualizar(especialidad);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarEspecialidad(Especialidad especialidad) {
		this.eliminar(especialidad);
	}
}