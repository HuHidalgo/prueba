package com.unmsm.clinica.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.configuration.security.SecurityContextFacade;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.parametro.Parametro;
import com.unmsm.clinica.service.IMantenibleService;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class MantenibleService<T> implements IMantenibleService<T> {
	protected IMantenibleMapper<T> mantenimientoMapper;

	public MantenibleService(IMantenibleMapper<T> mapper) {
		this.mantenimientoMapper = mapper;
	}

	public MantenibleService() {
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void registrar(T dto) {
		mantenimientoMapper.mantener(new Parametro(Verbo.INSERT, dto, SecurityContextFacade.obtenerNombreUsuario()));
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public List<T> registrarAutoIncrementable(T dto) {
		return mantenimientoMapper
				.mantener(new Parametro(Verbo.INSERT, dto, SecurityContextFacade.obtenerNombreUsuario()));
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void registrar(T dto, String verbo) {
		mantenimientoMapper.mantener(new Parametro(verbo, dto, SecurityContextFacade.obtenerNombreUsuario()));
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void actualizar(T dto) {
		mantenimientoMapper.mantener(new Parametro(Verbo.UPDATE, dto, SecurityContextFacade.obtenerNombreUsuario()));
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void actualizar(T dto, String verbo) {
		mantenimientoMapper.mantener(new Parametro(verbo, dto, SecurityContextFacade.obtenerNombreUsuario()));
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void eliminar(T dto) {
		mantenimientoMapper.mantener(new Parametro(Verbo.REMOVE, dto));
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void eliminar(T dto, String verbo) {
		mantenimientoMapper.mantener(new Parametro(verbo, dto));
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public List<T> buscar(T dto, String verbo) {
		return mantenimientoMapper.mantener(new Parametro(verbo, dto));
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public boolean existe(T dto) {
		return !mantenimientoMapper.mantener(new Parametro(Verbo.EXIST, dto)).isEmpty();
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public void mantener(T dto, String verbo) {
		mantenimientoMapper.mantener(new Parametro(verbo, dto, SecurityContextFacade.obtenerNombreUsuario()));
	}
}