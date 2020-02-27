var Brewer = Brewer || {};

Brewer.UploadFoto = (function() {
	
	function UploadFoto() {
		this.nomeFoto = $('input[name=foto]');
		this.contentType = $('input[name=contentType]'); 
		this.fotoTemplate = $('#foto-cerveja').html();
		this.template = Handlebars.compile(this.fotoTemplate);
		this.containerFoto = $('.bw-upload');
		this.upload = this.containerFoto.find('.js-upload');
	}
	
	UploadFoto.prototype.initializer = function() {
		UIkit.upload('.js-upload', {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png)',
			url: this.containerFoto.data('url-fotos'),
			complete: onUploadComplete.bind(this)
		}); 
		
		if(this.nomeFoto.val()) {
			onUploadComplete.call(this, {nome: this.nomeFoto.val(), contentType: this.contentType.val()});
		}
	}
	
	function onUploadComplete(resposta) {
		
		var rNome = null;
		var rContentType = null;
		
		if(resposta.response == undefined) {
			rNome = resposta.nome;
			rContentType = resposta.contentType;
		}
		else {
			rNome = resposta.response.nome;
			rContentType = resposta.response.contentType;
		}
		
		this.nomeFoto.val(rNome);
		this.contentType.val(rContentType);
		
		this.upload.addClass('hide');
		
		var fotoHtml = this.template({nomeFoto: rNome});
		this.containerFoto.append(fotoHtml);
		
		$('.js-btn-remove').on('click', onRemoverFoto.bind(this));
	}
	
	function onRemoverFoto() {
		$('.js-foto-carregada').remove();
		this.upload.removeClass('hide');	
		this.nomeFoto.val('');
		this.contentType.val('');
	}
	
	return UploadFoto;
	
}());

$(function() {
	var uploadFoto = new Brewer.UploadFoto();
	uploadFoto.initializer();
})