package com.unmsm.clinica.service.impl.carga;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.unmsm.clinica.model.mantenimiento.Alumno;
import com.unmsm.clinica.model.mantenimiento.Alumno.AlumnoBuilder;
import com.unmsm.clinica.model.mantenimiento.Escuela;
import com.unmsm.clinica.service.IAlumnoService;
import com.unmsm.clinica.service.ICargaService;
import com.unmsm.clinica.service.excepcion.CargaArchivoException;
import com.unmsm.clinica.utilitario.ConstantesExcepciones;
import com.unmsm.clinica.utilitario.ConstantesFormatosExcelIngresante;
import com.unmsm.clinica.utilitario.ConstantesFormatosExcelRegular;

@Service
public class CargaService implements ICargaService {
	private @Autowired IAlumnoService alumnoService;

	public void cargarAlumnoRegular(MultipartFile archivoAlumnos, String tipoAlumno) {
		List<Alumno> alumnos = new ArrayList<>();

		boolean finExcel = false;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		int fila = 1;
		try {
			workbook = new XSSFWorkbook(archivoAlumnos.getInputStream());
		} catch (IOException e) {
			throw new CargaArchivoException(ConstantesExcepciones.ERROR_LECTURA_ARCHIVO);
		}
		worksheet = workbook.getSheetAt(0);
		worksheet.getRow(3);
		fila = ConstantesFormatosExcelRegular.CANTIDAD_FILA_INICIO;

		while (fila <= worksheet.getLastRowNum() && !finExcel) {
			XSSFRow row = worksheet.getRow(fila);

			AlumnoBuilder alumno = Alumno.builder();

			// CODIGO ALUMNO
			Cell codigoAlumno = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_CODIGO_ALUMNO);
			// System.out.println(codigoAlumno);
			if (codigoAlumno == null) {
				finExcel = true;
				continue;
			}
			codigoAlumno.setCellType(Cell.CELL_TYPE_STRING);
			alumno.codigoAlumno(codigoAlumno.getStringCellValue().trim());

			// TIPO ALUMNO
			alumno.tipoAlumno(tipoAlumno);

			// APELLIDO PATERNO
			Cell apellidoPaterno = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_APELLIDO_PATERNO);
			// System.out.println(apellidoPaterno);
			apellidoPaterno.setCellType(Cell.CELL_TYPE_STRING);
			alumno.apellidoPaterno(Optional.ofNullable(apellidoPaterno.getStringCellValue()).orElse("").trim());

