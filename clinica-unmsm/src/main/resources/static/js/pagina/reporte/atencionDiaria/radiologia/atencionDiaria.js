$(document).ready(function() {

	var $local = {
		tablaReporteAtencionesDiariaDetalle : "",
		$tablaReporteAtencionesDiariaDetalle : $("#tablaReporteAtencionesDiariaDetalle"),
		tablaReporteAtencionesDiariaGeneral : "",
		$tablaReporteAtencionesDiariaGeneral : $("#tablaReporteAtencionesDiariaGeneral"),
		$buscar : $("#buscar"),
		$exportar : $("#exportar"),
		$tiposExamenMedico : $("#tiposExamenMedico"),
		$tiposTurno : $("#tiposTurno"),
		$campanias : $("#campanias"),
		$rangoFechaBusqueda : $("#rangoFechaBusqueda"),
		$facultades : $("#facultades"),
		$escuelas : $("#escuelas"),
		
		descripcionFacultad : ""
	}

	$formReporteAtencionDiaria = $("#formReporteAtencionDiaria");

	$funcionUtil.crearDateRangePickerConsulta($local.$rangoFechaBusqueda);
	$funcionUtil.crearSelect2($local.$campanias);
	$funcionUtil.crearSelect2($local.$tiposExamenMedico);
	$funcionUtil.crearSelect2($local.$tiposTurno);
	$funcionUtil.crearSelect2($local.$facultades);
	$funcionUtil.crearSelect2($local.$escuelas);

	//order : [ [ 0, "desc" ] ],
	
	$local.tablaReporteAtencionesDiariaDetalle = $local.$tablaReporteAtencionesDiariaDetalle.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaReporteAtencionesDiariaDetalle.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaReporteAtencionesDiariaDetalle);
		},
		"columnDefs" : [ {
			"targets" : [ 1, 2, 3, 4, 5, 6 ],
			"className" : "all filtrable",
			"defaultContent" : "-"
		}, {
			"targets" : 0,
			"width": "15%",
			"className" : "all filtrable dt-center",
			"render" : function(data, type, row, meta) {
				var texto = row.numeroRegistro != undefined && row.numeroRegistro != null && row.numeroRegistro > 0 ? row.numeroRegistro : "NO ATENDIDO";
				return "<label class='label label-primary label-size-12'>" + texto + "</label>";
			}
		} ],
		"columns" : [ {
			"data" : null,
			"title" : "Número de Registro"
		},{
			"data" : "fechaGeneracionNumeroRegistro",
			"title" : "Fecha Num. Reg.",
			"width": "15%",
			"className" : "all filtrable dt-center",
		},{
			"data" : 'codigoAlumno',
			"title" : "Código de Alumno",
			"width": "15%",
			"className" : "all filtrable dt-center",
		}, {
			"data" : function(row) {
				return row.apellidoPaterno + " " + row.apellidoMaterno;
			},
			"title" : "Apellidos"
		}, {
			"data" : function(row) {
				return row.nombres;
			},
			"title" : "Nombres"
		}, {
			"data" : "descripcionEscuela",
			"title" : "Escuela"
		}, {
			"data" : function(row) {
				if (row.resultado == null)
					return $variableUtil.labelNoRegistrado;
				else
					if (row.resultado == 'N' || row.resultado == 'C')
						return "<label class='label label-success label-size-12'>"+row.descripcionResultado+"</label>";
					else
						return "<label class='label label-danger label-size-12'>"+row.descripcionResultado+"</label>";
			},
			"className" : "all filtrable dt-center",
			"title" : "Resultado"
		} ]
	});

	$local.$tablaReporteAtencionesDiariaDetalle.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaReporteAtencionesDiariaDetalle.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaReporteAtencionesDiariaDetalle.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaReporteAtencionesDiariaDetalle.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});

	$local.tablaReporteAtencionesDiariaGeneral = $local.$tablaReporteAtencionesDiariaGeneral.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaReporteAtencionesDiariaGeneral.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaReporteAtencionesDiariaGeneral);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3 ],
			"className" : "all filtrable",
			"defaultContent" : "-"
		} ],
		"columns" : [ {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.codigoFacultad, row.descripcionFacultad);
			},
			"title" : "Facultad"
		}, {
			"data" : "cantidadObservados",
			"title" : "Observados"
		}, {
			"data" : "cantidadNoObservados",
			"title" : "No Observados"
		}, {
			"data" : function(row) {
				return row.cantidadObservados + row.cantidadNoObservados;
			},
			"title" : "Cantidad"
		} ],
		"footerCallback" : function(row, data, start, end, display) {
			var tieneData = $local.tablaReporteAtencionesDiariaGeneral == "" ? false : $local.tablaReporteAtencionesDiariaGeneral.data().any();
			var api = this.api(), data;
			if (tieneData) {
				cantidadTotal1 = api.column(1).data().reduce(function(a, b) {
					return parseInt(a) + parseInt(b);
				}, 0);
				cantidadTotal2 = api.column(2).data().reduce(function(a, b) {
					return parseInt(a) + parseInt(b);
				}, 0);
				cantidadTotal3 = api.column(3).data().reduce(function(a, b) {
					return parseInt(a) + parseInt(b);
				}, 0);
				$(api.column(1).footer()).html(cantidadTotal1);
				$(api.column(2).footer()).html(cantidadTotal2);
				$(api.column(3).footer()).html(cantidadTotal3);
			} else {
				$(api.column(1).footer()).html("");
				$(api.column(2).footer()).html("");
				$(api.column(3).footer()).html("");
			}
		},
	});

	$local.$tablaReporteAtencionesDiariaGeneral.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaReporteAtencionesDiariaGeneral.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaReporteAtencionesDiariaGeneral.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaReporteAtencionesDiariaGeneral.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
	
	$local.$facultades.on("change", function() {
		var codigoFacultad = $(this).val();
		if (codigoFacultad == null || codigoFacultad == -1) {
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
		var criterioBusqueda = $formReporteAtencionDiaria.serializeJSON();
		
		if ($funcionUtil.camposVacios($formReporteAtencionDiaria)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		var rangoFecha = $funcionUtil.obtenerFechasDateRangePicker($local.$rangoFechaBusqueda);
		criterioBusqueda.fechaInicio = rangoFecha.fechaInicio;
		criterioBusqueda.fechaFin = rangoFecha.fechaFin;
		criterioBusqueda.tipoReporte = "DETALLE";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "reporte/atencion/diaria/radiologia?accion=buscar",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaReporteAtencionesDiariaDetalle.clear().draw();
				$local.$buscar.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(atencionesDiarias) {
				if (criterioBusqueda.codigoFacultad != '-1')
					$local.descripcionFacultad = atencionesDiarias[0].descripcionFacultad;
				else
					$local.descripcionFacultad = 'TODOS';
				
				if (atencionesDiarias.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaReporteAtencionesDiariaDetalle.rows.add(atencionesDiarias).draw();
			},
			complete : function() {
				$local.$buscar.attr("disabled", false).find("i").addClass("fa-search").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});

		criterioBusqueda.tipoReporte = "GENERAL";
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "reporte/atencion/diaria/radiologia?accion=buscar",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaReporteAtencionesDiariaGeneral.clear().draw();
			},
			success : function(atencionesDiarias) {
				if (atencionesDiarias.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaReporteAtencionesDiariaGeneral.rows.add(atencionesDiarias).draw();
			},
			complete : function() {
				$local.$buscar.attr("disabled", false).find("i").addClass("fa-search").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$exportar.on("click", function() {
		var criterioBusqueda = $formReporteAtencionDiaria.serializeJSON();
		criterioBusqueda.descripcionFacultad = $local.descripcionFacultad;	
		if ($funcionUtil.camposVacios($formReporteAtencionDiaria)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		
		if (criterioBusqueda.idTurno == '1')
			criterioBusqueda.descripcionTurno = 'MAÑANA';
		else
			if (criterioBusqueda.idTurno == '2')
				criterioBusqueda.descripcionTurno = 'TARDE';
			else
				criterioBusqueda.descripcionTurno = 'TODOS';
		
		var rangoFecha = $funcionUtil.obtenerFechasDateRangePicker($local.$rangoFechaBusqueda);
		criterioBusqueda.fechaInicio = rangoFecha.fechaInicio;
		criterioBusqueda.fechaFin = rangoFecha.fechaFin;
		var descripcionRangoFechas = $local.$rangoFechaBusqueda.val();
		criterioBusqueda.descripcionFechaGeneracionNumeroRegistro = descripcionRangoFechas == null || descripcionRangoFechas == undefined || descripcionRangoFechas == "" ? "TODOS" : descripcionRangoFechas;
		criterioBusqueda.descripcionExamenMedico = $local.$tiposExamenMedico.find("option:selected").text();
		criterioBusqueda.descripcionCampania = $local.$campanias.find("option:selected").text();
		var paramCriterioBusqueda = $.param(criterioBusqueda);
		window.location.href = $variableUtil.root + "reporte/atencion/diaria/radiologia?accion=exportar&" + paramCriterioBusqueda;
	});

});