package com.unmsm.clinica.service.impl.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.mapper.IPerfilMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.seguridad.Perfil;
import com.unmsm.clinica.service.IPerfilService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class PerfilService extends MantenibleService<Perfil> implements IPerfilService {
	@SuppressWarnings("unused")
	private IPerfilMapper perfilMapper;

	public PerfilService(@Qualifier("IPerfilMapper") IMantenibleMapper<Perfil> mapper) {
		super(mapper);
		this.perfilMapper = (IPerfilMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Perfil> buscarTodos() {
		return this.buscar(new Perfil(), Verbo.GETS);
	}
}