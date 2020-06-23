package com.unmsm.clinica.service.impl.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IConsultaExamenMedicoPsicologicoMapper;
import com.unmsm.clinica.model.consulta.administrativa.ConsultaPsicologicoAdministrativo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAdministrativaExamenMedicoPsicologico;
import com.unmsm.clinica.service.IConsultaExamenMedicoPsicologicoService;

@Service
public class ConsultaExamenMedicoPsicologicoService implements IConsultaExamenMedicoPsicologicoService {
	private @Autowired IConsultaExamenMedicoPsicologicoMapper consultaExamenMedicoPsicologicoMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<ConsultaPsicologicoAdministrativo> buscarPorCriterioBusquedaAdministrativa(
			CriterioBusquedaAdministrativaExamenMedicoPsicologico criterioBusquedaAdministrativaExamenMedicoPsicologico) {
		return consultaExamenMedicoPsicologicoMapper
				.buscarPorCriterioBusquedaAdministrativa(criterioBusquedaAdministrativaExamenMedicoPsicologico);
	}
}