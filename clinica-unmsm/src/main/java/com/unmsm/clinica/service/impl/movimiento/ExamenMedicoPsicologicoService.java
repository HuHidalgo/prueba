package com.unmsm.clinica.service.impl.movimiento;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IExamenMedicoPsicologicoMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.movimiento.ExamenMedicoPsicologico;
import com.unmsm.clinica.service.IExamenMedicoPsicologicoService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class ExamenMedicoPsicologicoService extends MantenibleService<ExamenMedicoPsicologico>
		implements IExamenMedicoPsicologicoService {
	@SuppressWarnings("unused")
	private IExamenMedicoPsicologicoMapper examenMedicoPsicologicoMapper;

	public ExamenMedicoPsicologicoService(
			@Qualifier("IExamenMedicoPsicologicoMapper") IMantenibleMapper<ExamenMedicoPsicologico> mapper) {
		super(mapper);
		this.examenMedicoPsicologicoMapper = (IExamenMedicoPsicologicoMapper) mapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarExamenMedicoPsicologico(ExamenMedicoPsicologico examenMedicoPsicologico) {
		this.registrar(examenMedicoPsicologico);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarExamenMedicoPsicologico(ExamenMedicoPsicologico examenMedicoPsicologico) {
		this.actualizar(examenMedicoPsicologico);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarExamenMedicoPsicologico(ExamenMedicoPsicologico examenMedicoPsicologico) {
		this.eliminar(examenMedicoPsicologico);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ExamenMedicoPsicologico> buscarResultadoRegularPorNumeroRegistro(Integer numeroRegistro) {
		ExamenMedicoPsicologico examenMedicoPsicologico = ExamenMedicoPsicologico.builder()
				.numeroRegistro(numeroRegistro).build();
		return this.buscar(examenMedicoPsicologico, Verbo.GET);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean existeExamenMedicoPsicologico(Integer numeroRegistro) {
		ExamenMedicoPsicologico examenMedicoPsicologico = ExamenMedicoPsicologico.builder()
				.numeroRegistro(numeroRegistro).build();
		return this.existe(examenMedicoPsicologico);
	}
}