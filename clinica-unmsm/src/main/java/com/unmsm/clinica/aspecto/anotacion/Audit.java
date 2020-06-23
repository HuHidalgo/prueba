package com.unmsm.clinica.aspecto.anotacion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.unmsm.clinica.aspecto.enumeracion.Accion;
import com.unmsm.clinica.aspecto.enumeracion.Comentario;
import com.unmsm.clinica.aspecto.enumeracion.Dato;
import com.unmsm.clinica.aspecto.enumeracion.Tipo;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Audit {
	Accion accion() default Accion.Ninguna;

	Comentario comentario() default Comentario.Ninguno;

	Tipo tipo() default Tipo.Ninguno;

	Dato datos() default Dato.Ninguno;
}