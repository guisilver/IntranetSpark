PrimeFaces.locales['pt'] = {
	closeText : 'Fechar',
	prevText : 'Anterior',
	nextText : 'Próximo',
	currentText : 'Começo',
	monthNames : [ 'Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio',
			'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro',
			'Dezembro' ],
	monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago',
			'Set', 'Out', 'Nov', 'Dez' ],
	dayNames : [ 'Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta',
			'Sexta', 'Sábado' ],
	dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab' ],
	dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S', 'S' ],
	weekHeader : 'Semana',
	firstDay : 0,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Só Horas',
	timeText : 'Tempo',
	hourText : 'Hora',
	minuteText : 'Minuto',
	secondText : 'Segundo',
	currentText : 'Data Atual',
	ampm : false,
	month : 'Mês',
	week : 'Semana',
	day : 'Dia',
	allDayText : 'Dia Todo'
};

function up(lstr) {
	var str = lstr.value;
	lstr.value = str.toUpperCase();
}

mobileDevice = (/android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i
		.test(navigator.userAgent.toLowerCase()));
PrimeFaces.widget.SelectOneMenu.prototype.focusFilter = function(timeout) {
	if (!mobileDevice) {
		if (timeout) {
			var $this = this;
			setTimeout(function() {
				$this.focusFilter();
			}, timeout);
		} else {
			this.filterInput.focus();
		}
	}
}

function validate(evt) {
	var theEvent = evt || window.event;
	var key = theEvent.keyCode || theEvent.which;
	if ((key < 47 || key > 58) && (key != 8) && (key != 9)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
	}
}