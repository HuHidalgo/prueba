package com.unmsm.clinica.validacion.grupo.secuencia;

import javax.validation.GroupSequence;

import com.unmsm.clinica.validacion.grupo.IMetodo;
import com.unmsm.clinica.validacion.grupo.IParametro;

@GroupSequence({ IParametro.class, IMetodo.class })
public interface ISecuenciaValidacionController {

}
