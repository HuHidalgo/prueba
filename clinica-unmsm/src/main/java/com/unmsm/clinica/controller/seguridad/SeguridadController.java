package com.unmsm.clinica.controller.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unmsm.clinica.aspecto.anotacion.Audit;
import com.unmsm.clinica.aspecto.enumeracion.Accion;
import com.unmsm.clinica.aspecto.enumeracion.Comentario;
import com.unmsm.clinica.aspecto.enumeracion.Tipo;
import com.unmsm.clinica.controller.excepcion.anotacion.Vista;
import com.unmsm.clinica.service.IMultiTabDetService;
import com.unmsm.clinica.service.IPerfilService;
import com.unmsm.clinica.service.IPersonaService;
import com.unmsm.clinica.service.ITipoAuditoriaService;
import com.unmsm.clinica.service.IUsuarioService;
import com.unmsm.clinica.utilitario.MultiTablaUtil;

@Vista
@Audit(accion = Accion.Visita, comentario = Comentario.Visita)
@RequestMapping("/seguridad")
public @Controller class SeguridadController {
	private @Autowired IPerfilService perfilService;
	private @Autowired IPersonaService personaService;
	private @Autowired IUsuarioService usuarioService;
	private @Autowired IMultiTabDetService multiTabDetService;
	private @Autowired ITipoAuditoriaService tipoAuditoriaService;

	@Audit(tipo = Tipo.Usu)
	@GetMapping(value = "/{mantenimiento:usuario}")
	public String irPaginaMantenimientoUsuario(@PathVariable String mantenimiento, Model model) {
		model.addAttribute("mantenimiento", mantenimiento);
		model.addAttribute("perfiles", perfilService.buscarTodos());
		model.addAttribute("personas", personaService.buscarTodos());
		return "seguras/seguridad/mantenimiento";
	}

	@GetMapping(value = "/auditoria")
	public String irPaginaConsultaAuditoria(Model model) {
		model.addAttribute("usuarios", usuarioService.buscarTodos());
		model.addAttribute("tiposAuditoria", tipoAuditoriaService.buscarTodos());
		model.addAttribute("accionesAuditoria",
				multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_ACCION_AUDITORIA));
		return "seguras/auditoria/auditoria";
	}
}