			// APELLIDO MATERNO
			Cell apellidoMaterno = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_APELLIDO_MATERNO);
			// System.out.println(apellidoMaterno);
			apellidoMaterno.setCellType(Cell.CELL_TYPE_STRING);
			alumno.apellidoMaterno(apellidoMaterno.getStringCellValue().trim());

			// NOMBRES
			Cell nombres = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_NOMBRES);
			// System.out.println(nombres);
			nombres.setCellType(Cell.CELL_TYPE_STRING);
			alumno.nombres(nombres.getStringCellValue().trim());

			// SEXO
			Cell sexo = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_SEXO);
			// System.out.println(sexo);
			sexo.setCellType(Cell.CELL_TYPE_STRING);
			String idSexo = sexo.getStringCellValue().trim();
			alumno.idSexo(idSexo.length() == 1 ? idSexo : "N");

			// FECHA NACIMIENTO
			Cell fechaNacimiento = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_FECHA_NACIMIENTO);
			// System.out.println(fechaNacimiento);
			try {
				alumno.fechaNacimiento(fechaNacimiento.getDateCellValue());
			} catch (Exception e) {
				alumno.fechaNacimiento(null);
			}

			// TIPO DOCUMENTO
			Cell tipoDocumento = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_TIPO_DOCUMENTO);
			// System.out.println(tipoDocumento);
			tipoDocumento.setCellType(Cell.CELL_TYPE_STRING);
			alumno.idTipoDocumento(tipoDocumento.getStringCellValue().trim());

			// NUMERO DOCUMENTO
			Cell numeroDocumento = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_NUMERO_DOCUMENTO);
			// System.out.println(numeroDocumento);
			if (numeroDocumento == null) {
				alumno.numeroDocumento("");
			} else {
				numeroDocumento.setCellType(Cell.CELL_TYPE_STRING);
				alumno.numeroDocumento(numeroDocumento.getStringCellValue().trim());
			}

			// CORREO INSTITUCIONAL
			Cell correoInstitucional = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_CORREO_INSTITUCIONAL);
			// System.out.println(correoInstitucional);
			if (correoInstitucional == null) {
				alumno.correoInstitucional("");
			} else {
				correoInstitucional.setCellType(Cell.CELL_TYPE_STRING);
				alumno.correoInstitucional(correoInstitucional.getStringCellValue().trim());
			}

			// CORREO PERSONAL
			Cell correoPersonal = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_CORREO_PERSONAL);
			// System.out.println(correoPersonal);
			if (correoPersonal == null) {
				alumno.correoPersonal("");
			} else {
				correoPersonal.setCellType(Cell.CELL_TYPE_STRING);
				alumno.correoPersonal(correoPersonal.getStringCellValue().trim());
			}

			// DIRECCION
			Cell direccion = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_DIRECCION);
			// System.out.println(direccion);
			if (direccion == null) {
				alumno.direccion("");
			} else {
				direccion.setCellType(Cell.CELL_TYPE_STRING);
				alumno.direccion(direccion.getStringCellValue().trim());
			}

			// TELEFONO FIJO
			Cell telefonoFijo = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_TELEFONO_FIJO);
			// System.out.println(telefonoFijo);
			if (telefonoFijo == null) {
				alumno.telefonoFijo("");
			} else {
				telefonoFijo.setCellType(Cell.CELL_TYPE_STRING);
				alumno.telefonoFijo(telefonoFijo.getStringCellValue().trim());
			}

			// TELEFONO MOVIL
			Cell telefonoMovil = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_TELEFONO_MOVIL);
			// System.out.println(telefonoMovil);
			if (telefonoMovil == null) {
				alumno.telefonoMovil("");
			} else {
				telefonoMovil.setCellType(Cell.CELL_TYPE_STRING);
				alumno.telefonoMovil(telefonoMovil.getStringCellValue().trim());
			}

			// CODIGO ESCUELA
			Cell codigoEscuela = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_CODIGO_ESCUELA);
			// System.out.println(codigoEscuela);
			codigoEscuela.setCellType(Cell.CELL_TYPE_STRING);
			try {
				alumno.codigoEscuela(Integer.parseInt(codigoEscuela.getStringCellValue().trim()));
			} catch (Exception e) {
				e.printStackTrace();
				alumno.codigoEscuela(0);
			}

			// CODIGO FACULTAD
			Cell codigoFacultad = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_CODIGO_FACULTAD);
			// System.out.println(codigoFacultad);
			codigoFacultad.setCellType(Cell.CELL_TYPE_STRING);
			try {
				alumno.codigoFacultad(Integer.parseInt(codigoFacultad.getStringCellValue().trim()));
			} catch (Exception e) {
				e.printStackTrace();
				alumno.codigoFacultad(0);
			}

			// DISCAPACIDAD
			Cell discapacidad = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_DISCAPACIDAD);
			// System.out.println(discapacidad);
			if (discapacidad == null) {
				alumno.idDiscapacidad("N");
			} else {
				discapacidad.setCellType(Cell.CELL_TYPE_STRING);
				alumno.idDiscapacidad(discapacidad.getStringCellValue().trim());
			}

			alumnos.add(alumno.build());
			fila++;
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("FIN LECTURA EXCEL");
		System.out.println("ALUMNO: " + alumnos.size());
		alumnoService.registrarAlumnos(alumnos);
	}

	public void cargarAlumnoIngresante(MultipartFile archivoAlumnos, String tipoAlumno) {
		System.out.println("INICIANDO CARGA DE INGREZANTEZ");
		List<Alumno> alumnos = new ArrayList<>();

		boolean finExcel = false;
		XSSFWorkbook workbook = null;
		XSSFSheet worksheet = null;
		int fila = 1;
		try {
			workbook = new XSSFWorkbook(archivoAlumnos.getInputStream());
		} catch (IOException e) {
			throw new CargaArchivoException(ConstantesExcepciones.ERROR_LECTURA_ARCHIVO);
		}
		worksheet = workbook.getSheetAt(0);
		worksheet.getRow(3);
		fila = ConstantesFormatosExcelIngresante.CANTIDAD_FILA_INICIO;
		while (fila <= worksheet.getLastRowNum() && !finExcel) {
			XSSFRow row = worksheet.getRow(fila);

			AlumnoBuilder alumno = Alumno.builder();

			// CODIGO DE ALUMNO
			Cell codigoAlumno = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_CODIGO_POSTULANTE);
			if (codigoAlumno == null) {
				finExcel = true;
				continue;
			}
			codigoAlumno.setCellType(Cell.CELL_TYPE_STRING);
			alumno.codigoAlumno(codigoAlumno.getStringCellValue().trim());

			// TIPO DE ALUMNO
			alumno.tipoAlumno(tipoAlumno);

			// APELLIDO PATERNO
			Cell apellidoPaterno = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_APELLIDO_PATERNO);
			apellidoPaterno.setCellType(Cell.CELL_TYPE_STRING);
			alumno.apellidoPaterno(Optional.ofNullable(apellidoPaterno.getStringCellValue()).orElse("").trim());

			// APELLIDO MATERNO
			Cell apellidoMaterno = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_APELLIDO_MATERNO);
			apellidoMaterno.setCellType(Cell.CELL_TYPE_STRING);
			alumno.apellidoMaterno(apellidoMaterno.getStringCellValue().trim());

			// NOMBRES
			Cell nombres = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_NOMBRES);
			nombres.setCellType(Cell.CELL_TYPE_STRING);
			alumno.nombres(nombres.getStringCellValue().trim());

			// SEXO
			Cell sexo = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_SEXO);
			sexo.setCellType(Cell.CELL_TYPE_STRING);
			String idSexo = sexo.getStringCellValue().trim();
			alumno.idSexo(idSexo.length() == 1 ? idSexo : "N");

			// FECHA NACIMIENTO
			Cell fechaNacimiento = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_FECHA_NACIMIENTO);
			try {
				alumno.fechaNacimiento(fechaNacimiento.getDateCellValue());
			} catch (Exception e) {
				alumno.fechaNacimiento(null);
			}

			// TIPO DOCUMENTO
			Cell tipoDocumento = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_TIPO_DOCUMENTO);
			tipoDocumento.setCellType(Cell.CELL_TYPE_STRING);
			alumno.idTipoDocumento(ConstantesFormatosExcelIngresante
					.obtenerIdTipoDocumento(tipoDocumento.getStringCellValue().trim()));

			// NUMERO DOCUMENTO
			Cell numeroDocumento = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_NUMERO_DOCUMENTO);
			if (numeroDocumento == null) {
				alumno.numeroDocumento("");
			} else {
				numeroDocumento.setCellType(Cell.CELL_TYPE_STRING);
				alumno.numeroDocumento(numeroDocumento.getStringCellValue().trim());
			}

			// CORREO INSTITUCIONAL
			Cell correoInstitucional = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_CORREO_INSTITUCIONAL);
			// System.out.println(correoInstitucional);
			if (correoInstitucional == null) {
				alumno.correoInstitucional("");
			} else {
				correoInstitucional.setCellType(Cell.CELL_TYPE_STRING);
				alumno.correoInstitucional(correoInstitucional.getStringCellValue().trim());
			}

			// CORREO PERSONAL
			Cell correoPersonal = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_CORREO_PERSONAL);
			if (correoPersonal == null) {
				alumno.correoPersonal("");
			} else {
				correoPersonal.setCellType(Cell.CELL_TYPE_STRING);
				alumno.correoPersonal(correoPersonal.getStringCellValue().trim());
			}

			// DIRECCION
			Cell direccion = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_DIRECCION);
			if (direccion == null) {
				alumno.direccion("");
			} else {
				direccion.setCellType(Cell.CELL_TYPE_STRING);
				alumno.direccion(direccion.getStringCellValue().trim());
			}

			// UBIGEO
			Cell ubigeo = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_UBIGEO);
			ubigeo.setCellType(Cell.CELL_TYPE_STRING);
			alumno.codigoUbigeo(ubigeo.getStringCellValue().trim());

			// TELEFONO FIJO
			Cell telefonoFijo = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_TELEFONO_FIJO);
			if (telefonoFijo == null) {
				alumno.telefonoFijo("");
			} else {
				telefonoFijo.setCellType(Cell.CELL_TYPE_STRING);
				alumno.telefonoFijo(telefonoFijo.getStringCellValue().trim());
			}

			// TELEFONO MOVIL
			Cell telefonoMovil = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_TELEFONO_MOVIL);
			if (telefonoMovil == null) {
				alumno.telefonoMovil("");
			} else {
				telefonoMovil.setCellType(Cell.CELL_TYPE_STRING);
				alumno.telefonoMovil(telefonoMovil.getStringCellValue().trim());
			}

			// CODIGO ESCUELA
			Cell codigoEscuela = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_CODIGO_ESCUELA);
			// System.out.println(codigoEscuela);
			codigoEscuela.setCellType(Cell.CELL_TYPE_STRING);
			try {
				alumno.codigoEscuela(Integer.parseInt(codigoEscuela.getStringCellValue().trim()));
			} catch (Exception e) {
				e.printStackTrace();
				alumno.codigoEscuela(0);
			}

			// CODIGO FACULTAD
			Cell codigoFacultad = row.getCell(ConstantesFormatosExcelRegular.COLUMNA_CODIGO_FACULTAD);
			// System.out.println(codigoFacultad);
			codigoFacultad.setCellType(Cell.CELL_TYPE_STRING);
			try {
				alumno.codigoFacultad(Integer.parseInt(codigoFacultad.getStringCellValue().trim()));
			} catch (Exception e) {
				e.printStackTrace();
				alumno.codigoFacultad(0);
			}

			// DISCAPACIDAD
			Cell discapacidad = row.getCell(ConstantesFormatosExcelIngresante.COLUMNA_DISCAPACIDAD);
			if (discapacidad == null) {
				alumno.idDiscapacidad("N");
			} else {
				discapacidad.setCellType(Cell.CELL_TYPE_STRING);
				alumno.idDiscapacidad(discapacidad.getStringCellValue().trim());
			}

			alumnos.add(alumno.build());
			fila++;
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("FIN LECTURA EXCEL");
		System.out.println("ALUMNO: " + alumnos.size());
		alumnoService.registrarAlumnos(alumnos);
	}
}