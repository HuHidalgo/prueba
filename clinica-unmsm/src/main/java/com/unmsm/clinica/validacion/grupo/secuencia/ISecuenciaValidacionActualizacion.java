package com.unmsm.clinica.validacion.grupo.secuencia;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.unmsm.clinica.validacion.grupo.ILlave;
import com.unmsm.clinica.validacion.grupo.accion.IActualizacion;

@GroupSequence({ Default.class, ILlave.class, IActualizacion.class })
public interface ISecuenciaValidacionActualizacion {

}