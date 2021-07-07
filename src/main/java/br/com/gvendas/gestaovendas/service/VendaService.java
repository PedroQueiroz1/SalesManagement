package br.com.gvendas.gestaovendas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import br.com.gvendas.gestaovendas.dto.venda.ItemVendaRequestDTO;
import br.com.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import br.com.gvendas.gestaovendas.dto.venda.VendaResponseDTO;
import br.com.gvendas.gestaovendas.entity.Cliente;
import br.com.gvendas.gestaovendas.entity.ItemVenda;
import br.com.gvendas.gestaovendas.entity.Produto;
import br.com.gvendas.gestaovendas.entity.Venda;
import br.com.gvendas.gestaovendas.exception.RegraNegocioException;
import br.com.gvendas.gestaovendas.repository.ItemVendaRepository;
import br.com.gvendas.gestaovendas.repository.VendaRepository;

@Service
public class VendaService extends AbstractVendaService{

	private VendaRepository vendaRepository;

	private ClienteService clienteService;

	private ItemVendaRepository itemVendaRepository;	

	private ProdutoService produtoService;

	@Autowired
	public VendaService(VendaRepository vendaRepository, ClienteService clienteService,
			ItemVendaRepository itemVendaRepository, ProdutoService produtoService) {
		this.vendaRepository = vendaRepository;
		this.clienteService = clienteService;
		this.itemVendaRepository = itemVendaRepository;
		this.produtoService = produtoService;
	}


	public ClienteVendaResponseDTO listarVendaPorCliente(Long codigoCliente) {
		Cliente cliente = validarClienteVendaExiste(codigoCliente);
		List<VendaResponseDTO> lista = vendaRepository
				.findByClienteCodigo(codigoCliente).stream()
				.map(v->criandoVendaResponseDTO(v,itemVendaRepository
						.findByVendaCodigo(v.getCodigo()))).collect(Collectors.toList());
		return new ClienteVendaResponseDTO(cliente.getNome(),lista);
	}

	public ClienteVendaResponseDTO listarVendaPorCodigo(Long codigoVenda) {
		Venda vendaSalva = validarVendaExiste(codigoVenda);
		List<ItemVenda> itensVendaList = itemVendaRepository
				.findByVendaCodigo(vendaSalva.getCodigo());
		return retornandoClienteVendaResponseDTO(vendaSalva, itensVendaList);

		//		List<VendaResponseDTO> lista = vendaRepository.findById(codigoVenda)
		//				.stream()
		//				.map(n->criandoVendaResponseDTO(n)).collect(Collectors.toList());
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public ClienteVendaResponseDTO salvar(Long codigoCliente, VendaRequestDTO vendaRDTO) {
		Cliente cliente = validarClienteVendaExiste(codigoCliente);
		validarProdutoExisteEAtualizarQuantidade(vendaRDTO.getItemVendaDTO());
		Venda vendaSalva = salvarVenda(cliente, vendaRDTO);
		return retornandoClienteVendaResponseDTO(vendaSalva, itemVendaRepository
				.findByVendaCodigo(vendaSalva.getCodigo()));
	}


	private Venda salvarVenda(Cliente cliente, VendaRequestDTO vendaRDTO) {
		Venda vendaSalva = vendaRepository.save(new Venda(vendaRDTO.getData(),cliente));
		vendaRDTO.getItemVendaDTO().stream().map(n->criandoItemVenda(n, vendaSalva))
		.forEach(itemVendaRepository::save);
		return vendaSalva;
	}
	
	private Venda atualizarVenda(Long codigoVenda, Cliente cliente, VendaRequestDTO vendaRDTO) {
		Venda vendaSalva = vendaRepository.save(new Venda(vendaRDTO.getData(),cliente));
		vendaRDTO.getItemVendaDTO().stream().map(n->criandoItemVenda(n, vendaSalva))
		.forEach(itemVendaRepository::save);
		return vendaSalva;
	}
	
	public ClienteVendaResponseDTO atualizar(Long codigoVenda, Long codigoCliente, VendaRequestDTO vendaDTO) {
		validarVendaExiste(codigoVenda);
		Cliente cliente = validarClienteVendaExiste(codigoCliente);
		List<ItemVenda> itensVendaList = itemVendaRepository.findByVendaCodigo(codigoVenda);
		validarProdutoExisteEDevolverEstoque(itensVendaList);
		validarProdutoExisteEAtualizarQuantidade(vendaDTO.getItemVendaDTO());
		itemVendaRepository.deleteAll(itensVendaList);
		Venda vendaAtualizada = atualizarVenda(codigoVenda, cliente, vendaDTO);
		return retornandoClienteVendaResponseDTO(vendaAtualizada, itemVendaRepository
				.findByVendaCodigo(vendaAtualizada.getCodigo()));
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	public void deletarVenda(Long codigoVenda) {
		validarVendaExiste(codigoVenda);
		List<ItemVenda> itensVenda = itemVendaRepository.findByVendaCodigo(codigoVenda);
		validarProdutoExisteEDevolverEstoque(itensVenda);
		itemVendaRepository.deleteAll(itensVenda);
		vendaRepository.deleteById(codigoVenda);
	}
	
	private void validarProdutoExisteEAtualizarQuantidade(List<ItemVendaRequestDTO> itensVendaDTO) {
		itensVendaDTO.forEach(n-> {
		Produto produto =produtoService.validarProdutoExiste(n.getCodigoProduto());
		validarQuantidadeProdutoExiste(produto, n.getQuantidade());
		produto.setQuantidade(produto.getQuantidade()-n.getQuantidade());
		produtoService.atualizarQuantidadeAposVenda(produto);
		});
	}

	private void validarQuantidadeProdutoExiste(Produto produto, Integer qtdeVendaDTO) {
		if((produto.getQuantidade()<qtdeVendaDTO)){
		throw new RegraNegocioException("Não existe a quantidade que você deseja vender");
		}
	}
	
	private void validarProdutoExisteEDevolverEstoque(List<ItemVenda> itensVenda) {
		itensVenda.forEach(item ->{
			Produto produto = produtoService.validarProdutoExiste(item.getProduto().getCodigo());
			produto.setQuantidade(produto.getQuantidade()+item.getQuantidade());
			produtoService.atualizarQuantidadeAposVenda(produto);
		});
		
	}
	
	private Cliente validarClienteVendaExiste(Long codigoCliente) {
		Optional<Cliente> cliente = clienteService.buscarPorCodigo(codigoCliente);
		if(cliente.isEmpty()) {
			throw new RegraNegocioException("Cliente não existe!");
		}
		return cliente.get();
	}

	private Venda validarVendaExiste(Long codigoVenda) {
		Optional<Venda> venda = vendaRepository.findById(codigoVenda);
		if(venda.isEmpty()) {
			throw new RegraNegocioException("Venda não existe!");
		}
		return venda.get();
	}


}