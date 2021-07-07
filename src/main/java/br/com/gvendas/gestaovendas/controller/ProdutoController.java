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

import br.com.gvendas.gestaovendas.dto.produto.ProdutoRequestDTO;
import br.com.gvendas.gestaovendas.dto.produto.ProdutoResponseDTO;
import br.com.gvendas.gestaovendas.entity.Produto;
import br.com.gvendas.gestaovendas.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="Produto")
@RestController
@RequestMapping("/categoria{codigoCategoria}/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	
	@ApiOperation(value="Listar",nickname="listarTodos")
	@GetMapping
	public List<ProdutoResponseDTO> listarTodos(@PathVariable Long codigoCategoria){
		return produtoService.listarTodos(codigoCategoria).stream()
				.map(n->ProdutoResponseDTO.cParaPDTO(n)).collect(Collectors.toList());
	}
	
	@ApiOperation(value="Buscar", nickname="buscarPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<ProdutoResponseDTO> buscarPorCodigo
			(@PathVariable Long codigoCategoria, @PathVariable Long codigo){
		Optional<Produto> est = produtoService.buscarPorCodigo(codigo,
				codigoCategoria);
		return est.isPresent()?ResponseEntity
				.ok(ProdutoResponseDTO.cParaPDTO(est.get())):ResponseEntity
				.notFound()
				.build();
	}
	
	@ApiOperation(value="Salvar", nickname="salvar produto")
	@PostMapping
	public ResponseEntity<ProdutoResponseDTO> salvar(@PathVariable Long codigoCategoria,
			@Valid @RequestBody ProdutoRequestDTO produto){
		Produto est =produtoService.salvar(codigoCategoria,produto.cParaPRequestDTO(codigoCategoria)); 
	   	return ResponseEntity.status(HttpStatus.CREATED)
	   			.body(ProdutoResponseDTO.cParaPDTO(est));
	}
	
	@ApiOperation(value="Atualizar", nickname="atualizar produto")
	@PutMapping("/{codigo}")
	public ResponseEntity<ProdutoResponseDTO> atualizar(@Valid 
			@RequestBody ProdutoRequestDTO produto, @PathVariable Long codigoCategoria,
			@PathVariable Long codigo){
		Produto est = produtoService.atualizar(codigo, codigoCategoria,produto.cParaPRequestDTO(codigo,codigoCategoria));
		return ResponseEntity.ok(ProdutoResponseDTO.cParaPDTO(est));
	}
	
	@ApiOperation(value="Deletar",nickname="deletar produto")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigoCategoria,@PathVariable Long codigo) {
	produtoService.deletar(codigo, codigoCategoria);
	}
	
//	@ApiOperation(value="Atualizar", nickname="atualizar produto")
//	@PutMapping("/{codigo}")
//	public ResponseEntity<Produto> atualizar(@PathVariable Long codigoCategoria,
//			@PathVariable Long codigo,
//			@RequestBody Produto produto){
//		return ResponseEntity.ok(produtoService.atualizar(
//				codigo, codigoCategoria, produto));
//}
}
/*
	@ApiOperation(value = "Salvar",nickname="salvar")
	@PostMapping
	public ResponseEntity<Produto> salvar(Produto produto) {
		Produto est = produtoService.salvar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(est);
	}
	
	@ApiOperation(value = "Atualizar",nickname="atualizar")
	@PutMapping("/{codigo}")
	public ResponseEntity<Produto> atualizar(@PathVariable Long codigo,
			@Valid @RequestBody Produto produto){
		Produto est = produtoService.atualizar(codigo, produto);
		return ResponseEntity.ok(est);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{codigo}")
	public void deletar(Long codigo) {
		produtoService.deletar(codigo);
	}
	
}*/