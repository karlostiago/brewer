package com.algaworks.brewer.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "estilo")
public class Estilo extends AbstractModel implements Serializable {
	
	private static final long serialVersionUID = 8041181155976949465L;

	@NotBlank(message = "O nome é obrigatório")
	@Size(max = 50, message = "O tamanho do nome deve ter no maxímo 50 caracteres")
	private String nome;
	
	@OneToMany(mappedBy = "estilo")
	private List<Cerveja> cervejas;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		nome = nome.toUpperCase();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Cerveja> getCervejas() {
		return cervejas;
	}

	public void setCervejas(List<Cerveja> cervejas) {
		this.cervejas = cervejas;
	}
}
