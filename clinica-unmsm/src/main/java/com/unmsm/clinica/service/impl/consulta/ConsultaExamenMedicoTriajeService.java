package com.unmsm.clinica.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IConsultaExamenMedicoTriajeMapper;
import com.unmsm.clinica.model.consulta.administrativa.ConsultaTriajeAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoTriaje;
import com.unmsm.clinica.service.IConsultaExamenMedicoTriajeService;

@Service
public class ConsultaExamenMedicoTriajeService implements IConsultaExamenMedicoTriajeService {
	private @Autowired IConsultaExamenMedicoTriajeMapper consultaTriajeMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaTriajeAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoTriaje criterioBusquedaAdministrativaTriaje) {
		return consultaTriajeMapper.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaTriaje);
	}
}