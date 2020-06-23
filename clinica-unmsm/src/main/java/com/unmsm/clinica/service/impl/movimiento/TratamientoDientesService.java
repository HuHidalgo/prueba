package com.unmsm.clinica.service.impl.movimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.ITratamientoDientesMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.movimiento.Diente;
import com.unmsm.clinica.service.ITratamientoDientesService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class TratamientoDientesService extends MantenibleService<Diente> implements ITratamientoDientesService {
	@SuppressWarnings("unused")
	private ITratamientoDientesMapper dienteMapper;

	public TratamientoDientesService(@Qualifier("ITratamientoDientesMapper") IMantenibleMapper<Diente> mapper) {
		super(mapper);
		this.dienteMapper = (ITratamientoDientesMapper) mapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registrarTratamientoDientes(List<Diente> dientes) {
		for (Diente diente : dientes) {
			registrarTratamientoDientes(diente);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registrarTratamientoDientes(List<Diente> dientes, Integer numeroRegistro, String anio) {
		for (Diente diente : dientes) {
			diente.setAnio(anio);
			diente.setNumeroRegistro(numeroRegistro);
			registrarTratamientoDientes(diente);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registrarTratamientoDientes(Diente diente) {
		this.registrar(diente);
	}

	@Override
	public List<Diente> buscarTratamientoDientes(Integer numeroRegistro) {
		Diente diente = Diente.builder().numeroRegistro(numeroRegistro).build();
		return this.buscar(diente, Verbo.GET);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarTratamientoDientes(Diente diente) {
		this.eliminar(diente);
	}
}