package com.unmsm.clinica.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;

import com.unmsm.clinica.model.procesoautomatico.Correo;

public interface IEnvioMailAsyncService {
	public void enviarCorreo(List<Correo> correos) throws MessagingException, InterruptedException, ExecutionException;
}