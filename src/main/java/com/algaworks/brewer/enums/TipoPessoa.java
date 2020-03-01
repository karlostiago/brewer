package com.algaworks.brewer.enums;

import com.algaworks.brewer.util.StringUtil;

public enum TipoPessoa {
	
	FISICA("Física", "CPF", "000.000.000-00"),
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00");
	
	private String descricao;
	private String tipoPessoa;
	private String mascara;
	
	private TipoPessoa(String descricao, String tipoPessoa, String mascara) {
		this.descricao = descricao;
		this.tipoPessoa = tipoPessoa;
		this.mascara = mascara;
	}
	
	public static String forString(String descricao) {
		
		String clearString = StringUtil.removerAcentos(descricao);
		
		if(FISICA.toString().equalsIgnoreCase(clearString)) {
			return "pessoaFisica";
		}
		
		if(JURIDICA.toString().equalsIgnoreCase(clearString)) {
			return "pessoaJuridica";
		}
		
		return null;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getTipoPessoa() {
		return tipoPessoa;
	}
	
	public String getMascara() {
		return mascara;
	}
}
