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

import br.com.gvendas.gestaovendas.dto.categoria.CategoriaRequestDTO;
import br.com.gvendas.gestaovendas.dto.categoria.CategoriaResponseDTO;
import br.com.gvendas.gestaovendas.entity.Categoria;
import br.com.gvendas.gestaovendas.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@ApiOperation(value = "Listar",nickname="listarTodas")
	@GetMapping
	public List<CategoriaResponseDTO> listarTodas(){

		return categoriaService.listarTodas().stream()
				.map(n->CategoriaResponseDTO.convertarParaCategoriaDTO(n))
				.collect(Collectors.toList());
	}

	@ApiOperation(value = "Buscar", nickname = "buscarPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<CategoriaResponseDTO> buscarPorCodigo(@PathVariable Long codigo) {
		Optional<Categoria> est = categoriaService.buscarPorCodigo(codigo);
		return est.isPresent() ? ResponseEntity
				.ok(CategoriaResponseDTO.convertarParaCategoriaDTO(est.get())) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salvar", nickname = "salvar")
	@PostMapping
	public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
		Categoria est = categoriaService.salvar(categoriaDTO.cParaCRequestDTO());
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.convertarParaCategoriaDTO(est));
	}

	@ApiOperation(value = "Atualizar", nickname = "atualizar")
	@PutMapping("/{codigo}")
	public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriaDTO) {
		Categoria est = categoriaService.atualizar(codigo,categoriaDTO.cParaCRequestDTO(codigo) );
		return ResponseEntity.ok(CategoriaResponseDTO.convertarParaCategoriaDTO(est));
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long codigo) {
		categoriaService.deletar(codigo);
	}

}
