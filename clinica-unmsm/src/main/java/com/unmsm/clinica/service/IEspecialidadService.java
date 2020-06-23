package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.mantenimiento.Especialidad;

public interface IEspecialidadService extends IMantenibleService<Especialidad> {
	public List<Especialidad> buscarTodos();

	public List<Especialidad> buscarPorId(int idEspecialidad);

	public boolean existeEspecialidad(int idEspecialidad);

	public int registrarEspecialidad(Especialidad especialidad);

	public void actualizarEspecialidad(Especialidad especialidad);

	public void eliminarEspecialidad(Especialidad especialidad);
}