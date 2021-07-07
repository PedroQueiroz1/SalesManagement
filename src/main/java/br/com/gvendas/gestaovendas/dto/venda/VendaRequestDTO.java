package br.com.gvendas.gestaovendas.dto.venda;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Venda requisição DTO")
public class VendaRequestDTO {
	
	@ApiModelProperty(value="Data")
	@NotNull(message= "Data")
	private LocalDate data;
	
	@ApiModelProperty(value="Itens da Venda")
	@NotNull(message="Itens Venda")
	@Valid
	private List<ItemVendaRequestDTO> itemVendaDTO;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<ItemVendaRequestDTO> getItemVendaDTO() {
		return itemVendaDTO;
	}

	public void setItemVendaDTO(List<ItemVendaRequestDTO> itemVendaDTO) {
		this.itemVendaDTO = itemVendaDTO;
	}
	
	
	
}
