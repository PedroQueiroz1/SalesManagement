package br.com.gvendas.gestaovendas.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import br.com.gvendas.gestaovendas.dto.venda.ItemVendaRequestDTO;
import br.com.gvendas.gestaovendas.dto.venda.ItemVendaResponseDTO;
import br.com.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import br.com.gvendas.gestaovendas.entity.ItemVenda;
import br.com.gvendas.gestaovendas.entity.Produto;
import br.com.gvendas.gestaovendas.entity.Venda;

public abstract class AbstractVendaService {
	
	protected VendaResponseDTO criandoVendaResponseDTO(Venda venda,List<ItemVenda> itensVendaList) {
		List<ItemVendaResponseDTO> listaDeVendasRDTO = itensVendaList.stream()
				.map(this::criandoItemVendaResponseDTO)
				.collect(Collectors.toList());
		return new VendaResponseDTO(venda.getCodigo(),venda.getData(),listaDeVendasRDTO);
	}

	protected ItemVendaResponseDTO criandoItemVendaResponseDTO(ItemVenda itemVenda) {
		return new ItemVendaResponseDTO(itemVenda.getCodigo(),itemVenda.getQuantidade(),
				itemVenda.getPrecoVendido(),itemVenda.getProduto().getCodigo(),
				itemVenda.getProduto().getDescricao());
	}
	
	protected ClienteVendaResponseDTO retornandoClienteVendaResponseDTO(Venda venda,
			List<ItemVenda> itensVendaList) {
		return new ClienteVendaResponseDTO(venda.getCliente().getNome(),
			Arrays.asList(criandoVendaResponseDTO(venda, itensVendaList)));
	}

	protected ItemVenda criandoItemVenda(ItemVendaRequestDTO itemVendaRDTO,Venda venda) {
		Produto produto = new Produto(itemVendaRDTO.getCodigoProduto());
		return new ItemVenda(itemVendaRDTO.getQuantidade(),
				itemVendaRDTO.getPrecoVendido(),produto,venda);
	}

	
}
