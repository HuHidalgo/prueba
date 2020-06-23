package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.movimiento.Diente;

public interface ITratamientoDientesService extends IMantenibleService<Diente> {
	public void registrarTratamientoDientes(List<Diente> dientes);

	public void registrarTratamientoDientes(List<Diente> antecedentes, Integer numeroRegistro, String anio);

	public void registrarTratamientoDientes(Diente dientes);

	public void eliminarTratamientoDientes(Diente dientes);

	public List<Diente> buscarTratamientoDientes(Integer numeroRegistro);
}