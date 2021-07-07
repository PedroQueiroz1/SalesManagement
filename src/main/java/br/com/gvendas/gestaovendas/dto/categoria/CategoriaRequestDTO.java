package br.com.gvendas.gestaovendas.dto.categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.gvendas.gestaovendas.entity.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Categoria requisição DTO")
public class CategoriaRequestDTO {

	@ApiModelProperty(value="Nome")
	@NotBlank(message = "Nome")
	@Size(min = 3, max = 50, message = "Nome")
	private String nome;

	
	public Categoria cParaCRequestDTO() {
		return new Categoria(nome);
	}
	
	public Categoria cParaCRequestDTO(Long codigo) {
		return new Categoria(codigo, nome);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	}
