$(document).ready(function() {
	
	var $local = {
		$tablaAtencionInicial : $("#tablaAtencionInicial"),
		tablaAtencionInicial : "",
		$criterioBusquedaConsulta : $("#criterioBusquedaConsulta"),
		$filaSeleccionada : "",
		$facultades : $("#facultades"),
		$campanias : $("#campanias"),
		$numeroRegistro : $("#numeroRegistro"),		
		codigoAlumnoSeleccionado : "",
		tipoAlumnoSeleccionado : "",
		idCampaniaSeleccionado : 0,
		$buscar : $("#buscar"),		
		$inputCodigo :$("#codigo"),
		$inputApellidosNombres :$("#apellidosNombres"),
		$inputPiezasFaltantes : $("#input-pFaltantes"),
		$inputPiezasObturadas : $("#input-pObturadas"),
		$inputPiezasAObturar : $("#input-AObturar"),
		$inputPiezasAExtraer : $("#input-AExtraer"),
		$selectMaloclusion : $("#select-maloclusion"),
		$selectHigiene : $("#select-higiene"),
		$selectProtesis : $("#select-protesis"),		
		$inputIndiceMasticacion: $("#input-indiceMasticacion"),		
		$textAObservacion: $("#observacion"),
		$modalResultado : $("#modalResultado"),
		$registrarResultado : $("#registrarResultado"),
		$codigoAlumno : $("#codigoAlumno"),
		
		/*Dientes*/
		$dientes : $("#dientes li a"),
		$dienteButton : $(".dienteButton"),
		numeroDiente : "",
		colorDiente: "",
		tratamientoDientes: []
		
	};

	/* Variable Global */
	$formResultadoExamenMedico = $("#formResultadoExamenMedico");
	$formCriterioBusquedaConsulta = $("#formCriterioBusquedaConsulta");
	
	$funcionUtil.crearSelect2($local.$facultades);
	$funcionUtil.crearSelect2($local.$campanias);
	$funcionUtil.crearSelect2($local.$selectMaloclusion, "Seleccione una Maloclusión");
	$funcionUtil.crearSelect2($local.$selectHigiene, "Seleccione un Estado de Higiene");
	$funcionUtil.crearSelect2($local.$selectProtesis, "Seleccione un Estado de Prótesis");
	
	$local.$modalResultado.PopupWindow({
		title : "Resultado de Examen Médico Odontológico",
		autoOpen : false,
		modal : false,
		height : $variableUtil.altoModalResultadoOdontologico,
		width : $variableUtil.anchoModalResultadoOdontologico	
	});
	
	$local.$modalResultado.on("open.popupwindow", function() {
		$formResultadoExamenMedico.find("input:not([disabled]):first").focus();
		$local.$modalResultado.PopupWindow("maximize");
	});
	
	$local.$modalResultado.on("close.popupwindow", function() {
		$local.$codigoAlumno.select();		
		$local.$filaSeleccionada = "";
	});

	$local.tablaAtencionInicial = $local.$tablaAtencionInicial.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados."
		},
		"initComplete" : function() {
			$local.$tablaAtencionInicial.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaAtencionInicial);
		},
		"order" : [ [ 0, "desc" ] ],
		"columnDefs" : [ {
			"targets" : [ 1, 2, 3, 4, 5, 6, 7, 8 ],
			"className" : "all filtrable",
		}, {
			"targets" : 0,
			"className" : "all dt-center",
			"render" : function(data, type, row, meta) {
				var elemento = "";
				if (row.numeroRegistro == null || row.numeroRegistro == undefined || row.numeroRegistro < 1 || row.numeroRegistro == "null") {
					elemento = $variableUtil.botonRegistrar;
				} else {
					elemento = $variableUtil.labelAtendido;
				}
				return elemento;
			}
		} ],
		"columns" : [ {
			"data" : null,
			"title" : 'Acción'
		}, {
			"data" : 'codigoAlumno',
			"className" : "all filtrable dt-center",
			"title" : "Código de Alumno"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.tipoAlumno, row.descripcionTipoAlumno);
			},
			"className" : "all filtrable dt-center",
			"title" : "Tipo de Alumno"
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
			"data" : "edad",
			"title" : "Edad"
		}, {
			"data" : "descripcionFacultad",
			"title" : "Facultad"
		}, {
			"data" : "descripcionEscuela",
			"title" : "Escuela"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.idSexo, row.descripcionSexo);
			},
			"title" : "Sexo"
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
		if (!$formCriterioBusquedaConsulta.valid()) {
			return;
		}
		var criterioBusqueda = $formCriterioBusquedaConsulta.serializeJSON();
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "consulta/atencion/odontologia?accion=buscar",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.idCampaniaSeleccionado = 0;
				$local.tablaAtencionInicial.clear().draw();//
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

	$formResultadoExamenMedico.find("input").keypress(function(event) {
		if (event.which == 13) {
			$local.$registrarResultado.trigger("click");
			return false;
		}
	});

	$local.$tablaAtencionInicial.children("tbody").on("click", ".registrar", function() {
		$funcionUtil.prepararFormularioRegistro($formResultadoExamenMedico);
		$formResultadoExamenMedico.find("input").val("0");
		$local.$filaSeleccionada = $(this).parents("tr");
		var alumno = $local.tablaAtencionInicial.row($local.$filaSeleccionada).data();
		var apellidosNombres = alumno.nombres + ", " + alumno.apellidoPaterno + " " + alumno.apellidoMaterno;
		$local.codigoAlumnoSeleccionado = alumno.codigoAlumno;
		$local.tipoAlumnoSeleccionado = alumno.tipoAlumno;				
		$local.$inputApellidosNombres.val(apellidosNombres);
		$funcionUtil.aniadirTitleParaTooltip($local.$inputApellidosNombres, apellidosNombres);
		$local.$inputCodigo.val(alumno.codigoAlumno);
		$local.$inputPiezasFaltantes.focus();
		$local.$selectMaloclusion.val("7").trigger("change.select2");
		$local.$selectHigiene.val("R").trigger("change.select2");
		$local.$selectProtesis.val("N").trigger("change.select2");	
		
		$.each($local.tratamientoDientes, function(i, dientes) {
			if (this.tratamiento == '2'){
				$('#'+this.numeroDiente).removeClass("btn-danger").addClass("btn-success");
			}
			if (this.tratamiento == '3'){
				$('#'+this.numeroDiente).removeClass("btn-warning").addClass("btn-success");	
			}
			if (this.tratamiento == '4'){
				$('#'+this.numeroDiente).removeClass("btn-info").addClass("btn-success");	
			}
			if (this.tratamiento == '5'){
				$('#'+this.numeroDiente).removeClass("btn-primary").addClass("btn-success");	
			}
		});
		$local.tratamientoDientes = [];
		
		$local.$modalResultado.PopupWindow("open");	
		
	});
	
	$local.$dienteButton.on("click", function() {
		var op = $(this).attr('id');
		var color = $(this).css('backgroundColor');
		
		if (color == 'rgb(68, 157, 68)')
			$local.colorDiente = '1';
		
		if (color == 'rgb(201, 48, 44)'){
			$local.colorDiente = '2';
		}		
		
		if (color == 'rgb(236, 151, 31)'){
			$local.colorDiente = '3';
		}			
		
		if (color == 'rgb(49, 176, 213)'){
			$local.colorDiente = '4';
		}
		
		if (color == 'rgb(48, 113, 169)'){
			$local.colorDiente = '5';
		}
		
		$local.numeroDiente = op;
	});
	
	$local.$dientes.on("click", function() {
		var op = $(this).attr('id');
		var tratamiento = {};
		
		tratamiento.tratamiento = op;
		tratamiento.numeroDiente = $local.numeroDiente;
		$local.tratamientoDientes.push(tratamiento);
		
		if ($local.colorDiente == '1'){
			if (op == '2'){
				$local.$inputPiezasFaltantes.val(parseInt($local.$inputPiezasFaltantes.val())+1);
				$('#'+$local.numeroDiente).removeClass("btn-success").addClass("btn-danger");
			}
			if (op == '3'){
				$local.$inputPiezasObturadas.val(parseInt($local.$inputPiezasObturadas.val())+1);		
				$('#'+$local.numeroDiente).removeClass("btn-success").addClass("btn-warning");	
			}
			if (op == '4'){
				$local.$inputPiezasAObturar.val(parseInt( $local.$inputPiezasAObturar.val())+1);
				$('#'+$local.numeroDiente).removeClass("btn-success").addClass("btn-info");
			}
			if (op == '5'){
				$local.$inputPiezasAExtraer.val(parseInt($local.$inputPiezasAExtraer.val())+1);
				$('#'+$local.numeroDiente).removeClass("btn-success").addClass("btn-primary");
			}
		} 
		else{
			if ($local.colorDiente == '2'){
				if (op == '1'){
					$local.$inputPiezasFaltantes.val(parseInt($local.$inputPiezasFaltantes.val())-1);		
					$('#'+$local.numeroDiente).removeClass("btn-danger").addClass("btn-success");
				}
				if (op == '3'){
					$local.$inputPiezasFaltantes.val(parseInt($local.$inputPiezasFaltantes.val())-1);		
					$local.$inputPiezasObturadas.val(parseInt($local.$inputPiezasObturadas.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-danger").addClass("btn-warning");	
				}
				if (op == '4'){
					$local.$inputPiezasFaltantes.val(parseInt($local.$inputPiezasFaltantes.val())-1);		
					$local.$inputPiezasAObturar.val(parseInt($local.$inputPiezasAObturar.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-danger").addClass("btn-info");
				}
				if (op == '5'){
					$local.$inputPiezasFaltantes.val(parseInt($local.$inputPiezasFaltantes.val())-1);		
					$local.$inputPiezasAExtraer.val(parseInt($local.$inputPiezasAExtraer.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-danger").addClass("btn-primary");
				}
			}
			if ($local.colorDiente == '3'){
				if (op == '1'){
					$local.$inputPiezasObturadas.val(parseInt($local.$inputPiezasObturadas.val())-1);		
					$('#'+$local.numeroDiente).removeClass("btn-warning").addClass("btn-success");
				}
				if (op == '2'){
					$local.$inputPiezasObturadas.val(parseInt($local.$inputPiezasObturadas.val())-1);		
					$local.$inputPiezasFaltantes.val(parseInt($local.$inputPiezasFaltantes.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-warning").addClass("btn-danger");	
				}
				if (op == '4'){
					$local.$inputPiezasObturadas.val(parseInt($local.$inputPiezasObturadas.val())-1);		
					$local.$inputPiezasAObturar.val(parseInt($local.$inputPiezasAObturar.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-warning").addClass("btn-info");
				}
				if (op == '5'){
					$local.$inputPiezasObturadas.val(parseInt($local.$inputPiezasObturadas.val())-1);		
					$local.$inputPiezasAExtraer.val(parseInt($local.$inputPiezasAExtraer.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-warning").addClass("btn-primary");
				}
			}
			if ($local.colorDiente == '4'){
				if (op == '1'){
					$local.$inputPiezasAObturar.val(parseInt($local.$inputPiezasAObturar.val())-1);		
					$('#'+$local.numeroDiente).removeClass("btn-info").addClass("btn-success");
				}
				if (op == '2'){
					$local.$inputPiezasAObturar.val(parseInt($local.$inputPiezasAObturar.val())-1);		
					$local.$inputPiezasFaltantes.val(parseInt($local.$inputPiezasFaltantes.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-info").addClass("btn-danger");	
				}
				if (op == '3'){
					$local.$inputPiezasAObturar.val(parseInt($local.$inputPiezasAObturar.val())-1);		
					$local.$inputPiezasObturadas.val(parseInt($local.$inputPiezasObturadas.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-info").addClass("btn-warning");
				}
				if (op == '5'){
					$local.$inputPiezasAObturar.val(parseInt($local.$inputPiezasAObturar.val())-1);		
					$local.$inputPiezasAExtraer.val(parseInt($local.$inputPiezasAExtraer.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-info").addClass("btn-primary");
				}
			}
			if ($local.colorDiente == '5'){
				if (op == '1'){
					$local.$inputPiezasAExtraer.val(parseInt($local.$inputPiezasAExtraer.val())-1);		
					$('#'+$local.numeroDiente).removeClass("btn-primary").addClass("btn-success");
				}
				if (op == '2'){
					$local.$inputPiezasAExtraer.val(parseInt($local.$inputPiezasAExtraer.val())-1);		
					$local.$inputPiezasFaltantes.val(parseInt($local.$inputPiezasFaltantes.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-primary").addClass("btn-danger");	
				}
				if (op == '3'){
					$local.$inputPiezasAExtraer.val(parseInt($local.$inputPiezasAExtraer.val())-1);		
					$local.$inputPiezasObturadas.val(parseInt($local.$inputPiezasObturadas.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-primary").addClass("btn-warning");
				}
				if (op == '4'){
					$local.$inputPiezasAExtraer.val(parseInt($local.$inputPiezasAExtraer.val())-1);		
					$local.$inputPiezasAObturar.val(parseInt($local.$inputPiezasAObturar.val())+1);
					$('#'+$local.numeroDiente).removeClass("btn-primary").addClass("btn-info");
				}
			}
		}
		
	});
	
	$local.$registrarResultado.on("click", function() {
		if (!$formResultadoExamenMedico.valid()) {
			return;
		}
		var examenMedicoOdontologico= $formResultadoExamenMedico.serializeJSON();
		examenMedicoOdontologico.dientes = $local.tratamientoDientes;
		examenMedicoOdontologico.idCampania = $local.idCampaniaSeleccionado;
		examenMedicoOdontologico.codigoAlumno = $local.codigoAlumnoSeleccionado;
		examenMedicoOdontologico.tipoAlumno = $local.tipoAlumnoSeleccionado;		
		$.ajax({
			type : "POST",
			url : $variableUtil.root + "examenmedico/odontologia",
			data : JSON.stringify(examenMedicoOdontologico),
			beforeSend : function(xhr) {
				$local.$registrarResultado.attr("disabled", true).find("i").removeClass("fa-pencil-square").addClass("fa-spinner fa-pulse fa-fw");
				xhr.setRequestHeader('Content-Type', 'application/json');
				xhr.setRequestHeader("X-CSRF-TOKEN", $variableUtil.csrf);
			},
			statusCode : {
				400 : function(response) {
					$funcionUtil.limpiarMensajesDeError($formResultadoExamenMedico);
					$funcionUtil.mostrarMensajeDeError(response.responseJSON, $formResultadoExamenMedico);
				}
			},
			success : function(response) {
				$funcionUtil.notificarException(response, "fa-check", "Aviso", "success");
				var alumno = $local.tablaAtencionInicial.row($local.$filaSeleccionada).data();
				alumno.numeroRegistro = 1;
				var row = $local.tablaAtencionInicial.row($local.$filaSeleccionada).data(alumno).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				$local.$modalResultado.PopupWindow("close");
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$registrarResultado.attr("disabled", false).find("i").addClass("fa-pencil-square").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$formCriterioBusquedaConsulta.find("input").keypress(function(event) {
		if (event.which == 13) {
			$local.$buscar.trigger("click");
			return false;
		}
	});
});