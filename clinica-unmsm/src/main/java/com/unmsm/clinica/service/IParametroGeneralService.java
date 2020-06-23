package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.mantenimiento.ParametroGeneral;

public interface IParametroGeneralService extends IMantenibleService<ParametroGeneral> {
	public List<ParametroGeneral> buscarTodos();

	public void actualizarParametroGeneral(ParametroGeneral parametroGeneral);

	public Integer buscarAnioInicio();

	public String buscarCorreoSUM();

	public String buscarCorreoClinica();
}