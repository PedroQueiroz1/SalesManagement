package br.com.gvendas.gestaovendas.dto.venda;

import java.time.LocalDate;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Venda retorno DTO")
public class VendaResponseDTO {
	
	@ApiModelProperty(value = "Código")
	private Long codigo;
	
	@ApiModelProperty(value = "Data")
	private LocalDate data;
	
	@ApiModelProperty(value = "Itens da venda")
	private List<ItemVendaResponseDTO> itemVendaDTO;

	public VendaResponseDTO(Long codigo, LocalDate data, List<ItemVendaResponseDTO> itemVendaDTO) {
		this.codigo = codigo;
		this.data = data;
		this.itemVendaDTO = itemVendaDTO;
	}

	public VendaResponseDTO() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<ItemVendaResponseDTO> getItemVendaDTO() {
		return itemVendaDTO;
	}

	public void setItemVendaDTO(List<ItemVendaResponseDTO> itemVendaDTO) {
		this.itemVendaDTO = itemVendaDTO;
	}
	
	
	
}
