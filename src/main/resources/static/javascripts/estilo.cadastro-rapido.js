var Brewer = Brewer || {};

Brewer.EstiloCadastroRapido = (function() {
	
	function EstiloCadastroRapido() {
		this.modal = $('#modal-cadastro-rapido-estilo');
		this.btnSalvar = this.modal.find('.js-btn-salvar');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomeEstilo = $('#nome-estilo');
		this.containerMessageError = $('.js-error-message');		
	}
	
	EstiloCadastroRapido.prototype.initializer = function() {		
		this.form.on('submit', function(event) { event.preventDefault(); })
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		this.btnSalvar.on('click', onBtnSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomeEstilo.focus();
	}
	
	function onModalClose() {
		this.inputNomeEstilo.val('');
		this.containerMessageError.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}
	
	function onBtnSalvarClick() {
		var nomeEstilo = this.inputNomeEstilo.val().trim();
		$.ajax({
			url: this.url,
			method: 'post',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeEstilo }),
			error: onErrorSalvandoEstilo.bind(this),
			success: onEstiloSalvo.bind(this)
		});
	}
	
	function onEstiloSalvo(estilo) {
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		this.modal.modal('hide');
	}
	
	function onErrorSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		this.containerMessageError.removeClass('hidden');
		this.containerMessageError.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
		this.inputNomeEstilo.focus();
	}
	
	return EstiloCadastroRapido;
	
}());

$(function() {
	var estiloCadastroRapido = new Brewer.EstiloCadastroRapido();
	estiloCadastroRapido.initializer();
});