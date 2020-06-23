$(document).ready(function() {

	var $local = {
		// Para Consulta 
		$tablaConsultaAdministrativaExamenMedico : $("#tablaConsultaAdministrativaExamenMedico"),
		$tablaConsultaAdministrativaExamenMedicoDetalle : $("#tablaConsultaAdministrativaExamenMedicoDetalle"),
		tablaConsultaAdministrativaExamenMedico : "",
		$filaSeleccionada : "",
		$facultades : $("#facultades"),
		$campanias : $("#campanias"),
		$tiposAlumno : $("#tiposAlumno"),
		$escuelas : $("#escuelas"),
		$buscar : $("#buscar"),
		$buscar1 : $("#buscar1"),
		$rangoFechaBusqueda : $("#rangoFechaBusqueda"),
		$administrador : $("#administrador"),

		// Para Actualizacion de Resultado
		$inputApellidosNombres :$("#apellidosNombres"),
		$inputPiezasFaltantes : $("#input-pFaltantes"),
		$inputPiezasObturadas : $("#input-pObturadas"),
		$inputPiezasAObturar : $("#input-AObturar"),
		$inputPiezasAExtraer : $("#input-AExtraer"),
		$selectMaloclusion : $("#select-maloclusion"),
		$selectMaloclusion2 : $("#select-maloclusion2"),
		$selectHigiene : $("#select-higiene"),
		$selectHigiene2 : $("#select-higiene2"),
		$selectProtesis : $("#select-protesis"),
		$selectProtesis2 : $("#select-protesis2"),		
		$inputIndiceMasticacion: $("#input-indiceMasticacion"),		
		$textAObservacion: $("#observacion"),
		$modalResultado : $("#modalResultado"),
		numeroRegistroSeleccionado : "",
		anioSeleccionado : "",
		$registrarResultado : $("#registrarResultado"),
		$numeroRegistro : $("#numeroRegistro"),
		
		/* Datos */
		$inputCodigo : $("#codigo"),
		
		/*Dientes*/
		$dientes : $("#dientes li a"),
		$dienteButton : $(".dienteButton"),
		numeroDiente : "",
		colorDiente: "",
		tratamientoDientes: [],
		borrarDientes : []
	};

	// Para Consulta
	$formCriterioBusquedaAdministrativaExamenMedico = $("#formCriterioBusquedaAdministrativaExamenMedico");
	$formCriterioBusquedaAdministrativaDetallada = $("#formCriterioBusquedaAdministrativaDetallada");

	$funcionUtil.crearDateRangePickerConsulta($local.$rangoFechaBusqueda);

	$funcionUtil.crearSelect2($local.$facultades);
	$funcionUtil.crearSelect2($local.$campanias);
	$funcionUtil.crearSelect2($local.$tiposAlumno);
	$funcionUtil.crearSelect2($local.$escuelas);
	$funcionUtil.crearSelect2($local.$selectMaloclusion2, "Seleccione una Maloclusión");
	$funcionUtil.crearSelect2($local.$selectHigiene2, "Seleccione un Estado de Higiene");
	$funcionUtil.crearSelect2($local.$selectProtesis2, "Seleccione un Estado de Prótesis");
	$funcionUtil.crearSelect2($local.$selectMaloclusion, "Seleccione una Maloclusión");
	$funcionUtil.crearSelect2($local.$selectHigiene, "Seleccione un Estado de Higiene");
	$funcionUtil.crearSelect2($local.$selectProtesis, "Seleccione un Estado de Prótesis");

	var esAdmin = $local.$administrador.length > 0;

	$local.tablaConsultaAdministrativaExamenMedico = $local.$tablaConsultaAdministrativaExamenMedico.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaConsultaAdministrativaExamenMedico.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaConsultaAdministrativaExamenMedico);
		},
		"columnDefs" : [ {
			"targets" : [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ],
			"className" : "all filtrable",
			"defaultContent" : "-"
		}, {
			"targets" : 0,
			"className" : "all dt-center",
			"render" : function(data, type, row, meta) {
				var elemento = "";
				if (esAdmin) {
					if (row.idEstadoExamenMedico == 'C') 
						elemento = $variableUtil.botonActualizar;
					else
						elemento = $variableUtil.labelNoAtendido;
				}
				return elemento;
			}
		} ],
		"columns" : [ {
			"data" : null,
			"title" : 'Acción'
		}, {
			"data" : "fechaRegistro",
			"title" : "Fecha Registro"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.idEstadoExamenMedico, row.descripcionEstadoExamenMedico);
			},
			"title" : "Estado Ex. Médico"
		}, {
			"data" : 'codigoAlumno',
			"title" : "Código de Alumno"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.tipoAlumno, row.descripcionTipoAlumno);
			},
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
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.idSexo, row.descripcionSexo);
			},
			"title" : "Sexo"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.idCampania, row.descripcionCampania);
			},
			"title" : "Campaña"
		}, {
			"data" : "descripcionEscuela",
			"title" : "Escuela"
		} ]
	});

	$local.$tablaConsultaAdministrativaExamenMedico.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaConsultaAdministrativaExamenMedico.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaConsultaAdministrativaExamenMedico.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaConsultaAdministrativaExamenMedico.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
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
	
	//Selecciona el tratamiento para el diente
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

	$local.$buscar.on("click", function() {
		var criterioBusqueda = $formCriterioBusquedaAdministrativaExamenMedico.serializeJSON();
		if ($funcionUtil.camposVacios($formCriterioBusquedaAdministrativaExamenMedico)) {
			$funcionUtil.notificarException($variableUtil.camposVacios, "fa-exclamation-circle", "Información", "info");
			return;
		}
		var rangoFechaTxn = $funcionUtil.obtenerFechasDateRangePicker($local.$rangoFechaBusqueda);
		criterioBusqueda.fechaInicio = rangoFechaTxn.fechaInicio;
		criterioBusqueda.fechaFin = rangoFechaTxn.fechaFin;
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "consulta/odontologia/administrativa?accion=buscarPorCriterioBusquedaAdministrativa",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaConsultaAdministrativaExamenMedico.clear().draw();
				$local.$buscar.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(examenesMedicoOdontologico) {
				if (examenesMedicoOdontologico.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaConsultaAdministrativaExamenMedico.rows.add(examenesMedicoOdontologico).draw();
			},
			complete : function() {
				$local.$buscar.attr("disabled", false).find("i").addClass("fa-search").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
		
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "consulta/odontologia/administrativa?accion=buscarPorCriterioBusquedaAdministrativa",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaConsultaAdministrativaExamenMedicoDetalle.clear().draw();
				$local.$buscar1.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(examenesMedicoOdontologico) {
				if (examenesMedicoOdontologico.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaConsultaAdministrativaExamenMedicoDetalle.rows.add(examenesMedicoOdontologico).draw();
			},
			complete : function() {
				$local.$buscar1.attr("disabled", false).find("i").addClass("fa-search").removeClass("fa-spinner fa-pulse fa-fw");
				$local.$numeroRegistro.select();
			}
		});
	});

	$formCriterioBusquedaAdministrativaExamenMedico.find("input").keypress(function(event) {
		if (event.which == 13) {
			$local.$buscar.trigger("click");
			return false;
		}
	});

	// Para Actualizacion de Resultado
	$formResultadoExamenMedico = $("#formResultadoExamenMedico");

	$local.$modalResultado.PopupWindow({
		title : "Resultado de Examen Médico Odontológico",
		autoOpen : false,
		modal : false,
		height : 600,
		width : 800
	});

	$local.$modalResultado.on("open.popupwindow", function() {
		$formResultadoExamenMedico.find("input:not([disabled]):first").focus();
		$local.$modalResultado.PopupWindow("maximize");
	});

	$local.$modalResultado.on("close.popupwindow", function() {
		$local.$filaSeleccionada = "";
		$local.numeroRegistroSeleccionado = 0;
		$local.anioSeleccionado = "";
	});

	$local.$tablaConsultaAdministrativaExamenMedico.children("tbody").on("click", ".actualizar", function() {
		$local.$filaSeleccionada = $(this).parents("tr");
		var examenMedicoOdontologico = $local.tablaConsultaAdministrativaExamenMedico.row($local.$filaSeleccionada).data();
		var apellidosNombres = examenMedicoOdontologico.nombres + ", " + examenMedicoOdontologico.apellidoPaterno + " " + examenMedicoOdontologico.apellidoMaterno;
		$local.numeroRegistroSeleccionado = examenMedicoOdontologico.numeroRegistro;
		$local.anioSeleccionado = examenMedicoOdontologico.anio;
		$local.$inputCodigo.val(examenMedicoOdontologico.codigoAlumno);
		$local.$inputApellidosNombres.val(apellidosNombres);
		$funcionUtil.aniadirTitleParaTooltip($local.$inputApellidosNombres, apellidosNombres);
		var criterioBusqueda = {
			numeroRegistro : $local.numeroRegistroSeleccionado,
		};
		//console.log(examenMedicoOdontologico);
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "consulta/odontologia?accion=buscarResultadoRegularPorNumeroRegistro",
			data : criterioBusqueda,
			beforeSend : function() {
				$(this).attr("disabled", true).find("i").removeClass("fa-floppy-o").addClass("fa-spinner fa-pulse fa-fw");
				///////////////////////////////////////////////////////////////////
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
				///////////////////////////////////////////////////////////////////////////////
				$.each($local.borrarDientes, function(i, dientes) {
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
				$local.borrarDientes = [];
				//////////////////////////////////////////////////////////////////////////////////////
			},
			success : function(examenesMedicoOdontologico) {
				if (examenesMedicoOdontologico.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				examenMedicoOdontologico = examenesMedicoOdontologico[0];
				$local.borrarDientes = examenMedicoOdontologico.dientes;
				$.each(examenMedicoOdontologico.dientes, function(i, dientes) {
					if (this.tratamiento == '2'){
						$('#'+this.numeroDiente).removeClass("btn-success").addClass("btn-danger");
					}
					if (this.tratamiento == '3'){
						$('#'+this.numeroDiente).removeClass("btn-success").addClass("btn-warning");	
					}
					if (this.tratamiento == '4'){
						$('#'+this.numeroDiente).removeClass("btn-success").addClass("btn-info");	
					}
					if (this.tratamiento == '5'){
						$('#'+this.numeroDiente).removeClass("btn-success").addClass("btn-primary");	
					}
				});
				$funcionUtil.prepararFormularioActualizacion($formResultadoExamenMedico);
				$funcionUtil.llenarFormulario(examenMedicoOdontologico, $formResultadoExamenMedico);				
				$local.$modalResultado.PopupWindow("open");
			},
			complete : function() {
				$(this).attr("disabled", false).find("i").addClass("fa-floppy-o").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$local.$registrarResultado.on("click", function() {
		if (!$formResultadoExamenMedico.valid()) {
			return;
		}
		var examenMedicoOdontologico = $formResultadoExamenMedico.serializeJSON();
		examenMedicoOdontologico.anio = $local.anioSeleccionado;
		examenMedicoOdontologico.dientes = $local.tratamientoDientes;
		examenMedicoOdontologico.numeroRegistro = $local.numeroRegistroSeleccionado;
		$.ajax({
			type : "PUT",
			url : $variableUtil.root + "examenmedico/odontologia",
			data : JSON.stringify(examenMedicoOdontologico),
			beforeSend : function(xhr) {
				$local.$registrarResultado.attr("disabled", true).find("i").removeClass("fa-floppy-o").addClass("fa-spinner fa-pulse fa-fw");
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
				$funcionUtil.notificarException($variableUtil.registroExitoso, "fa-check", "Aviso", "success");
				var examenMedicoOdontologico = $local.tablaConsultaAdministrativaExamenMedico.row($local.$filaSeleccionada).data();
				var row = $local.tablaConsultaAdministrativaExamenMedico.row($local.$filaSeleccionada).data(examenMedicoOdontologico).draw();
				row.show().draw(false);
				$(row.node()).animateHighlight();
				$local.$modalResultado.PopupWindow("close");
			},
			error : function(response) {
			},
			complete : function(response) {
				$local.$registrarResultado.attr("disabled", false).find("i").addClass("fa-floppy-o").removeClass("fa-spinner fa-pulse fa-fw");
			}
		});
	});

	$formResultadoExamenMedico.find("input").keypress(function(event) {
		if (event.which == 13) {
			$local.$buscar.trigger("click");
			return false;
		}
	});

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//						CONSULTA DETALLADA DE ODONTOLOGIA
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	$local.tablaConsultaAdministrativaExamenMedicoDetalle = $local.$tablaConsultaAdministrativaExamenMedicoDetalle.DataTable({
		"language" : {
			"emptyTable" : "No hay resultados para la búsqueda."
		},
		"initComplete" : function() {
			$local.$tablaConsultaAdministrativaExamenMedicoDetalle.wrap("<div class='table-responsive'></div>");
			$tablaFuncion.aniadirFiltroDeBusquedaEnEncabezado(this, $local.$tablaConsultaAdministrativaExamenMedicoDetalle);
		},
		"columnDefs" : [ {
			"targets" : [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ],
			"className" : "all filtrable",
			"defaultContent" : "-"
		}],
		"columns" : [ {
			"data" : "fechaRegistro",
			"title" : "Fecha Registro"
		}, {
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.idCampania, row.descripcionCampania);
			},
			"title" : "Campaña"
		}, {
			"data" : 'codigoAlumno',
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
			"data" : function(row) {
				return $funcionUtil.unirCodigoDescripcion(row.idSexo, row.descripcionSexo);
			},
			"title" : "Sexo"
		}, {
			"data" : "descProtesis",
			"title" : "Prótesis"
		}, {
			"data" : "descMaloclusion",
			"title" : "Maloclusión"
		}, {
			"data" : "descHigiene",
			"title" : "Higiene"
		}, {
			"data" : "piezasFaltantes",
			"title" : "P. Faltantes"
		}, {
			"data" : "piezasObturadas",
			"title" : "P. Obturadas"
		}, {
			"data" : "piezasPorObturar",
			"title" : "P. a Obturar"
		}, {
			"data" : "piezasPorExtraer",
			"title" : "P. a Extraer"
		}, {
			"data" : function(row) {
				return row.indiceMasticacion + " %";
			},
			"title" : "Ind. Masticación"
		}, {
			"data" : "observacion",
			"title" : "Observación"
		}]
	});
	
	$local.$tablaConsultaAdministrativaExamenMedicoDetalle.find("thead").on('keyup', 'input.filtrable', function() {
		$local.tablaConsultaAdministrativaExamenMedicoDetalle.column($(this).parent().index() + ':visible').search(this.value).draw();
	});

	$local.$tablaConsultaAdministrativaExamenMedicoDetalle.find("thead").on('change', 'select', function() {
		var val = $.fn.dataTable.util.escapeRegex($(this).val());
		$local.tablaConsultaAdministrativaExamenMedicoDetalle.column($(this).parent().index() + ':visible').search(val ? '^' + val + '$' : '', true, false).draw();
	});
		
	$local.$buscar1.on("click", function() {
		var criterioBusqueda = $formCriterioBusquedaAdministrativaDetallada.serializeJSON();
		if (!$formCriterioBusquedaAdministrativaDetallada.valid()) {
			return;
		}
		$.ajax({
			type : "GET",
			url : $variableUtil.root + "consulta/odontologia/administrativa?accion=buscarPorCriterioBusquedaAdministrativa",
			data : criterioBusqueda,
			beforeSend : function() {
				$local.tablaConsultaAdministrativaExamenMedicoDetalle.clear().draw();
				$local.$buscar1.attr("disabled", true).find("i").removeClass("fa-search").addClass("fa-spinner fa-pulse fa-fw");
			},
			success : function(examenesMedicoOdontologia) {
				if (examenesMedicoOdontologia.length == 0) {
					$funcionUtil.notificarException($variableUtil.busquedaSinResultados, "fa-exclamation-circle", "Información", "info");
					return;
				}
				$local.tablaConsultaAdministrativaExamenMedicoDetalle.rows.add(examenesMedicoOdontologia).draw();
			},
			complete : function() {
				$local.$buscar1.attr("disabled", false).find("i").addClass("fa-search").removeClass("fa-spinner fa-pulse fa-fw");
				$local.$numeroRegistro.select();
			}
		});
	});
	
	$formCriterioBusquedaAdministrativaDetallada.find("input").keypress(function(event) {
		if (event.which == 13) {
			$local.$buscar1.trigger("click");
			return false;
		}
	});

});