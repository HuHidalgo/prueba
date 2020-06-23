package com.unmsm.clinica.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IConsultaExamenMedicoLaboratorioMapper;
import com.unmsm.clinica.model.consulta.administrativa.ConsultaLaboratorioAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoLaboratorio;
import com.unmsm.clinica.service.IConsultaExamenMedicoLaboratorioService;

@Service
public class ConsultaExamenMedicoLaboratorioService implements IConsultaExamenMedicoLaboratorioService {
	private @Autowired IConsultaExamenMedicoLaboratorioMapper consultaExamenMedicoLaboratorioMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaLaboratorioAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoLaboratorio criterioBusquedaAdministrativaExamenMedicoLaboratorio) {
		return consultaExamenMedicoLaboratorioMapper
				.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoLaboratorio);
	}
}