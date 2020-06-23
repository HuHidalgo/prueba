package com.unmsm.clinica.service.impl.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.ITipoAuditoriaMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.seguridad.TipoAuditoria;
import com.unmsm.clinica.service.ITipoAuditoriaService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class TipoAuditoriaService extends MantenibleService<TipoAuditoria> implements ITipoAuditoriaService {
	@SuppressWarnings("unused")
	private ITipoAuditoriaMapper tipoAuditoriaMapper;

	public TipoAuditoriaService(@Qualifier("ITipoAuditoriaMapper") IMantenibleMapper<TipoAuditoria> mapper) {
		super(mapper);
		this.tipoAuditoriaMapper = (ITipoAuditoriaMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<TipoAuditoria> buscarTodos() {
		return this.buscar(new TipoAuditoria(), Verbo.GETS);
	}
}