package br.com.gvendas.gestaovendas.dto.cliente;

import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.gvendas.gestaovendas.entity.Cliente;
import br.com.gvendas.gestaovendas.entity.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Cliente requisição DTO")
public class ClienteRequestDTO {

	@ApiModelProperty(value="Nome")
	@NotBlank(message="Nome")
	@Length(min=3,max=50, message="Nome")
	private String nome;

	@NotBlank(message="Telefone")
	@ApiModelProperty(value="Telefone")
	@Pattern(regexp="\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}", message ="Telefone")
	private String telefone;

	@ApiModelProperty(value="Ativo")
	@NotNull(message="Ativo")
	private Boolean ativo;

	@Embedded
	@ApiModelProperty(value="Endereço")
	@Valid
	private EnderecoRequestDTO enderecoDTO;


	public Cliente cParaRequestDTO(){
		Endereco endereco = new Endereco(enderecoDTO.getLogradouro(),
				enderecoDTO.getNumero(),
				enderecoDTO.getComplemento(),
				enderecoDTO.getBairro(),
				enderecoDTO.getCep(),
				enderecoDTO.getCidade(),
				enderecoDTO.getEstado());
		
		return new Cliente(nome,telefone,ativo,endereco);
	}

	public Cliente cParaRequestDTO(Long codigo) {
		Endereco endereco = new Endereco(enderecoDTO.getLogradouro(),
				enderecoDTO.getNumero(),
				enderecoDTO.getComplemento(),
				enderecoDTO.getBairro(),
				enderecoDTO.getCep(),
				enderecoDTO.getCidade(),
				enderecoDTO.getEstado());
		return new Cliente(codigo,nome,telefone,ativo,endereco);
	}
	
	
	public ClienteRequestDTO(String nome,String telefone, Boolean ativo,
			EnderecoRequestDTO enderecoDTO) {
		this.nome = nome;
		this.telefone = telefone;
		this.ativo = ativo;
		this.enderecoDTO = enderecoDTO;
	}





	public ClienteRequestDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public EnderecoRequestDTO getEnderecoDTO() {
		return enderecoDTO;
	}

	public void setEnderecoDTO(EnderecoRequestDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}
	
	public Boolean getAtivo() {
		return ativo;
	}





}
