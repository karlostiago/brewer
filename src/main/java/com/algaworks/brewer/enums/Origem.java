package com.algaworks.brewer.enums;

public enum Origem {
	
	NACIONAL("Nacional"),
	INTERNACIONAL("Internacional");
	
	private String descricao;

	private Origem(String descricao) {
		this.descricao = descricao;
	}
	
	public static int ordinal(String descricao) {
		if(NACIONAL.toString().equals(descricao.toUpperCase())) {
			return 1;
		}
		
		if(INTERNACIONAL.toString().equals(descricao.toUpperCase())) {
			return 2;
		}
		
		return -1;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
