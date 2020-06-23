package com.unmsm.clinica.service;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.mail.MessagingException;

import com.unmsm.clinica.model.criterio.CriterioBusquedaEnvioCorreo;

public interface IEjecucionProcesoManualService {
	public void ejecutarProcesoManual(CriterioBusquedaEnvioCorreo criterioBusquedaEnvioCorreo)
			throws IOException, MessagingException, InterruptedException, ExecutionException;
}