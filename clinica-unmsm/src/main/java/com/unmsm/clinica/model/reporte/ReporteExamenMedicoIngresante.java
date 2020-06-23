package com.unmsm.clinica.model.reporte;

import lombok.Data;

@Data
public class ReporteExamenMedicoIngresante {
	private String codigoAlumno;
	private String tipoAlumno;
	private String descripcionTipoAlumno;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombres;
	private String descripcionEscuela;
	private String idGrupoSanguineo;
	private String descGrupoSanguineo;
	private String idRPR;
	private String descripcionRPR;
	private String idFactorRh;
	private String descFactorRh;

	private String idCie10;
	private String descCie10;
	private String idTipoAntecedente;
	private String alergia;
	private String diagnostico;
	private String resultado;

	private String idResultadoRadiologico;
	private String descripcionResultadoRadiologico;
	private String idResultadoPsicologico;
	private String descripcionResultadoPsicologico;
}