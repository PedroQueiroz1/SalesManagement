package br.com.gvendas.gestaovendas.dto.venda;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("ItemVenda retorno DTO")
public class ItemVendaResponseDTO {

	@ApiModelProperty(name ="Código")
	private Long codigo;
	
	@ApiModelProperty(name ="Quantidade")
	private Integer quantidade;
	
	@ApiModelProperty(name ="Valor")
	private BigDecimal valor;
	
	@ApiModelProperty(name ="Código do Produto")
	private Long codigoProduto;
	
	@ApiModelProperty(name ="Descrição do Produto")
	private String produtoDescricao;

	public ItemVendaResponseDTO() {
	}

	public ItemVendaResponseDTO(Long codigo, Integer quantidade, BigDecimal valor, Long codigoProduto,
			String produtoDescricao) {
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.valor = valor;
		this.codigoProduto = codigoProduto;
		this.produtoDescricao = produtoDescricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getProdutoDescricao() {
		return produtoDescricao;
	}

	public void setProdutoDescricao(String produtoDescricao) {
		this.produtoDescricao = produtoDescricao;
	}
	
	
	
}
