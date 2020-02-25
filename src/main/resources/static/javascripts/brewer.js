$(function() {
	var decimal = $('.js-decimal');
	decimal.maskMoney();
	
	var integer = $('.js-integer');
	integer.maskMoney({ precision: 0 });
});