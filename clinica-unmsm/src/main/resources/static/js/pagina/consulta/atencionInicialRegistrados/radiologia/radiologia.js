$(document).ready(function() {
	
	var $local = {
		$tablaAtencionInicial : $("#tablaAtencionInicial"),
		tablaAtencionInicial : "",
		$criterioBusquedaConsulta : $("#criterioBusquedaConsulta"),
		$filaSeleccionada : "",
		$facultades : $("#facultades"),
		$campanias : $("#campanias"),
		codigoAlumnoSeleccionado : "",
		tipoAlumnoSeleccionado : "",
		idCampaniaSeleccionado : 0,
		$buscar : $("#buscar"),
		$exportar : $("#exportar"),
		$codigoAlumno : $("#codigoAlumno"),
		$tiposTurno : $("#tiposTurno"),
		$rangoFechaBusqueda : $("#rangoFechaBusqueda"),
	};

	/* Variable Global */
	$formCriterioBusquedaConsulta = $("#formCriterioBusquedaConsulta");

	$funcionUtil.crearDateRangePickerConsulta($local.$rangoFechaBusqueda);
	$funcionUtil.crearSelect2($local.$facultades);
	$funcionUtil.crearSelect2($local.$campanias);
	$funcionUtil.crearSelect2($local.$tiposTurno);

	$local.tablaAtencionInicial = $local.$tablaAtencionInicial.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados."
		},
		"initComplete" : function() {
			$local.$tablaAtencionInicial.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaAtencionInicial);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5 ],
			"className" : "all filtrable",
		} ],
		"columns" : [ {
			"data" : function(row){
				return "<label class='label label-primary label-size-12'>" + row.numeroRegistro + "</label>";
			},
			"width": "10%",
			"className" : "all filtrable dt-center",
			"title" : 'Acción'
		}, {
			"data" : "fechaGeneracionNumeroRegistro",
			"title" : "Fecha Num. Reg.",
			"width": "15%",
			"className" : "all filtrable dt-center",
		}, {
			"data" : 'codigoAlumno',
			"width": "15%",
			"className" : "all filtrable dt-center",
			"title" : "Código de Alumno"
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
		} ]
	});

	$local.$tablaAtencionInicial.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaAtencionInicial.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaAtencionInicial.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaAtencionInicial.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});

	$local.$buscar.on("click", function() {
		var criterioBusqueda = $formCriterioBusquedaConsulta.serializeJSON();
		if ($funcionUtil.camposVacios($formCriterioBusquedaConsulta)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "reporte/atencion/diaria/registrados/radiologia?accion=buscar",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.idCampaniaSeleccionado = 0;
				$local.tablaAtencionInicial.clear().draw();
				$local.$buscar.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(atenciones) {
				if (atenciones.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}/*
				if (atenciones[0].tipoAlumno == 'R')
					criterioBusqueda.idCampania = "1";
				else
					criterioBusqueda.idCampania = "2";*/
				$local.$campanias.val(criterioBusqueda.idCampania).trigger("change.select2");
				$local.idCampaniaSeleccionado = criterioBusqueda.idCampania;
				$local.tablaAtencionInicial.rows.add(atenciones).draw();
			},
			complete : function() {
				$local.$buscar.attr("disabled", false).find("i").addClass("fa-search").removeClass("fa-spinner fa-pulse fa-fw");
				$local.$codigoAlumno.select();
			}
		});
	});
		
	$formCriterioBusquedaConsulta.find("input").keypress(function(event) {
		if (event.which == 13) {
			$local.$buscar.trigger("click");
			return false;
		}
	});
	
	$local.$exportar.on("click", function() {
		var criterioBusqueda = $formCriterioBusquedaConsulta.serializeJSON();
		criterioBusqueda.descripcionFacultad = $local.descripcionFacultad;	
		if ($funcionUtil.camposVacios($formCriterioBusquedaConsulta)) {
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
		criterioBusqueda.descripcionCampania = $local.$campanias.find("option:selected").text();
		var paramCriterioBusqueda = $.param(criterioBusqueda);
		window.location.href = $variableUtil.root + "reporte/atencion/diaria/registrados/radiologia?accion=exportar&" + paramCriterioBusqueda;
	});
});