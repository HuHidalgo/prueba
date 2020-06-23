$(document).ready(function() {

	$formResultadoExamenMedico.validate({
		focusCleanup : true,
		rules : {
			idResultadoRadiologico : {
				required : true,
				lettersonly : true,
				selectlength : [ 1, 1 ]
			}
		},
		messages : {
			idResultadoRadiologico : {
				required : "Seleccione un Resultado.",
				lettersonly : "El Resultado debe contener solo car&aacute;cteres.",
				selectlength : "El Resultado debe contener 1 car&aacute;cter."
			}
		}
	});
});