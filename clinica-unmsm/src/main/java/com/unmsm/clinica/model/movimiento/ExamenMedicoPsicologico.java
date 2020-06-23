package com.unmsm.clinica.model.movimiento;

import org.hibernate.validator.constraints.Length;

import com.unmsm.clinica.utilitario.MultiTablaUtil;
import com.unmsm.clinica.validacion.CodigoAlumno;
import com.unmsm.clinica.validacion.IdCampania;
import com.unmsm.clinica.validacion.MultitabDet;
import com.unmsm.clinica.validacion.NumRegPsicologico;
import com.unmsm.clinica.validacion.grupo.accion.IActualizacion;
import com.unmsm.clinica.validacion.grupo.accion.IRegistro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CodigoAlumno(existe = true, groups = IRegistro.class)
public class ExamenMedicoPsicologico {
	@NumRegPsicologico(existe = true, groups = IActualizacion.class)
	private Integer numeroRegistro;

	@MultitabDet(campoIdItem = "idResultadoPsicologico", idTabla = MultiTablaUtil.TABLA_RESULTADO_PSICOLOGICO, existe = true, min = 1, max = 1)
	private String idResultadoPsicologico;

	@Length(max = 250, message = "{Length.ExamenPsicologico.observacion}")
	private String observacion;

	@IdCampania(existe = true, groups = IRegistro.class)
	private Integer idCampania;
	private String codigoAlumno;
	private String tipoAlumno;
	private String idEstadoExamenMedico;

	/* Datos Adicionales */
	private String descripcionTipoAlumno;
	private String descripcionResultadoPsicologico;
	private String descripcionCampania;
	private String descripcionEstadoExamenMedico;
}