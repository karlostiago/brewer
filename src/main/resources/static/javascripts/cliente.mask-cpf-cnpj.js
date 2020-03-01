var Brewer = Brewer || {};

Brewer.MaskCpfCnpj = (function() {
	
	function MaskCpfCnpj() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelTipoPessoa = $('[for=cpfCnpj]');
		this.inputCpfCnpj = $('#cpfCnpj');
	}
	
	MaskCpfCnpj.prototype.initializer = function() {
		this.radioTipoPessoa.on('change', onChangeRadioTipoPessoa.bind(this));
	}
	
	function onChangeRadioTipoPessoa(event) {
		var tipoPessoaSelecionada = $(event.currentTarget);
		this.labelTipoPessoa.text(tipoPessoaSelecionada.data('document'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
		this.inputCpfCnpj.val('');
		this.inputCpfCnpj.removeAttr('disabled');
	}
	
	return MaskCpfCnpj;
	
}());

$(function() {
	var maskCpfCnpj = new Brewer.MaskCpfCnpj();
	maskCpfCnpj.initializer();
});