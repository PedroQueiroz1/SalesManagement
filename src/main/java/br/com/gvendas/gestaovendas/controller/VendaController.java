package br.com.gvendas.gestaovendas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gvendas.gestaovendas.dto.venda.ClienteVendaResponseDTO;
import br.com.gvendas.gestaovendas.dto.venda.VendaRequestDTO;
import br.com.gvendas.gestaovendas.service.VendaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags="Venda")
@RestController
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	VendaService vendaService;
	
	@ApiOperation(value = "Listar vendas por cliente", nickname="listarVendaPorCliente")
	@GetMapping("/cliente/{codigo}")
	public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente(@PathVariable Long codigo){
		return ResponseEntity.ok(vendaService.listarVendaPorCliente(codigo));
	}
	
	@ApiOperation(value="Listar vendas por codigo", nickname="listarVendasPorCodigo")
	@GetMapping("/{codigoVenda}")
	public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(@PathVariable Long codigoVenda){
		return ResponseEntity.ok(vendaService.listarVendaPorCodigo(codigoVenda));
	}
	
	@ApiOperation(value="Salvar", nickname="salvar")
	@PostMapping("/cliente/{codigoCliente}")
	public ResponseEntity<ClienteVendaResponseDTO> salvar(@PathVariable Long codigoCliente,
			@Valid @RequestBody VendaRequestDTO vendaRDTO){
		ClienteVendaResponseDTO cliente = vendaService.salvar(codigoCliente, vendaRDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
	}
	
	@ApiOperation(value="Atualizar", nickname="atualizar")
	@PutMapping("/{codigoVenda}/cliente/{codigoCliente}")
	public ResponseEntity<ClienteVendaResponseDTO> atualizar(@PathVariable Long codigoVenda, 
			@PathVariable Long codigoCliente,
			@Valid @RequestBody VendaRequestDTO vendaRDTO){
		return ResponseEntity.ok(vendaService.atualizar(codigoVenda, codigoCliente, vendaRDTO));
	}
	
	@ApiOperation(value="Deletar", nickname="deletar")
	@DeleteMapping("/{codigoVenda}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigoVenda) {
		vendaService.deletarVenda(codigoVenda);
	}
}
