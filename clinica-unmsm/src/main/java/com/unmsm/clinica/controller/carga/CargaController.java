package com.unmsm.clinica.controller.carga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.unmsm.clinica.controller.excepcion.anotacion.Vista;
import com.unmsm.clinica.service.IMultiTabDetService;
import com.unmsm.clinica.utilitario.MultiTablaUtil;

@Vista
public @Controller class CargaController {
	private @Autowired IMultiTabDetService multiTabDetService;

	@GetMapping("/carga/alumno")
	public String irPaginaCargaAlumno(ModelMap model) {
		//model.addAttribute("tiposAlumno", multiTabDetService.buscarPorIdTabla(MultiTablaUtil.TABLA_TIPO_ALUMNO));
		return "seguras/carga/alumno";
	}
}