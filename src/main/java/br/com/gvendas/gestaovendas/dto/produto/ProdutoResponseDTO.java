package br.com.gvendas.gestaovendas.dto.produto;

import java.math.BigDecimal;

import br.com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import br.com.gvendas.gestaovendas.entity.Produto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Produto retorno DTO")
public class ProdutoResponseDTO {

	@ApiModelProperty(value = "Código")
	private Long codigo;

	@ApiModelProperty(value = "Descrição")
	private String descricao;

	@ApiModelProperty(value = "Quantidade")
	private Integer quantidade;

	@ApiModelProperty(value = "Preço de custo")
	private BigDecimal precoCusto;

	@ApiModelProperty(value = "Preço")
	private BigDecimal precoVenda;

	@ApiModelProperty(value = "Observação")
	private String observacao;

	@ApiModelProperty(value = "Código categoria")
	private CategoriaResponseDTO categoriaDTO;

	public static ProdutoResponseDTO cParaPDTO(Produto produto) {
		return new ProdutoResponseDTO(produto.getCodigo(),produto.getDescricao(),
				produto.getQuantidade(),produto.getPrecoCusto(),produto.getPrecoVenda(),
				produto.getObservacao(),CategoriaResponseDTO.convertarParaCategoriaDTO(produto.getCategoria()));
	}


	public ProdutoResponseDTO(Long codigo, String descricao, Integer quantidade, BigDecimal precoCusto,
			BigDecimal precoVenda, String observacao, CategoriaResponseDTO categoriaDTO) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.observacao = observacao;
		this.categoriaDTO = categoriaDTO;
	}


	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}


	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public CategoriaResponseDTO getCategoriaDTO() {
		return categoriaDTO;
	}


	public void setCategoriaDTO(CategoriaResponseDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}


	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
