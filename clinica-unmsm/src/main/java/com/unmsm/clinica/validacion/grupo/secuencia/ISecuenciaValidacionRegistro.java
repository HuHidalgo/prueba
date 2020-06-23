package com.unmsm.clinica.validacion.grupo.secuencia;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

import com.unmsm.clinica.validacion.grupo.ILlave;
import com.unmsm.clinica.validacion.grupo.accion.IRegistro;

@GroupSequence({ Default.class, ILlave.class, IRegistro.class })
public interface ISecuenciaValidacionRegistro {

}
