package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.seguridad.TipoAuditoria;

public interface ITipoAuditoriaService extends IMantenibleService<TipoAuditoria> {
	public List<TipoAuditoria> buscarTodos();
}
