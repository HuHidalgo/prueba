package com.unmsm.clinica.model.movimiento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diente {
	private Integer idDiente;
	private Integer numeroRegistro;
	private String tratamiento;
	private String numeroDiente;
	private String anio;
}