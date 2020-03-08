package com.algaworks.brewer.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.algaworks.brewer.validation.ValidadorDeSenha;

@ValidadorDeSenha(password = "senha", confirmation = "confirmacaoDeSenha")
@Entity
@Table(name = "usuario")
public class Usuario extends AbstractModel implements Serializable {

	private static final long serialVersionUID = -6473799305435091198L;
		
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotBlank(message = "O email é obrigatório")
	@Email(message = "E-mail invalído")
	private String email;
	
	private String senha;
	
	@Transient
	private String confirmacaoDeSenha;
	
	private Boolean ativo;
	
	@NotNull(message = "Data de nascimento é obrigatório")
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@NotNull(message = "Selecione pelo menos um grupo")
	@ManyToMany
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name = "codigo_usuario")
			,inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))
	private List<Grupo> grupos;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmacaoDeSenha() {
		return confirmacaoDeSenha;
	}

	public void setConfirmacaoDeSenha(String confirmacaoDeSenha) {
		this.confirmacaoDeSenha = confirmacaoDeSenha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
}
