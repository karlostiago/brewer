var Brewer = Brewer || {};

Brewer.MaskCpfCnpj = (function() {
	
	function MaskCpfCnpj() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelTipoPessoa = $('[for=cpfCnpj]');
		this.inputCpfCnpj = $('#cpfCnpj');
	}
	
	MaskCpfCnpj.prototype.initializer = function() {
		this.radioTipoPessoa.on('change', onChangeRadioTipoPessoa.bind(this));
		var tipoPessoaSelecionada = this.radioTipoPessoa.filter(':checked')[0];
		
		if(tipoPessoaSelecionada) {
			aplicarMascara.call(this, $(tipoPessoaSelecionada));
		}
	}
	
	function onChangeRadioTipoPessoa(event) {
		var tipoPessoaSelecionada = $(event.currentTarget);
		aplicarMascara.call(this, tipoPessoaSelecionada);
		this.inputCpfCnpj.val('');		
	}
	
	function aplicarMascara(tipoPessoa) {
		this.labelTipoPessoa.text(tipoPessoa.data('document'));
		this.inputCpfCnpj.mask(tipoPessoa.data('mascara'));
		this.inputCpfCnpj.removeAttr('disabled');
	}
	
	return MaskCpfCnpj;
	
}());

$(function() {
	var maskCpfCnpj = new Brewer.MaskCpfCnpj();
	maskCpfCnpj.initializer();
});