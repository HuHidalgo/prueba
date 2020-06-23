package com.unmsm.clinica.controller.reporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unmsm.clinica.controller.excepcion.anotacion.Vista;
import com.unmsm.clinica.service.ICampaniaService;
import com.unmsm.clinica.service.IFacultadService;

@Vista
@RequestMapping("/reporte/detalle")
public @Controller class ReporteDetalleController {
	private @Autowired IFacultadService facultadService;
	private @Autowired ICampaniaService campaniaService;

	@GetMapping("/resultado/examenMedico/{reporte:regular}")
	public String irPaginaReporteDetalleResultadoExamenMedicoRegular(ModelMap model, @PathVariable String reporte) {
		model.addAttribute("reporte", reporte);
		model.addAttribute("campanias", campaniaService.buscarTodos());
		model.addAttribute("facultades", facultadService.buscarTodos());
		return "seguras/reporte/reporteDetalleResultadoExamenMedico";
	}

	@GetMapping("/resultado/examenMedico/{reporte:ingresante}")
	public String irPaginaReporteDetalleResultadoExamenMedicoIngresante(ModelMap model, @PathVariable String reporte) {
		model.addAttribute("reporte", reporte);
		model.addAttribute("campanias", campaniaService.buscarTodos());
		model.addAttribute("facultades", facultadService.buscarTodos());
		return "seguras/reporte/reporteDetalleResultadoExamenMedico";
	}
}