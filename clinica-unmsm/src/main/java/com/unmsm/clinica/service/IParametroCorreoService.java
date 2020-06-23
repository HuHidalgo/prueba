package com.unmsm.clinica.service;

import com.unmsm.clinica.model.mantenimiento.ParametroCorreo;

public interface IParametroCorreoService extends IMantenibleService<ParametroCorreo> {
	public ParametroCorreo buscarParametroCorreo();
}