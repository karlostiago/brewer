package com.algaworks.brewer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.algaworks.brewer.enums.TipoPessoa;
import com.algaworks.brewer.model.validation.ClienteGroupSequenceProvider;
import com.algaworks.brewer.model.validation.group.CnpjGroup;
import com.algaworks.brewer.model.validation.group.CpfGroup;

@Entity
@Table(name = "cliente")
@GroupSequenceProvider(ClienteGroupSequenceProvider.class)
public class Cliente extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 6325861829652918840L;

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	@NotNull(message = "Tipo pessoa é obrigatório")
	@Column(name = "tipo_pessoa")
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	
	@NotBlank(message = "CPF/CNPJ é obrigatório")
	@CPF(groups = CpfGroup.class)
	@CNPJ(groups = CnpjGroup.class)
	@Column(name = "cpf_cnpj")
	private String cpfCnpj;

	private String telefone;
	
	@Email(message = "email inválido")
	private String email;

	@Embedded
	private Endereco endereco;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		this.cpfCnpj = getCpfCnpjWithoutFormatter();
		this.nome = this.nome.toUpperCase();
		this.email = this.email.toUpperCase();
		this.endereco.setCep(this.endereco.getCep().toUpperCase());
		this.endereco.setComplemento(this.endereco.getComplemento().toUpperCase());
		this.endereco.setNumero(this.endereco.getNumero().toUpperCase());
		this.endereco.setLogradouro(this.endereco.getLogradouro().toUpperCase());
	}
	
	@PostLoad
	public void postLoad() {
		this.cpfCnpj = tipoPessoa.formatter(cpfCnpj);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getCpfCnpjWithoutFormatter() {
		return TipoPessoa.removeFormatter(this.cpfCnpj);
	}
}
