package com.algaworks.brewer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "estado")
public class Estado extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 9203080112075907085L;
	
	@NotBlank( message = "O nome é obrigatório")
	@Size(max = 50, message = "O nome deve conter no máximo caracteres")
	private String nome;
	
	@NotBlank( message = "A sigla é obrigatória")
	@Size(max = 2, message = "A sigla deve conter no máximo 2 caracteres")
	private String sigla;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
