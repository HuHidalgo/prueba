$(document).ready(function() {

	var $local = {
		$campanias : $("#campanias"),
		$facultades : $("#facultades"),
		$escuelas : $("#escuelas"),
		$buscar : $("#buscar"),
		$imprimir : $("#imprimir"),
		$tablaReporteDetalleResultadoExamenMedico : $("#tablaReporteDetalleResultadoExamenMedico"),
		tablaReporteDetalleResultadoExamenMedico : "",
		$rangoFechaBusqueda : $("#rangoFechaBusqueda"),
		idCampaniaSeleccionado : "",
		bloque: 0
	}

	$formCriterioBusquedaReporte = $("#formCriterioBusquedaReporte");

	$funcionUtil.crearDateRangePickerConsulta($local.$rangoFechaBusqueda);
	$funcionUtil.crearSelect2($local.$campanias);
	$funcionUtil.crearSelect2($local.$facultades);
	$funcionUtil.crearSelect2($local.$escuelas);

	$local.tablaReporteDetalleResultadoExamenMedico = $local.$tablaReporteDetalleResultadoExamenMedico.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaReporteDetalleResultadoExamenMedico.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaReporteDetalleResultadoExamenMedico);
		},
		order : [ [ 0, "desc" ] ],
		"columnDefs" : [ {
			"targets" : 0,
			"className" : "all dt-center",
			"render" : function(data, type, row, meta) {
				if (row.fechaAtencionExamenLaboratorio != null && row.fechaAtencionExamenRadiologico != null && row.fechaAtencionExamenPsicologico != null && row.fechaAtencionExamenOdontologia != null && row.fechaAtencionExamenMedicoGeneral != null) {
					return $variableUtil.botonExportarDocx;
				}
				return "";
			}
		}, {
			"targets" : [ 1, 2, 3, 4, 5, 15, 17, 18, 19, 20, 21 ],
			"className" : "all filtrable",
			"defaultContent" : "-"
		}, {
			"targets" : 6,
			"className" : "all filtrable",
			"render" : function(data, type, row, meta) {
				if (row.fechaAtencionExamenLaboratorio == null || row.fechaAtencionExamenLaboratorio == undefined) {
					return "<label class='label label-info label-size-12'>NO ATENDIDO</label>";
				} else {
					return row.fechaAtencionExamenLaboratorio;
				}
			}
		}, {
			"targets" : 7,
			"className" : "all filtrable celdaRPR",
			"render" : function(data, type, row, meta) {
				if (row.idRPR == null || row.idRPR == undefined) {
					return $variableUtil.labelNoRegistrado;
				} else if (row.idRPR == "R") {
					return $funcionUtil.unirCodigoDescripcion(row.idRPR, row.descripcionRPR) + " " + row.dilucion;
				} else {
					return $funcionUtil.unirCodigoDescripcion(row.idRPR, row.descripcionRPR);
				}
			}
		}, {
			"targets" : 8,
			"className" : "all filtrable",
			"render" : function(data, type, row, meta) {
				if (row.idGrupoSanguineo == null || row.idGrupoSanguineo == undefined) {
					return $variableUtil.labelNoRegistrado;
				} else {
					return $funcionUtil.unirCodigoDescripcion(row.idGrupoSanguineo, row.descGrupoSanguineo);
				}
			}
		}, {
			"targets" : 9,
			"className" : "all filtrable",
			"render" : function(data, type, row, meta) {
				if (row.idFactorRh == null || row.idFactorRh == undefined) {
					return $variableUtil.labelNoRegistrado;
				} else {
					return $funcionUtil.unirCodigoDescripcion(row.idFactorRh, row.descFactorRh);
				}
			}
		}, {
			"targets" : 10,
			"className" : "all filtrable",
			"render" : function(data, type, row, meta) {
				if (row.fechaAtencionExamenRadiologico == null || row.fechaAtencionExamenRadiologico == undefined) {
					return "<label class='label label-info label-size-12'>NO ATENDIDO</label>";
				} else {
					return row.fechaAtencionExamenRadiologico;
				}
			}
		}, {
			"targets" : 11,
			"className" : "all filtrable celdaResultadoRadiologico",
			"render" : function(data, type, row, meta) {
				if (row.idResultadoRadiologico == null || row.idResultadoRadiologico == undefined) {
					return $variableUtil.labelNoRegistrado;
				} else if (row.idResultadoRadiologico != "G") {
					return $funcionUtil.unirCodigoDescripcion(row.idResultadoRadiologico, row.descripcionResultadoRadiologico);
				} else {
					return "<label class='label label-info label-size-12'>" + $funcionUtil.unirCodigoDescripcion(row.idResultadoRadiologico, row.descripcionResultadoRadiologico) + "</label>";
				}
			}
		}, {
			"targets" : 12,
			"className" : "all filtrable",
			"render" : function(data, type, row, meta) {
				if (row.fechaAtencionExamenPsicologico == null || row.fechaAtencionExamenPsicologico == undefined) {
					return "<label class='label label-info label-size-12'>NO ATENDIDO</label>";
				} else {
					return row.fechaAtencionExamenPsicologico;
				}
			}
		}, {
			"targets" : 13,
			"className" : "all filtrable celdaResultadoPsicologico",
			"render" : function(data, type, row, meta) {
				if (row.idResultadoPsicologico == null || row.idResultadoPsicologico == undefined) {
					return $variableUtil.labelNoRegistrado;
				} else {
					return $funcionUtil.unirCodigoDescripcion(row.idResultadoPsicologico, row.descripcionResultadoPsicologico);
				}
			}
		} , {
			"targets" : 14,
			"className" : "all filtrable",
			"render" : function(data, type, row, meta) {
				if (row.fechaAtencionExamenOdontologia == null || row.fechaAtencionExamenOdontologia == undefined) {
					return "<label class='label label-info label-size-12'>NO ATENDIDO</label>";
				} else {
					return row.fechaAtencionExamenOdontologia;
				}
			}
		}, {
			"targets" : 16,
			"className" : "all filtrable",
			"render" : function(data, type, row, meta) {
				if (row.fechaAtencionExamenMedicoGeneral == null || row.fechaAtencionExamenMedicoGeneral == undefined) {
					return "<label class='label label-info label-size-12'>NO ATENDIDO</label>";
				} else {
					return row.fechaAtencionExamenMedicoGeneral;
				}
			}
		}],
		"columns" : [ {
			"data" : null,
			"title" : "Acción"
		}, {
			"data" : "codigoAlumno",
			"title" : "Código"
		}, {
			"data" : function(row) {
				return row.apellidoPaterno + " " + row.apellidoMaterno;
			},
			"title" : "Apellidos"
		}, {
			"data" : "nombres",
			"title" : "Nombres"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.idSexo, row.descripcionSexo);
			},
			"title" : "Sexo"
		}, {
			"data" : "edad",
			"title" : "Edad"
		}, {
			"data" : null,
			"title" : "F. Laboratorio"
		}, {
			"data" : null,
			"title" : "RPR"
		}, {
			"data" : null,
			"title" : "Grupo Sanguíneo"
		}, {
			"data" : null,
			"title" : "Factor Rh"
		}, {
			"data" : null,
			"title" : "F. Radiológico"
		}, {
			"data" : null,
			"title" : "Res. Radiológico"
		}, {
			"data" : null,
			"title" : "F. Psicológico"
		}, {
			"data" : null,
			"title" : "Res. Psicológico"
		}, {
			"data" : null,
			"title" : "F. Odontología"
		}, {
			"data" : function(row) {
				if (row.fechaAtencionExamenOdontologia == null)
					return $variableUtil.labelNoRegistrado;
				else
					return "<label class='label label-success label-size-12'>REVISADO</label>";
			},
			"title" : "Examen Dental"
		}, {
			"data" : null,
			"title" : "F. Medicina General"
		}, {
			"data" : function(row) {
				if (row.fechaAtencionExamenMedicoGeneral == null)
					return $variableUtil.labelNoRegistrado;
				else
					if (row.diagnostico == null)
						return "<label class='label label-success label-size-12'>NORMAL</label>";
					else
						return "<label class='label label-warning label-size-12'>CONSULTA</label>";
			},
			"title" : "Examen Clínico"
		}, {
			"data" : function(row) {
				if (row.fechaAtencionExamenMedicoGeneral == null)
					return $variableUtil.labelNoRegistrado;
				else
					if (row.diagnostico == null)
						return "<label class='label label-success label-size-12'>NORMAL</label>";
					else
						return row.diagnostico;
			},
			"title" : "Diagnóstico"
		}, {
			"data" : function(row) {
				if (row.fechaAtencionExamenMedicoGeneral == null)
					return $variableUtil.labelNoRegistrado;
				else
					if (row.alergia == 'No Presenta')
						return "<label class='label label-success label-size-12'>NO PRESENTA</label>";
					else
						return row.alergia;
			},
			"title" : "Alergias"
		}, {
			"data" : "descripcionFacultad",
			"title" : "Facultad"
		}, {
			"data" : "descripcionEscuela",
			"title" : "Escuela"
		}],
		"createdRow" : function(row, data, dataIndex) {
			var $fila = $(row);
			if (data.idRPR != null) {
				var $celda = $fila.find(".celdaRPR");
				if (data.idRPR == "R") {
					$celda.addClass("color-red");
				} else {
					$celda.addClass("color-blue");
				}
			}
			if (data.hemoglobina != null && data.descripcionResultadoHemoglobina != null) {
				var $celda = $fila.find(".celdaHemoglobina");
				if (data.descripcionResultadoHemoglobina == "OBSERVADO") {
					$celda.addClass("color-red");
				} else {
					$celda.addClass("color-blue");
				}
			}
			if (data.idHemograma != null) {
				var $celda = $fila.find(".celdaHemograma");
				if (data.idHemograma == "O") {
					$celda.addClass("color-red");
				} else {
					$celda.addClass("color-blue");
				}
			}
			if (data.idResultadoRadiologico != null) {
				var $celda = $fila.find(".celdaResultadoRadiologico");
				if (data.idResultadoRadiologico == "N" || data.idResultadoRadiologico == "C") {
					$celda.addClass("color-blue");
				} else if (data.idResultadoRadiologico != "G") {
					$celda.addClass("color-red");
				}
			}
			if (data.idResultadoPsicologico != null) {
				var $celda = $fila.find(".celdaResultadoPsicologico");
				if (data.idResultadoPsicologico == "N") {
					$celda.addClass("color-blue");
				} else {
					$celda.addClass("color-red");
				}
			}
		},
	});

	$local.$facultades.on("change", function() {
		var codigoFacultad = $(this).val();
		if (codigoFacultad == null || codigoFacultad == undefined || codigoFacultad == -1) {
			$local.$escuelas.find("option:not(:eq(0))").remove();
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "mantenimiento/escuela/facultad/" + codigoFacultad,
			beforeSend : function(xhr) {
				$local.$escuelas.find("option:not(:eq(0))").remove();
				$local.$escuelas.parent().append("<span class='help-block cargando'><i class='fa fa-spinner fa-pulse fa-fw'></i> Cargando Escuelas</span>")
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formMantenimiento);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formMantenimiento);
				}
			},
			success : function(escuelas) {
				$.each(escuelas, function(i, escuela) {
					$local.$escuelas.append($("<option />").val(this.codigoEscuela).text(this.codigoEscuela + " - " + this.descripcion));
				});
			},
			complete : function() {
				$local.$escuelas.parent().find(".cargando").remove();
			}
		});
	});

	$local.$buscar.on("click", function() {
		var criterioBusqueda = $formCriterioBusquedaReporte.serializeJSON();
		if ($funcionUtil.camposVacios($formCriterioBusquedaReporte)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		$local.idCampaniaSeleccionado = criterioBusqueda.idCampania;
		var rangoFechaTxn = $funcionUtil.obtenerFechasDateRangePicker($local.$rangoFechaBusqueda);
		criterioBusqueda.fechaInicio = rangoFechaTxn.fechaInicio;
		criterioBusqueda.fechaFin = rangoFechaTxn.fechaFin;
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "reporte/detalle/resultado/examenMedico/ingresante?accion=buscar",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaReporteDetalleResultadoExamenMedico.clear().draw();
				$local.$buscar.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(resultadosExamenesMedico) {
				$local.bloque = resultadosExamenesMedico.length;
				if (resultadosExamenesMedico.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaReporteDetalleResultadoExamenMedico.rows.add(resultadosExamenesMedico).draw();
				$local.$imprimir.attr("disabled", false)
			},
			complete : function() {
				$local.$buscar.attr("disabled", false).find("i").addClass("fa-search").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$tablaReporteDetalleResultadoExamenMedico.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaReporteDetalleResultadoExamenMedico.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaReporteDetalleResultadoExamenMedico.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaReporteDetalleResultadoExamenMedico.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});

	$formCriterioBusquedaReporte.find("input").keypress(function(event) {
		if (event.which == 13) {
			$local.$buscar.trigger("click");
			return false;
		}
	});
		
	$local.$imprimir.on("click", function() {
		//var criterioBusqueda = $formCriterioBusquedaReporte.serializeJSON(); 
		if ($funcionUtil.camposVacios($formCriterioBusquedaReporte)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		
		var criterioBusqueda = {};
		criterioBusqueda.bloque = $local.bloque;
		var paramCriterioBusqueda = $.param(criterioBusqueda);
		//window.location.href = $variableUtil.root + "imprimir/examenMedico?accion=imprimirIngresantes&" + paramCriterioBusqueda;

		$.ajax({
			type : "GET",
			url : $variableUtil.root + "imprimir/examenMedico?accion=imprimirIngresantes&r" + paramCriterioBusqueda,
			data : criterioBusqueda,
			beforeSend : function(xhr) {
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formCriterioBusquedaReporte);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formCriterioBusquedaReporte);
				}
			},
			success : function(response) {
				$funcionUtil.notificarException("Se imprimieron los Certificados Médicos correctamente", "fa-check", "Aviso", "success");
			},
			complete : function() {
			}
		});
	});

	$local.$tablaReporteDetalleResultadoExamenMedico.children("tbody").on("click", ".exportar-docx", function() {
		$local.$filaSeleccionada = $(this).parents("tr");
		var reporte = $local.tablaReporteDetalleResultadoExamenMedico.row($local.$filaSeleccionada).data();
		console.log(reporte);
		var criterioBusqueda = {};
		criterioBusqueda.codigoAlumno = reporte.codigoAlumno;
		criterioBusqueda.idCampania = $local.idCampaniaSeleccionado;
		var paramCriterioBusqueda = $.param(criterioBusqueda);
		window.location.href = $variableUtil.root + "reporte/examenMedico?accion=exportarIngresante&" + paramCriterioBusqueda;
	});

});