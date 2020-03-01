var Brewer = Brewer || {};

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
});