package br.com.gvendas.gestaovendas.dto.produto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gvendas.gestaovendas.entity.Categoria;
import br.com.gvendas.gestaovendas.entity.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Produto requisição DTO")
public class ProdutoRequestDTO {

	@ApiModelProperty(value="Descrição")
	@NotBlank(message = "Descrição")
	@Size(min = 3, max = 50, message = "Descrição")
	private String descricao;
	
	@ApiModelProperty(value = "Quantidade")
	@NotNull(message="Quantidade")
	private Integer quantidade;
	
	@ApiModelProperty(value = "Preço de custo")
	@NotNull(message="Preço de Custo")
	private BigDecimal precoCusto;
	
	@ApiModelProperty(value = "Preço")
	@NotNull(message="Preço")
	private BigDecimal precoVenda;
	
	@ApiModelProperty(value = "Observação")
	@Size(max = 500, message = "Observação")
	private String observacao;
	
	public Produto cParaPRequestDTO(Long codigo, Long codigoCategoria) {
		return new Produto(codigo,descricao,quantidade,precoCusto,precoVenda,
				observacao,new Categoria(codigoCategoria));
	}
	
	public Produto cParaPRequestDTO(Long codigoCategoria) {
		return new Produto(descricao,quantidade,precoCusto,precoVenda,
				observacao,new Categoria(codigoCategoria));
	}

	public ProdutoRequestDTO() {
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
}
