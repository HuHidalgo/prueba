package com.unmsm.clinica.service.impl.seguridad;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.unmsm.clinica.aspecto.enumeracion.Accion;
import com.unmsm.clinica.aspecto.enumeracion.Comentario;
import com.unmsm.clinica.aspecto.enumeracion.Tipo;
import com.unmsm.clinica.mapper.IAuditoriaMapper;
import com.unmsm.clinica.mapper.base.IMantenibleMapper;
import com.unmsm.clinica.model.criterio.CriterioBusquedaAuditoria;
import com.unmsm.clinica.model.parametro.Parametro;
import com.unmsm.clinica.model.seguridad.Auditoria;
import com.unmsm.clinica.service.IAuditoriaService;
import com.unmsm.clinica.service.impl.MantenibleService;
import com.unmsm.clinica.utilitario.DatesUtils;
import com.unmsm.clinica.utilitario.Verbo;

@Service
public class AuditoriaService extends MantenibleService<Auditoria> implements IAuditoriaService {
	private IAuditoriaMapper auditoriaMapper;

	public AuditoriaService(@Qualifier("IAuditoriaMapper") IMantenibleMapper<Auditoria> mapper) {
		super(mapper);
		this.auditoriaMapper = (IAuditoriaMapper) mapper;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Auditoria> buscarPistasAuditoriaPorCriterio(CriterioBusquedaAuditoria criterioBusqueda) {
		return auditoriaMapper.buscarPistasAuditoriaPorCriterio(criterioBusqueda);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarAuditoria(Auditoria auditoria) {
		auditoria.setFecha(new Date());
		auditoria.setHora(DatesUtils.obtenerFechaEnFormato(new Date(), DatesUtils.FORMATO_HHMMSS));
		this.registrar(auditoria);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void registrarAuditoria(Tipo tipo, Comentario comentario, Accion accion, boolean exito, String nombreUsuario,
			String direccionIp) {
		Auditoria auditoria = Auditoria.builder().codigoAuditoria(tipo.name().toUpperCase()).idAccion(accion.toString())
				.comentario(comentario.toString()).exito(exito).direccionIp(direccionIp).nombreUsuario(nombreUsuario)
				.fecha(new Date()).hora(DatesUtils.obtenerFechaEnFormato(new Date(), DatesUtils.FORMATO_HHMMSS))
				.build();
		auditoriaMapper.mantener(new Parametro(Verbo.INSERT, auditoria));
	}
}