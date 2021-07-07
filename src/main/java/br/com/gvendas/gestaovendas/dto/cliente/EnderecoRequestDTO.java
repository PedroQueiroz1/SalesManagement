package br.com.gvendas.gestaovendas.dto.cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Endereco requisição DTO")
public class EnderecoRequestDTO {

	@ApiModelProperty(value="Logradouro")
	@NotBlank(message="Logradouro")
	@Length(min=3,max=50, message="Logradouro")
	private String logradouro;

	@ApiModelProperty(value="Número")
	@NotNull(message="Número")
	private Integer numero;

	@ApiModelProperty(value="Complemento")
	@Length(max=30, message="Complemento")
	private String complemento;

	@ApiModelProperty(value="Bairro")
	@NotBlank(message="Bairro")
	@Length(min=3,max=50, message="Bairro")
	private String bairro;

	@ApiModelProperty(value="CEP")
	@NotBlank(message="CEP")
	@Pattern(regexp="[\\d]{5}-[\\d]{3}", message ="CEP")
	private String cep;

	@ApiModelProperty(value="Cidade")
	@NotBlank(message="Cidade")
	@Length(min=3,max=50, message="Cidade")
	private String cidade;

	@ApiModelProperty(value="Estado")
	@NotBlank(message="Estado")
	@Length(min=3,max=50, message="Estado")
	private String estado;
	
	
	
	public EnderecoRequestDTO() {
	}

	public EnderecoRequestDTO(String logradouro, Integer numero, String complemento, String bairro, String cep,
			String cidade, String estado) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	
}
