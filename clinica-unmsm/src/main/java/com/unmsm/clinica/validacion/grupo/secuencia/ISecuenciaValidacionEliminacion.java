package com.unmsm.clinica.validacion.grupo.secuencia;

import javax.validation.GroupSequence;

import com.unmsm.clinica.validacion.grupo.ILlave;
import com.unmsm.clinica.validacion.grupo.accion.IActualizacion;

@GroupSequence({ ILlave.class, IActualizacion.class })
public interface ISecuenciaValidacionEliminacion {

}