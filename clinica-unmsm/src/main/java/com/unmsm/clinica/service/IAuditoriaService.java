package com.unmsm.clinica.service;

import java.util.List;

import com.unmsm.clinica.aspecto.enumeracion.Accion;
import com.unmsm.clinica.aspecto.enumeracion.Comentario;
import com.unmsm.clinica.aspecto.enumeracion.Tipo;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAuditoria;
import com.unmsm.clinica.model.seguridad.Auditoria;

public interface IAuditoriaService extends IMantenibleService<Auditoria> {
	public List<Auditoria> buscarPistasAuditoriaPorCriterio(CriterioBusquedaAuditoria criterio);

	public void registrarAuditoria(Auditoria auditoria);

	public void registrarAuditoria(Tipo tipo, Comentario comentario, Accion accion, boolean exito, String nombreUsuario,
			String direccionIp);
}