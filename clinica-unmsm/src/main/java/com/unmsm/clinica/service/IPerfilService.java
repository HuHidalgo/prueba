package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.seguridad.Perfil;

public interface IPerfilService extends IMantenibleService<Perfil> {
	public List<Perfil> buscarTodos();
}