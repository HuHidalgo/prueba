package com.unmsm.clinica.service.impl.movimiento;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IExamenMedicoMedicinaGeneralMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.mantenimiento.Alumno;
import com.unmsm.clinica.model.movimiento.Antecedente;
import com.unmsm.clinica.model.movimiento.ExamenMedicoMedicinaGeneral;
import com.unmsm.clinica.service.IAlumnoService;
import com.unmsm.clinica.service.IAntecedenteService;
import com.unmsm.clinica.service.IExamenMedicoMedicinaGeneralService;
import com.unmsm.clinica.service.IInterconsultaService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.ConstantesExcepciones;

@Service
public class ExamenMedicoMedicinaGeneralService extends MantenibleService<ExamenMedicoMedicinaGeneral>
		implements IExamenMedicoMedicinaGeneralService {
	@SuppressWarnings("unused")
	private IExamenMedicoMedicinaGeneralMapper examenMedicoMedicinaGeneralMapper;

	private @Autowired IAlumnoService alumnoService;
	private @Autowired IAntecedenteService antecedenteService;
	private @Autowired IInterconsultaService interconsultaService;

	private static final String GET_UPDATE_REG = "GET_UPDATE_REG";

	public ExamenMedicoMedicinaGeneralService(
			@Qualifier("IExamenMedicoMedicinaGeneralMapper") IMantenibleMapper<ExamenMedicoMedicinaGeneral> mapper) {
		super(mapper);
		this.examenMedicoMedicinaGeneralMapper = (IExamenMedicoMedicinaGeneralMapper) mapper;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarExamenMedicoMedicinaGeneral(ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral) {
		String anio = "";
		int numeroRegistro = 0;
		List<ExamenMedicoMedicinaGeneral> examenes = this.registrarAutoIncrementable(examenMedicoMedicinaGeneral);
		Validate.notEmpty(examenes, ConstantesExcepciones.ERROR_REGISTRO);
		Validate.notNull(examenes.get(0).getNumeroRegistro(), ConstantesExcepciones.ERROR_REGISTRO);
		Validate.notNull(examenes.get(0).getAnio(), ConstantesExcepciones.ERROR_REGISTRO);
		ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneralResultado = examenes.get(0);
		anio = examenMedicoMedicinaGeneralResultado.getAnio();
		numeroRegistro = examenMedicoMedicinaGeneralResultado.getNumeroRegistro();
		Alumno alumno = alumnoService.buscarPorCodigoAlumnoTipoAlumno(examenMedicoMedicinaGeneral.getCodigoAlumno(),
				examenMedicoMedicinaGeneral.getTipoAlumno());
		this.antecedenteService.registrarAntecedente(examenMedicoMedicinaGeneral.getAntecedentes(), numeroRegistro,
				anio, alumno.getNumeroDocumento(), alumno.getIdTipoDocumento());
		this.interconsultaService.registrarInterconsulta(examenMedicoMedicinaGeneral.getInterconsultas(),
				numeroRegistro, anio);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void actualizarExamenMedicoMedicinaGeneral(ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral) {
		String anio = "";
		int numeroRegistro = 0;

		this.actualizar(examenMedicoMedicinaGeneral);
		anio = examenMedicoMedicinaGeneral.getAnio();
		numeroRegistro = examenMedicoMedicinaGeneral.getNumeroRegistro();
		Alumno alumno = alumnoService.buscarPorCodigoAlumnoTipoAlumno(examenMedicoMedicinaGeneral.getCodigoAlumno(),
				examenMedicoMedicinaGeneral.getTipoAlumno());
		this.antecedenteService.registrarAntecedente(examenMedicoMedicinaGeneral.getAntecedentes(), numeroRegistro,
				anio, alumno.getNumeroDocumento(), alumno.getIdTipoDocumento());
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void eliminarExamenMedicoMedicinaGeneral(ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral) {
		this.eliminar(examenMedicoMedicinaGeneral);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ExamenMedicoMedicinaGeneral> buscarResultadoPorNumeroRegistro(Integer numeroRegistro) {
		ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral = ExamenMedicoMedicinaGeneral.builder()
				.numeroRegistro(numeroRegistro).build();
		List<ExamenMedicoMedicinaGeneral> examenUpdate = this.buscar(examenMedicoMedicinaGeneral, GET_UPDATE_REG);
		List<Antecedente> antecedenteUpdate = this.antecedenteService.buscarAntecedente(numeroRegistro);
		examenUpdate.get(0).setAntecedentes(antecedenteUpdate);
		return examenUpdate;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean existeExamenMedicoMedicinaGeneral(Integer numeroRegistro, String anio) {
		ExamenMedicoMedicinaGeneral examenMedicoMedicinaGeneral = ExamenMedicoMedicinaGeneral.builder()
				.numeroRegistro(numeroRegistro).anio(anio).build();
		return this.existe(examenMedicoMedicinaGeneral);
	}
}