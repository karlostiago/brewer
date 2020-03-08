package com.algaworks.brewer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "permissao")
public class Permissao extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -334341703137060437L;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
