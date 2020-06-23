package com.unmsm.clinica.service.impl.movimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IAntecedenteMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.movimiento.Antecedente;
import com.unmsm.clinica.service.IAntecedenteService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class AntecedenteService extends MantenibleService<Antecedente> implements IAntecedenteService {
	@SuppressWarnings("unused")
	private IAntecedenteMapper antecedenteMapper;

	public AntecedenteService(@Qualifier("IAntecedenteMapper") IMantenibleMapper<Antecedente> mapper) {
		super(mapper);
		this.antecedenteMapper = (IAntecedenteMapper) mapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registrarAntecedente(List<Antecedente> antecedentes) {
		for (Antecedente antecedente : antecedentes) {
			registrarAntecedente(antecedente);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registrarAntecedente(List<Antecedente> antecedentes, Integer numeroRegistro, String anio,
			String numeroDocumento, String idTipoDocumento) {
		for (Antecedente antecedente : antecedentes) {
			antecedente.setAnio(anio);
			antecedente.setNumeroRegistro(numeroRegistro);
			antecedente.setIdTipoDocumento(idTipoDocumento);
			antecedente.setNumeroDocumento(numeroDocumento);
			registrarAntecedente(antecedente);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registrarAntecedente(Antecedente antecedente) {
		this.registrar(antecedente);
	}

	@Override
	public List<Antecedente> buscarAntecedente(Integer numeroRegistro) {
		Antecedente antecedente = Antecedente.builder().numeroRegistro(numeroRegistro).build();
		return this.buscar(antecedente, Verbo.GET);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarAntecedente(Antecedente antecedente) {
		this.eliminar(antecedente);
	}
}