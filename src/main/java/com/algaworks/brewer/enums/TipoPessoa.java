package com.algaworks.brewer.enums;

import com.algaworks.brewer.util.StringUtil;
import com.algaworks.brewer.model.validation.group.CnpjGroup;
import com.algaworks.brewer.model.validation.group.CpfGroup;

public enum TipoPessoa {
	
	FISICA("Física", "CPF", "000.000.000-00", CpfGroup.class) {
		@Override
		public String formatter(String cpfCnpj) {
			return cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
		}
	},
	
	JURIDICA("Jurídica", "CNPJ", "00.000.000/0000-00", CnpjGroup.class) {
		@Override
		public String formatter(String cpfCnpj) {
			return cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-");
		}
	};
	
	private String descricao;
	private String tipoPessoa;
	private String mascara;
	private Class<?> group;
	
	public abstract String formatter(String cpfCnpj);
	
	private TipoPessoa(String descricao, String tipoPessoa, String mascara, Class<?> group) {
		this.descricao = descricao.toUpperCase();
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
	
	public static String removeFormatter(String cpfCnpj) {
		return cpfCnpj.replaceAll("\\.|-|/", "");
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
