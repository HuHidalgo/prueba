package com.unmsm.clinica.service;

import java.util.List;

import javax.mail.MessagingException;

import com.unmsm.clinica.model.procesoautomatico.Correo;

public interface IEnvioMailService {
	public void enviarCorreo(List<Correo> correos) throws MessagingException;

	public void enviarCorreo(Correo correo) throws MessagingException;

	public void iniciarSesion(String emisor) throws MessagingException;

	public void cerrarSesion() throws MessagingException;
}