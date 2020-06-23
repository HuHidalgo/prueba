package com.unmsm.clinica.utilitario;

import com.unmsm.clinica.model.mantenimiento.Escuela;
import com.unmsm.clinica.model.mantenimiento.Escuela.EscuelaBuilder;

public class ConstantesFormatosExcelIngresante {
	/* Ingresante */
	public static final int CANTIDAD_FILA_INICIO = 1;
	public static final int CANTIDAD_FILA_FIN = 1;
	public static final int COLUMNA_CODIGO_POSTULANTE = 0;
	public static final int COLUMNA_TIPO_DOCUMENTO = 1;
	public static final int COLUMNA_NUMERO_DOCUMENTO = 2;
	public static final int COLUMNA_NOMBRES = 3;
	public static final int COLUMNA_APELLIDO_PATERNO = 4;
	public static final int COLUMNA_APELLIDO_MATERNO = 5;
	public static final int COLUMNA_SEXO = 6;
	public static final int COLUMNA_DIRECCION = 7;
	public static final int COLUMNA_UBIGEO = 8;
	public static final int COLUMNA_FECHA_NACIMIENTO = 9;
	public static final int COLUMNA_CODIGO_FACULTAD = 10;
	public static final int COLUMNA_CODIGO_ESCUELA = 11;
	public static final int COLUMNA_CORREO_INSTITUCIONAL = 12;
	public static final int COLUMNA_CORREO_PERSONAL = 13;
	public static final int COLUMNA_TELEFONO_FIJO = 14;
	public static final int COLUMNA_TELEFONO_MOVIL = 15;
	public static final int COLUMNA_DISCAPACIDAD = 16;

	private ConstantesFormatosExcelIngresante() {
		throw new UnsupportedOperationException(ConstantesExcepciones.NO_INSTANCIAR_CLASE_ESTATICA);
	}

	public static String obtenerIdTipoDocumento(String celdaTipoDocumento) {
		String idTipoDocumento = "";
		switch (celdaTipoDocumento) {
		case "D.N.I.":
			idTipoDocumento = "DNI";
			break;
		case "CARNET EXTRANJERIA":
			idTipoDocumento = "CEXT";
			break;
		default:
			idTipoDocumento = "N";
			break;
		}
		return idTipoDocumento;
	}

	public static String obtenerIdDiscapacidad(String celdaDiscapacidad) {
		String idDiscapacidad = "";
		switch (celdaDiscapacidad) {
		case "N":
			idDiscapacidad = "NO";
			break;
		case "S":
			idDiscapacidad = "VIS";
			break;
		default:
			idDiscapacidad = "NA";
			break;
		}
		return idDiscapacidad;
	}

	public static Escuela obtenerEscuela(String celdaEscuelaFacultad) {
		EscuelaBuilder escuela = Escuela.builder();
		int codigoFacultadEscuela = Integer.parseInt(celdaEscuelaFacultad);
		if (codigoFacultadEscuela >= 611) {
			escuela.codigoFacultad(6).codigoEscuela(11);
		} else {
			int codigoEscuela = Integer.parseInt(
					celdaEscuelaFacultad.substring(celdaEscuelaFacultad.length() - 1, celdaEscuelaFacultad.length()));
			int codigoFacultad = Integer.parseInt(celdaEscuelaFacultad.substring(0, celdaEscuelaFacultad.length() - 1));
			escuela.codigoFacultad(codigoFacultad).codigoEscuela(codigoEscuela);
		}
		return escuela.build();
	}
}