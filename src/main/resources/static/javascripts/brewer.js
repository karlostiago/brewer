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

$(function() {
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();
});