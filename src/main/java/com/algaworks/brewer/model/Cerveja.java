package com.algaworks.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.enums.Origem;
import com.algaworks.brewer.enums.Sabor;
import com.algaworks.brewer.validation.SKU;

@Entity
@Table(name = "cerveja")
public class Cerveja extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 5598601194272424129L;
	
	@SKU
	@NotBlank( message = "O SKU é obrigatório")
	private String sku;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@NotBlank( message = "A descrição é obrigatória")
	@Size(min = 5, max = 255, message = "O tamanho da descrição deve estar entre 5 e 255 caracteres")
	private String descricao;
	
	@NotNull(message = "O valor é obrigatório")
	@DecimalMin("0.01")
	@DecimalMax(value = "9999999.99", message = "O valor da cerveja deve ser menor que R$ 9.999.999.99")
	private BigDecimal valor;
	
	@NotNull(message = "O teor alcoólico é obrigatório")
	@DecimalMax(value = "100.0", message = "O teor alcoólico deve ser menor que 100")
	@Column(name = "teor_alcoolico")
	private BigDecimal teorAlcoolico;
	
	@NotNull(message = "A comissão é obrigatório")
	@DecimalMax(value = "100.0", message = "A comissão deve ser menor ou igual a 100")
	private BigDecimal comissao;
	
	@NotNull(message = "O estoque é obrigatório")
	@Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9.999")
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;
	
	@NotNull(message = "A origem é obrigatória")
	@Enumerated(EnumType.STRING)
	private Origem origem;
	
	@NotNull(message = "O sabor é obrigatório")
	@Enumerated(EnumType.STRING)
	private Sabor sabor;
	
	@ManyToOne
	@NotNull(message = "O estilo é obrigatório")
	@JoinColumn(name = "codigo_estilo")
	private Estilo estilo;
	
	private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@PrePersist
	@PreUpdate
	private void prePersistUpdate() {
		sku = sku.toUpperCase();
		descricao = descricao.toUpperCase();
		nome = nome.toUpperCase();
	}

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}
	
	public String getFoto() {
		return foto;
	}
	
	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "cerveja_mock.png";
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
