package br.com.gvendas.gestaovendas.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import br.com.gvendas.gestaovendas.dto.cliente.ClienteRequestDTO;
import br.com.gvendas.gestaovendas.dto.cliente.ClienteResponseDTO;
import br.com.gvendas.gestaovendas.entity.Cliente;
import br.com.gvendas.gestaovendas.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@ApiOperation(value="Listar",nickname="listarTodos")
	@GetMapping
	public List<ClienteResponseDTO> listarTodos(){
		return clienteService.listarTodos().stream()
				.map(n->ClienteResponseDTO.cParaCRDTO(n))
				.collect(Collectors.toList());
	}
	
	@ApiOperation(value="Buscar",nickname="listarTodos")
	@GetMapping("/{codigo}")
	public ResponseEntity<ClienteResponseDTO> buscarPorCodigo(@PathVariable Long codigo) {
		Optional<Cliente> cliente = clienteService.buscarPorCodigo(codigo);
		return cliente.isPresent()?ResponseEntity.ok(ClienteResponseDTO
				.cParaCRDTO(cliente.get()))
				:ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ApiOperation(value="Salvar",nickname="salvar")
	@PostMapping
	public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO cliente){
		Cliente est =clienteService.salvar(cliente.cParaRequestDTO());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ClienteResponseDTO.cParaCRDTO(est));
	}
	
	@ApiOperation(value="Atualizar",nickname="atualizar")
	@PutMapping("/{codigo}")
	public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable 
			Long codigo, @Valid @RequestBody ClienteRequestDTO cliente ){
		Cliente clienteVar = clienteService.atualizar(codigo, cliente.cParaRequestDTO(codigo));
		return ResponseEntity.ok(ClienteResponseDTO.cParaCRDTO(clienteVar));
	}
	
	@ApiOperation(value="Deletar",nickname="deletar")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigo) {
		clienteService.deletar(codigo);
		
	}
	
}