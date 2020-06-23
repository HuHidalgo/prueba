package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.movimiento.Antecedente;

public interface IAntecedenteService extends IMantenibleService<Antecedente> {
	public void registrarAntecedente(List<Antecedente> antecedentes);

	public void registrarAntecedente(List<Antecedente> antecedentes, Integer numeroRegistro, String anio,
			String numeroDocumento, String idTipoDocumento);

	public void registrarAntecedente(Antecedente antecedente);

	public void eliminarAntecedente(Antecedente antecedente);

	public List<Antecedente> buscarAntecedente(Integer numeroRegistro);
}