package com.unmsm.clinica.mapper.base;

import java.util.List;
import java.util.Map;

import com.unmsm.clinica.model.parametro.Parametro;

public interface IMantenibleMapper<T> {
	
	public List<T> mantener(Parametro parametro);
	
}
