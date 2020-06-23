package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.movimiento.ExamenMedicoTriaje;

public interface IExamenMedicoTriajeService extends IMantenibleService<ExamenMedicoTriaje> {
	public void registrarExamenMedicoTriaje(ExamenMedicoTriaje examenMedicoTriaje);

	public void actualizarExamenMedicoTriaje(ExamenMedicoTriaje examenMedicoTriaje);

	public void eliminarExamenMedicoTriaje(ExamenMedicoTriaje examenMedicoTriaje);

	public List<ExamenMedicoTriaje> buscarResultadoRegularPorNumeroRegistro(Integer numeroRegistro);

	public boolean existeExamenMedicoTriaje(Integer numeroRegistro);
}
