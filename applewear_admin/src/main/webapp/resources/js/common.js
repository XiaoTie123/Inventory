function notify(message, type) {
	$.growl({
		message: message
	}, {
		type: type,
		allow_dismiss: false,
		label: 'Cancel',
		className: 'btn-xs btn-inverse',
		placement: {
			from: 'top',
			align: 'center'
		},
		delay: 2500,
		animate: {
			enter: 'animated fadeInDown',
			exit: 'animated fadeOutUp'
		},
		offset: {
			x: 30,
			y: 30
		}
	});
};

$(document).ready(function() {

	$("input").attr({ "autocomplete": "off", "autocapitalize": "off", "spellcheck": "false", "autocorrect": "off" });

	if ($('#frmMessage').length && $('#frmMessage').val()) {
		var frmMessageValue = $("#frmMessage").val();
		if (frmMessageValue != null && typeof frmMessageValue != "undefined" && $.trim(frmMessageValue) !== "") {
			var messageArr = frmMessageValue.split("|");
			if (messageArr && messageArr.length == 2) {
				notify(messageArr[1], messageArr[0]);
			}
		}
	}

	if ($('.date-picker').length) {
		$('.date-picker').datepicker({
			autoclose: true,
			format: 'dd/mm/yyyy',
			todayHighlight: true
		});
	}

	if ($('.month-year-picker').length) {
		$('.month-year-picker').datepicker({
			autoclose: true,
			format: 'mm/yyyy',
			minViewMode: 1,
		});
	}

	if ($('.year-picker').length) {
		$('.year-picker').datepicker({
			autoclose: true,
			format: 'yyyy',
			minViewMode: 2,
		});
	}

	var getCurrentDate = function() {
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		if (dd < 10) {
			dd = '0' + dd;
		}

		if (mm < 10) {
			mm = '0' + mm;
		}
		return dd + '/' + mm + '/' + yyyy;
	}

	if ($('.date-picker.default-date-now').length) {
		if (!$('.date-picker.default-date-now').val()) {
			$('.date-picker.default-date-now').val(getCurrentDate());
		}
	}

	$(document).on("keypress keyup blur", ".decimal-input", function(event) {
		$(this).val($(this).val().replace(/[^0-9\.]/g, ''));
		if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});

	$(document).on("keypress keyup blur", ".integer-input", function(event) {
		$(this).val($(this).val().replace(/[^\d].+/, ""));
		if ((event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});

	$(document).on('click', '[data-toggle="lightbox"]', function(event) {
		event.preventDefault();
		$(this).ekkoLightbox();
	});

	if ($('.color-code-input').length) {
		$('.color-code-input').minicolors();
	}

	

	$(".file-upload").change(function(e) {
		$(this).parent().closest("div").find(".custom-file-label").text($(this).val().split('\\').pop());
		let imgPreview = $(this).parent().closest("div[class*='form-group']").find('img[class*="image-preview"]');
		if (imgPreview) {
			const file = e.target.files[0];
			if (file) {
				$(imgPreview).attr('src', URL.createObjectURL(file));
				$(imgPreview).show();
			}
		}
	});

});