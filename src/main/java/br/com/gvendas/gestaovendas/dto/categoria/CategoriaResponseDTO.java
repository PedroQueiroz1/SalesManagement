package br.com.gvendas.gestaovendas.dto.categoria;

import br.com.gvendas.gestaovendas.entity.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Categoria retorno DTO")
public class CategoriaResponseDTO {

	@ApiModelProperty(value="Código")
	private Long codigo;
	
	@ApiModelProperty(value="Nome")
	private String nome;
	
	public static CategoriaResponseDTO convertarParaCategoriaDTO(Categoria categoria) {
		return new CategoriaResponseDTO(categoria.getCodigo(),categoria.getNome());
	}

	public CategoriaResponseDTO(Long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
