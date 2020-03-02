package com.algaworks.brewer.enums;

import com.algaworks.brewer.util.StringUtil;
import com.algaworks.brewer.model.validation.group.CnpjGroup;
import com.algaworks.brewer.model.validation.group.CpfGroup;

public enum TipoPessoa {
	
	FISICA("Física", "CPF", "000.000.000-00", CpfGroup.class),
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class);
	
	private String descricao;
	private String tipoPessoa;
	private String mascara;
	private Class<?> group;
	
	private TipoPessoa(String descricao, String tipoPessoa, String mascara, Class<?> group) {
		this.descricao = descricao;
		this.tipoPessoa = tipoPessoa;
		this.mascara = mascara;
		this.group = group;
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
	
	public Class<?> getGroup() {
		return group;
	}
}
