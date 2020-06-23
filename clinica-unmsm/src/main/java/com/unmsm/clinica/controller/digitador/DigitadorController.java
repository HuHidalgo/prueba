package com.unmsm.clinica.controller.digitador;

import static com.unmsm.clinica.utilitario.ConstantesGenerales.PAGINA_DIGITADOR_REGISTRO_RESULTADO;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_CAMPANIAS;
import static com.unmsm.clinica.utilitario.ConstantesGenerales.P_FACULTADES;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unmsm.clinica.service.ICampaniaService;
import com.unmsm.clinica.service.IFacultadService;
import com.unmsm.clinica.service.IMultiTabDetService;
import com.unmsm.clinica.utilitario.MultiTablaUtil;

@RequestMapping("/digitador")
public @Controller class DigitadorController {
	private @Autowired IFacultadService facultadService;
	private @Autowired ICampaniaService campaniaService;
	private @Autowired IMultiTabDetService multiTabDetService;

	@GetMapping("/examenMedico")
	public String irPaginaRegistroResultado(ModelMap model) {
		model.addAttribute(P_FACULTADES, facultadService.buscarTodos());
		model.addAttribute(P_CAMPANIAS, campaniaService.buscarPorEstadoActivo(true));
		model.addAttribute("indicadoresRPR", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RPR));
		model.addAttribute("diluciones", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_DILUCION));
		model.addAttribute("higienes", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_HIGIENE));
		model.addAttribute("indicadoresHemograma", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_HEMOGRAMA));
		model.addAttribute("factoresRh", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_FACTOR_RH));
		model.addAttribute("protesis", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_PROTESIS));
		model.addAttribute("maloclusiones", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_MALOCLUSION));
		model.addAttribute("gruposSanguineo",
				multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_GRUPO_SANGUINEO));
		model.addAttribute("resultadosPsicologico",
				multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RESULTADO_PSICOLOGICO));
		model.addAttribute("resultadosRadiologico",
				multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_RESULTADO_RADIOLOGICO));
		return PAGINA_DIGITADOR_REGISTRO_RESULTADO;
	}
}