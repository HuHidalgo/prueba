package com.unmsm.clinica.service.impl.movimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IExamenMedicoOdontologiaMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.movimiento.Antecedente;
import com.unmsm.clinica.model.movimiento.Diente;
import com.unmsm.clinica.model.movimiento.ExamenMedicoOdontologico;
import com.unmsm.clinica.service.IExamenMedicoOdontologicoService;
import com.unmsm.clinica.service.ITratamientoDientesService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class ExamenMedicoOdontologicoService extends MantenibleService<ExamenMedicoOdontologico>
		implements IExamenMedicoOdontologicoService {
	@SuppressWarnings("unused")
	private IExamenMedicoOdontologiaMapper examenMedicoOdontologiaMapper;
	private @Autowired ITratamientoDientesService dienteService;
	private static final String GET_UPDATE_REG = "GET_UPDATE_REG";

	public ExamenMedicoOdontologicoService(
			@Qualifier("IExamenMedicoOdontologiaMapper") IMantenibleMapper<ExamenMedicoOdontologico> mapper) {
		super(mapper);
		this.examenMedicoOdontologiaMapper = (IExamenMedicoOdontologiaMapper) mapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico) {
		List<ExamenMedicoOdontologico> examen = this.registrarAutoIncrementable(examenMedicoOdontologico);
		this.dienteService.registrarTratamientoDientes(examenMedicoOdontologico.getDientes(),
				examen.get(0).getNumeroRegistro(), examen.get(0).getAnio());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico) {
		this.actualizar(examenMedicoOdontologico);
		this.dienteService.registrarTratamientoDientes(examenMedicoOdontologico.getDientes(),
				examenMedicoOdontologico.getNumeroRegistro(), examenMedicoOdontologico.getAnio());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarExamenMedicoOdontologico(ExamenMedicoOdontologico examenMedicoOdontologico) {
		this.eliminar(examenMedicoOdontologico);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ExamenMedicoOdontologico> buscarResultadoPorNumeroRegistro(Integer numeroRegistro) {
		ExamenMedicoOdontologico examenMedicoOdontologico = ExamenMedicoOdontologico.builder()
				.numeroRegistro(numeroRegistro).build();
		List<ExamenMedicoOdontologico> examenUpdate = this.buscar(examenMedicoOdontologico, GET_UPDATE_REG);
		List<Diente> dienteUpdate = this.dienteService.buscarTratamientoDientes(numeroRegistro);
		examenUpdate.get(0).setDientes(dienteUpdate);
		return examenUpdate;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean existeExamenMedicoOdontologico(Integer numeroRegistro) {
		ExamenMedicoOdontologico examenMedicoOdontologico = ExamenMedicoOdontologico.builder()
				.numeroRegistro(numeroRegistro).build();
		return this.existe(examenMedicoOdontologico);
	}
}