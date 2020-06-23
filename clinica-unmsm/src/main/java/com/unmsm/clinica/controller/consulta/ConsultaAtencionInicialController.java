package com.unmsm.clinica.controller.consulta;

import static com.unmsm.clinica.utilitario.ConstantesGenerales.PAGINA_CONSULTA_ATENCION_INICIAL;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_CAMPANIAS;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_CONSULTA;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_FACULTADES;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_REGISTRO_EN_ATENCION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unmsm.clinica.controller.excepcion.anotacion.Vista;
import com.unmsm.clinica.service.ICampaniaService;
import com.unmsm.clinica.service.IEspecialidadService;
import com.unmsm.clinica.service.IFacultadService;
import com.unmsm.clinica.service.IMultiTabDetService;
import com.unmsm.clinica.utilitario.MultiTablaUtil;

@Vista
@RequestMapping("/consulta/atencion")
public @Controller class ConsultaAtencionInicialController {
	private @Autowired IFacultadService facultadService;
	private @Autowired ICampaniaService campaniaService;
	private @Autowired IMultiTabDetService multiTabDetService;
	private @Autowired IEspecialidadService especialidadService;

	@GetMapping("/{consulta:psicologia}")
	public String irPaginaConsultaAtencionInicialPsicologia(@PathVariable String consulta, ModelMap model) {
		model.addAttribute(P_CONSULTA, consulta);
		model.addAttribute(P_REGISTRO_EN_ATENCION, true);
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		model.addAttribute(P_CAMPANIAS, campaniaService.buscarPorEstadoActivo(true));
		model.addAttribute("resultadosPsicologico",
				multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RESULTADO_PSICOLOGICO));
		return PAGINA_CONSULTA_ATENCION_INICIAL;
	}

	@GetMapping("/{consulta:laboratorio}")
	public String irPaginaConsultaAtencionInicialLaboratorio(@PathVariable String consulta, ModelMap model) {
		model.addAttribute(P_CONSULTA, consulta);
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		model.addAttribute(P_CAMPANIAS, campaniaService.buscarPorEstadoActivo(true));
		return PAGINA_CONSULTA_ATENCION_INICIAL;
	}

	@GetMapping("/{consulta:radiologia}")
	public String irPaginaConsultaAtencionInicialRadiologia(@PathVariable String consulta, ModelMap model) {
		model.addAttribute(P_CONSULTA, consulta);
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		model.addAttribute(P_CAMPANIAS, campaniaService.buscarPorEstadoActivo(true));
		return PAGINA_CONSULTA_ATENCION_INICIAL;
	}

	@GetMapping("/{consulta:triaje}")
	public String irPaginaConsultaAtencionInicialTriaje(@PathVariable String consulta, ModelMap model) {
		model.addAttribute(P_CONSULTA, consulta);
		model.addAttribute(P_REGISTRO_EN_ATENCION, true);
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		model.addAttribute(P_CAMPANIAS, campaniaService.buscarPorEstadoActivo(true));
		return PAGINA_CONSULTA_ATENCION_INICIAL;
	}

	@GetMapping("/{consulta:odontologia}")
	public String irPaginaConsultaAtencionInicialOdontologia(@PathVariable String consulta, ModelMap model) {
		model.addAttribute(P_CONSULTA, consulta);
		model.addAttribute(P_REGISTRO_EN_ATENCION, true);
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		model.addAttribute(P_CAMPANIAS, campaniaService.buscarPorEstadoActivo(true));
		model.addAttribute("higienes", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_HIGIENE));
		model.addAttribute("protesis", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_PROTESIS));
		model.addAttribute("maloclusiones", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_MALOCLUSION));
		model.addAttribute("dientes", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TRATAMIENTO_DIENTE));
		return PAGINA_CONSULTA_ATENCION_INICIAL;
	}

	@GetMapping("/{consulta:medicinaGeneral}")
	public String irPaginaConsultaAtencionInicialMedicinaGeneral(@PathVariable String consulta, ModelMap model) {
		model.addAttribute(P_CONSULTA, consulta);
		model.addAttribute(P_REGISTRO_EN_ATENCION, true);
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		model.addAttribute("especialidades", especialidadService.buscarTodos());
		model.addAttribute(P_CAMPANIAS, campaniaService.buscarPorEstadoActivo(true));
		model.addAttribute("parentescos", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ANTECEDENTE));
		return PAGINA_CONSULTA_ATENCION_INICIAL;
	}
}