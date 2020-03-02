var Brewer = Brewer || {};

Brewer.ComboEstado = (function() {
	
	function ComboEstado() {
		this.combo = $('#estado');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboEstado.prototype.initializer = function() {
		this.combo.on('change', onChangeEstado.bind(this));
	}
	
	function onChangeEstado() {
		this.emitter.trigger('update', this.combo.val());
	}
	
	return ComboEstado;
}());

Brewer.ComboCidade = (function() {
	
	function ComboCidade(comboEstado) {
		this.comboEstado = comboEstado;
		this.combo = $('#cidade');
		this.loading = $('.js-loading');
		this.inputHiddenCidadeSelecionada = $('#inputHiddenCidadeSelecionada');
	}
	
	ComboCidade.prototype.initializer = function() {
		this.comboEstado.on('update', onUpdateEstado.bind(this));
		var codigoEstado = this.comboEstado.combo.val();
		initializerCidades.call(this, codigoEstado);
	}
	
	function onUpdateEstado(event, codigoEstado) {
		this.inputHiddenCidadeSelecionada.val('');
		initializerCidades.call(this, codigoEstado);
	}
	
	function initializerCidades(codigoEstado) {
		if(codigoEstado) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'get',
				contentType: 'application/json',
				data: {'estado': codigoEstado },
				beforeSend: initRequest.bind(this),
				complete: completeRequest.bind(this)
			});
			
			resposta.done(onPreencherComboCidade.bind(this));
		}
		else {
			reset.call(this);
		}
	}
	
	function onPreencherComboCidade(cidades) {
		var options = [];
		
		cidades.forEach(function(cidade){
			options.push('<option value="' + cidade.codigo + '">' + cidade.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
		var codigoCidadeSelecionada = this.inputHiddenCidadeSelecionada;
		if(codigoCidadeSelecionada) {
			this.combo.val(codigoCidadeSelecionada);
		}
	}
	
	function initRequest() {
		reset.call(this);
		this.loading.show();
	}
	
	function completeRequest() {
		this.loading.hide();			
	}
	
	function reset() {
		this.combo.html("<option value=''>Selecione a cidade</option>");
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	return ComboCidade;
}());

$(function() {
	var comboEstado = new Brewer.ComboEstado();
	comboEstado.initializer();
	
	var comboCidade = new Brewer.ComboCidade(comboEstado);
	comboCidade.initializer();
});