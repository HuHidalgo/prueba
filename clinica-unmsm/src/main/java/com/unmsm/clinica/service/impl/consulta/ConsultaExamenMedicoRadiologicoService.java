package com.unmsm.clinica.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IConsultaExamenMedicoRadiologicoMapper;
import com.unmsm.clinica.model.consulta.administrativa.ConsultaRadiologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoRadiologico;
import com.unmsm.clinica.service.IConsultaExamenMedicoRadiologicoService;

@Service
public class ConsultaExamenMedicoRadiologicoService implements IConsultaExamenMedicoRadiologicoService {
	private @Autowired IConsultaExamenMedicoRadiologicoMapper consultaExamenMedicoRadiologicoMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaRadiologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoRadiologico criterioBusquedaAdministrativaExamenMedicoRadiologico) {
		return consultaExamenMedicoRadiologicoMapper
				.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoRadiologico);
	}
}