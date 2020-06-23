package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.model.mantenimiento.Persona;

public interface IPersonaService extends IMantenibleService<Persona> {
	public List<Persona> buscarTodos();

	public List<Persona> buscarPorTipoDocumentoNumeroDocumento(String idTipoDocumento, String numeroDocumento);

	public boolean existePersona(String idTipoDocumento, String numeroDocumento);

	public void registrarPersona(Persona persona);

	public void actualizarPersona(Persona persona);

	public void eliminarPersona(Persona persona);
}