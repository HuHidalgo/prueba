package com.unmsm.clinica.controller.mantenimiento;

import static com.unmsm.clinica.utilitario.ConstantesGenerales.PAGINA_MANTENIMIENTO;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.PAGINA_MANTENIMIENTO_ALUMNO;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_MANTENIMIENTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unmsm.clinica.aspecto.anotacion.Audit;
import com.unmsm.clinica.aspecto.enumeracion.Accion;
import com.unmsm.clinica.aspecto.enumeracion.Comentario;
import com.unmsm.clinica.aspecto.enumeracion.Tipo;
import com.unmsm.clinica.controller.excepcion.anotacion.Vista;
import com.unmsm.clinica.service.IFacultadService;
import com.unmsm.clinica.service.IMultiTabDetService;
import com.unmsm.clinica.service.IParametroGeneralService;
import com.unmsm.clinica.service.IPersonaService;
import com.unmsm.clinica.utilitario.MultiTablaUtil;

@Vista
@Audit(accion = Accion.Visita, comentario = Comentario.Visita)
@RequestMapping("/mantenimiento")
public @Controller class MantenimientoController {
	private @Autowired IPersonaService personaService;
	private @Autowired IFacultadService facultadService;
	private @Autowired IMultiTabDetService multiTabDetService;
	private @Autowired IParametroGeneralService parametroGeneralService;

	@Audit(tipo = Tipo.Persona)
	@GetMapping("/{mantenimiento:persona}")
	public String irPaginaMantenimientoPersona(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		model.addAttribute("tiposDocumento", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_DOCUMENTO));
		model.addAttribute("sexos", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_SEXO));
		return PAGINA_MANTENIMIENTO;
	}

	@Audit(tipo = Tipo.Medico)
	@GetMapping("/{mantenimiento:medico}")
	public String irPaginaMantenimientoMedico(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute("personas", personaService.buscarTodos());
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		return PAGINA_MANTENIMIENTO;
	}

	@Audit(tipo = Tipo.Alumno)
	@GetMapping("/{mantenimiento:alumno}")
	public String irPaginaMantenimientoAlumno(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		model.addAttribute("tiposDocumento", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_DOCUMENTO));
		model.addAttribute("tiposDocumento2", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_DOCUMENTO));
		model.addAttribute("sexos", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_SEXO));
		model.addAttribute("tiposAlumno", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ALUMNO));
		model.addAttribute("discapacidades", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_DISCAPACIDAD));
		model.addAttribute("facultades", facultadService.buscarTodos());
		return PAGINA_MANTENIMIENTO_ALUMNO;
	}

	@Audit(tipo = Tipo.Campania)
	@GetMapping("/{mantenimiento:campania}")
	public String irPaginaMantenimientoCampania(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		model.addAttribute("tiposCertificado",
				multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_CERTIFICADO));
		return PAGINA_MANTENIMIENTO;
	}

	@GetMapping("/{mantenimiento:especialidad}")
	public String irPaginaMantenimientoEspecialidad(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		return PAGINA_MANTENIMIENTO;
	}

	@Audit(tipo = Tipo.MulTabCab)
	@GetMapping("/{mantenimiento:multitabcab}")
	public String irPaginaMantenimientoTabladeTabla(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		return "seguras/mantenimiento/multiTabla";
	}

	@GetMapping("/{mantenimiento:facultad}")
	public String irPaginaMantenimientoFacultad(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		return PAGINA_MANTENIMIENTO;
	}

	@GetMapping("/{mantenimiento:escuela}")
	public String irPaginaMantenimientoEscuela(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		model.addAttribute("facultades", facultadService.buscarTodos());
		return PAGINA_MANTENIMIENTO;
	}

	@GetMapping("/{mantenimiento:parametroGeneral}")
	public String irPaginaMantenimientoParametroGeneral(@PathVariable String mantenimiento, ModelMap model) {
		model.addAttribute(P_MANTENIMIENTO, mantenimiento);
		model.addAttribute("parametroGeneral", parametroGeneralService.buscarTodos());
		return "seguras/mantenimiento/parametroGeneral";
	}
}