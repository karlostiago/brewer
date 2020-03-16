package com.algaworks.brewer.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.algaworks.brewer.util.StringUtil;

@Entity
@Table(name = "grupo")
public class Grupo extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -1743313270239640733L;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@ManyToMany
	@JoinTable(name = "grupo_permissao", joinColumns = @JoinColumn(name = "codigo_grupo")
		,inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
	private List<Permissao> permissoes;
	
	@PrePersist
	@PreUpdate
	private void preInsertUpdate() {
		this.nome = this.nome.toUpperCase();
	}
	
	@PostLoad
	public void postLoad() {
		this.nome = StringUtil.caixaAltaPrimeiroCaracter(nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
}
