package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.movimiento.ExamenMedicoMedicinaGeneral;

public interface IExamenMedicoMedicinaGeneralService extends IMantenibleService<ExamenMedicoMedicinaGeneral> {
	public void registrarExamenMedicoMedicinaGeneral(ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral);

	public void actualizarExamenMedicoMedicinaGeneral(ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral);

	public void eliminarExamenMedicoMedicinaGeneral(ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral);

	public List<ExamenMedicoMedicinaGeneral> buscarResultadoPorNumeroRegistro(Integer numeroRegistro);

	public boolean existeExamenMedicoMedicinaGeneral(Integer numeroRegistro, String anio);
}