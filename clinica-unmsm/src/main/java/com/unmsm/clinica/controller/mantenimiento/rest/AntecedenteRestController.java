package com.unmsm.clinica.controller.mantenimiento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unmsm.clinica.model.movimiento.Antecedente;
import com.unmsm.clinica.service.IAntecedenteService;
import com.unmsm.clinica.utilitario.ConstantesGenerales;

@RequestMapping("/mantenimiento/antecedente")
public @RestController class AntecedenteRestController {
	private @Autowired IAntecedenteService antecedenteService;

	@DeleteMapping
	public ResponseEntity<?> eliminarAntecedente(@RequestBody Antecedente antecedente) {
		antecedenteService.eliminarAntecedente(antecedente);
		return ResponseEntity.ok(ConstantesGenerales.ELIMINACION_EXITOSA);
	}
}