$(document).ready(function() {
	
	$(window).resize(function() {
		var width = $(window).width();
		if (width < 976) {
			var ds = $('.nav-right').css('display')
			if (ds == 'block') {
				$(".nav-right").css({
                    display : 'none'
				});
				
				$(".page-content-wrap").removeClass('submenu-mobile');
				$(".page-content-wrap").addClass('display1');
				
				$(".navbar2").removeClass('submenu-mobile2');
				$(".navbar2").addClass('display2');
			}			
		}
		
	});

	$(".mobile-options").on('click', function() {
		$(".navbar-container .nav-right").slideToggle('slow');
		var v = $(".page-content-wrap").position().top;
		if (v == 90) {
			$(".page-content-wrap").removeClass('display1');
			$(".page-content-wrap").addClass('submenu-mobile');
		} else {
			$(".page-content-wrap").addClass('submenu-mobile');
			$(".page-content-wrap").removeClass('submenu-mobile');
		}
		
		var v2 = $(".navbar2").position().top;
		if (v2 == 75) {
			$(".navbar2").removeClass('display2');
			$(".navbar2").addClass('submenu-mobile2');
		} else {
			$(".navbar2").addClass('submenu-mobile2');
			$(".navbar2").removeClass('submenu-mobile2');
		}
	});
	
	$(".menu-button").on('click', function() {
		$('#my-modal-menu').modal({backdrop: 'static', keyboard: false})
		var v = $(".mp-close-hide").css('right');
		//console.log(v);
		
		if (v == '-50px') {
			$(".mp-close-hide").addClass('mp-close-show');
		} else {
			$(".mp-close-hide").removeClass('mp-close-show');
			$("#my-modal-menu").modal("toggle");
		}		
	});
	
	$(".mp-close-hide").on('click', function() {
		$(".mp-close-hide").removeClass('mp-close-show');
		$("#my-modal-menu").modal("toggle");		
	});
		
		
	$(function () {
	  $('[data-toggle="tooltip"]').tooltip()
	})
	
	

});

