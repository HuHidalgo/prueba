package com.unmsm.clinica.controller.reporte;

import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_CONSULTA;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_FACULTADES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unmsm.clinica.controller.excepcion.anotacion.Vista;
import com.unmsm.clinica.service.ICampaniaService;
import com.unmsm.clinica.service.IFacultadService;
import com.unmsm.clinica.service.IMultiTabDetService;
import com.unmsm.clinica.utilitario.MultiTablaUtil;

@Vista
@RequestMapping("/reporte")
public @Controller class ReporteAtencionController {
	private @Autowired IFacultadService facultadService;
	private @Autowired ICampaniaService campaniaService;
	private @Autowired IMultiTabDetService multiTabDetService;

	@GetMapping("/atencion/diaria")
	public String irPaginaReporteAtencionDiaria(ModelMap model) {
		model.addAttribute("campanias", campaniaService.buscarTodos());
		model.addAttribute("tiposExamenMedico",
				multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_EXAMEN_MEDICO));
		return "seguras/reporte/reporteAtencionDiaria";
	}

	@GetMapping("/atencion/diaria/laboratorio")
	public String irPaginaReporteAtencionDiariaLaboratorio(ModelMap model) {
		model.addAttribute("campanias", campaniaService.buscarTodos());
		model.addAttribute("tiposTurno", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TURNO));
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		return "seguras/reporte/reporteAtencionDiariaLaboratorio";
	}

	@GetMapping("/atencion/diaria/registrados/{consulta:laboratorio}")
	public String irPaginaReporteAtencionDiariaRegistradosLaboratorio(@PathVariable String consulta, ModelMap model) {
		model.addAttribute(P_CONSULTA, consulta);
		model.addAttribute("campanias", campaniaService.buscarTodos());
		model.addAttribute("tiposTurno", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TURNO));
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		return "seguras/consulta/consultaAtencionInicialRegistrados";
	}

	@GetMapping("/atencion/diaria/radiologia")
	public String irPaginaReporteAtencionDiariaRadiologia(ModelMap model) {
		model.addAttribute("campanias", campaniaService.buscarTodos());
		model.addAttribute("tiposTurno", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TURNO));
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		return "seguras/reporte/reporteAtencionDiariaRadiologia";
	}

	@GetMapping("/atencion/diaria/registrados/{consulta:radiologia}")
	public String irPaginaReporteAtencionDiariaRegistradosRadiologia(@PathVariable String consulta, ModelMap model) {
		model.addAttribute(P_CONSULTA, consulta);
		model.addAttribute("campanias", campaniaService.buscarTodos());
		model.addAttribute("tiposTurno", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TURNO));
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		return "seguras/consulta/consultaAtencionInicialRegistrados";
	}

	@GetMapping("/atencion/diaria/psicologia")
	public String irPaginaReporteAtencionDiariaPsicologia(ModelMap model) {
		model.addAttribute("campanias", campaniaService.buscarTodos());
		model.addAttribute("tiposTurno", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TURNO));
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		return "seguras/reporte/reporteAtencionDiariaPsicologia";
	}
}