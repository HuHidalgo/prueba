package com.unmsm.clinica.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IConsultaExamenMedicoOdontologicoMapper;
import com.unmsm.clinica.model.consulta.administrativa.ConsultaOdontologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoOdontologico;
import com.unmsm.clinica.service.IConsultaExamenMedicoOdontologicoService;

@Service
public class ConsultaExamenMedicoOdontologicoService implements IConsultaExamenMedicoOdontologicoService {
	private @Autowired IConsultaExamenMedicoOdontologicoMapper consultaExamenMedicoOdontologicoMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaOdontologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoOdontologico criterioBusquedaAdministrativaExamenMedicoOdontologico) {
		return consultaExamenMedicoOdontologicoMapper
				.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoOdontologico);
	}
}