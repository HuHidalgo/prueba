package com.unmsm.clinica.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IConsultaExamenMedicoMedicinaGeneralMapper;
import com.unmsm.clinica.model.consulta.administrativa.ConsultaMedicinaGeneralAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral;
import com.unmsm.clinica.service.IConsultaExamenMedicoMedicinaGeneralService;

@Service
public class ConsultaExamenMedicoMedicinaGeneralService implements IConsultaExamenMedicoMedicinaGeneralService {
	private @Autowired IConsultaExamenMedicoMedicinaGeneralMapper consultaExamenMedicoMedicinaGeneralMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaMedicinaGeneralAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoMedicinaGeneral criterioBusquedaAdministrativaExamenMedicoMedicinaGeneral) {
		return consultaExamenMedicoMedicinaGeneralMapper
				.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoMedicinaGeneral);
	}
}