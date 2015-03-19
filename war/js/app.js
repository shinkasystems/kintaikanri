$(document).ready(function() {

	/*
	 * 申請画面
	 */
	$('#entry-form').submit(function() {
		$(this).find(':submit').attr('disabled', 'disabled');
	});

});
