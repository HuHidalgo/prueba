package com.unmsm.clinica.service;

import java.util.concurrent.Future;

import javax.mail.MessagingException;

import com.unmsm.clinica.model.procesoautomatico.Correo;

public interface IEnvioMailAsyncExecutor {
	public Future<Void> enviarCorreo(Correo correo) throws MessagingException;
}