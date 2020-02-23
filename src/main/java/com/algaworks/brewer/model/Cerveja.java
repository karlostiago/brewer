package com.algaworks.brewer.model;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Cerveja implements Serializable {

	private static final long serialVersionUID = 5598601194272424129L;
	
	@NotBlank( message = "O SKU é obrigatório")
	private String sku;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@Size(min = 5, max = 255, message = "O tamanho da descrição deve estar entre 5 e 255 caracteres")
	private String descricao;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
