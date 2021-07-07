package br.com.gvendas.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gvendas.gestaovendas.entity.Categoria;
import br.com.gvendas.gestaovendas.exception.RegraNegocioException;
import br.com.gvendas.gestaovendas.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarTodas(){
		return categoriaRepository.findAll();
	}
	
	public Optional<Categoria> buscarPorCodigo(Long codigo){
		return categoriaRepository.findById(codigo);
	}
	
	public Categoria salvar(Categoria categoria) {
		validarCategoriaDuplicada(categoria);
		return categoriaRepository.save(categoria);
	}

	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria cSalvar = validarSeExiste(codigo);
		validarCategoriaDuplicada(categoria);
		BeanUtils.copyProperties(categoria,cSalvar,"codigo");
		return categoriaRepository.save(cSalvar);
	}

	private Categoria validarSeExiste(Long codigo) {
		Optional<Categoria> est = buscarPorCodigo(codigo);
		if(est.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return est.get();
	}

	public void deletar(Long codigo) {
		categoriaRepository.deleteById(codigo);
	}
	
	private void validarCategoriaDuplicada(Categoria categoria) {
		Categoria categoriaEncontrada = categoriaRepository.findByNome(categoria.getNome());
		if(categoriaEncontrada!=null && categoriaEncontrada.getCodigo() != categoria.getCodigo()) {
		throw new RegraNegocioException(String.format("A categoria %s já esta cadastrada",
				categoria.getNome().toUpperCase()));
		}
	}
}
