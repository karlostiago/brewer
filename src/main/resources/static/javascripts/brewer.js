var Brewer = Brewer || {};

Brewer.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings){
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());

Brewer.MaskDate = (function() {
	
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;
}());

Brewer.MaskMoney = (function() {
	
	function MaskMoney() {		
		this.decimal = $('.js-decimal');
		this.integer = $('.js-integer');
	}
	
	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({ decimal: ',', thousands: '.' });
		this.integer.maskMoney({ precision: 0, thousands: '.' });
	}
	
	return MaskMoney;
	
}());

Brewer.MaskCep = (function() {
	
	function MaskCep() {
		this.cepMask = $('.js-cep-mask');
	}
	
	MaskCep.prototype.enable = function() {
		this.cepMask.on('keyup', onKeyUpMaskCep.bind(this));
	}
	
	function onKeyUpMaskCep(event) {
		var cepValue = $(event.target);
		cepValue.mask(cepValue.data('mascara'));
	}
	
	return MaskCep;
	
}());

Brewer.MaskPhone = (function() {
	
	function MaskPhone() {
		this.phoneMask = $('.js-phone-mask');
	}
	
	MaskPhone.prototype.enable = function() {
		var maskBehavior = function(val) {
			return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		}
		
		var options = {
			onKeyPress: function(val, e, field, options) {
				field.mask(maskBehavior.apply({}, arguments), options);
			}
		}
		
		this.phoneMask.mask(maskBehavior, options);
	}
	
	return MaskPhone;
	
}());

$(function() {
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();

	var maskPhone = new Brewer.MaskPhone();
	maskPhone.enable();
	
	var maskCep = new Brewer.MaskCep();
	maskCep.enable();
	
	var maskDate = new Brewer.MaskDate();
	maskDate.enable();
	
	var security = new Brewer.Security();
	security.enable();
});