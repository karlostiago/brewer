package com.algaworks.brewer.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cidade")
public class Cidade extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -7508380967515385844L;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotNull(message = "O estado é obrigatório")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_estado")
	@JsonIgnore
	private Estado estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public boolean temEstado() {
		return this.estado != null;
	}
}
