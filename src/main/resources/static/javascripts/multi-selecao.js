var Brewer = Brewer || {};

Brewer.MultiSelecao = (function() {
	
	function MultiSelecao() {
		this.btnEvent = $('.js-btn__event'); 
		this.selectCheckbox = $('.js-select-checkbox');
		this.selectAllCheckbox = $('.js-select-all-checkbox');
	}
	
	MultiSelecao.prototype.enable = function() {
		this.btnEvent.on('click', onClickEvent.bind(this));
	}
	
	function onClickEvent(e) {
		var clicked = $(e.currentTarget);
		var acao = clicked.data('value');
		var checked = this.selectCheckbox.filter(':checked');
		var codigos = $.map(checked, function(c){
			return $(c).data('codigo');
		});
		
		if(codigos.length > 0) {
			$.ajax({
				url: '/brewer/usuarios/status',
				method: 'put',
				data: {
					codigos: codigos,
					acao: acao
				},
				success: function() {
					window.location.reload();
				}
			});
		}
	}
	
	return MultiSelecao;
	
}());

$(function() {
	var multiSelecao = new Brewer.MultiSelecao();
	multiSelecao.enable();
});