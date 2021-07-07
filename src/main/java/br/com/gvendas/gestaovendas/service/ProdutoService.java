package br.com.gvendas.gestaovendas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gvendas.gestaovendas.entity.Produto;
import br.com.gvendas.gestaovendas.exception.RegraNegocioException;
import br.com.gvendas.gestaovendas.repository.ProdutoRepository;

// listarTodos
// buscarPorCodigo
// salvar

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaService categoriaService;
	
	public List<Produto> listarTodos(Long codigoCategoria){
		return produtoRepository.findByCategoriaCodigo(codigoCategoria);
	}
	
	public Optional<Produto> buscarPorCodigo(Long codigo, Long codigoCategoria) {
		return produtoRepository.findByCodigoAndCategoriaCodigo(codigo, codigoCategoria);
	}
	
	public Produto salvar(Long codigoCategoria,Produto produto) {
		validarCategoriaDoProdutoExiste(codigoCategoria);
		validarProdutoDuplicado(produto);
		return produtoRepository.save(produto);
	}

	public Produto atualizar(Long codigo, Long codigoCategoria, Produto produto) {
		Produto cProduto = validarProdutoExiste(codigo, codigoCategoria);
		validarProdutoDuplicado(produto);
		BeanUtils.copyProperties(produto, cProduto,"codigo");
		return produtoRepository.save(cProduto);
	}
	
	public void deletar(Long codigo,Long codigoCategoria) {
		Produto p = validarProdutoExiste(codigo,codigoCategoria);
		produtoRepository.delete(p);
	}
	
	private Produto validarProdutoExiste(Long codigo, Long codigoCategoria) {
		Optional<Produto> produto = produtoRepository.findByCodigoAndCategoriaCodigo(codigo, codigoCategoria);
		if(produto.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return produto.get();
	}
	
	protected Produto validarProdutoExiste(Long codigoProduto) {
		Optional<Produto> produto = produtoRepository.findById(codigoProduto);
		if(produto.isEmpty()) {
			throw new RegraNegocioException("Produto não encontrado!");
		}
		 return produto.get();
	}

	private void validarProdutoDuplicado(Produto produto) {
			Optional<Produto> est =produtoRepository.findByCategoriaCodigoAndDescricao(
					produto.getCategoria().getCodigo(),produto.getDescricao());
		if(est.isPresent()&&est.get().getCodigo()!=produto.getCodigo()){
			throw new RegraNegocioException("Produto já cadastrado!");
		}
	}
	
	private void validarCategoriaDoProdutoExiste(Long codigoCategoria) {
		if(codigoCategoria==null) {
			throw new RegraNegocioException("A categoria não pode ser nula");
		}
		if(categoriaService.buscarPorCodigo(codigoCategoria).isEmpty()) {
			throw new RegraNegocioException(String.format("A categoria de código %s "
					+ "não existe no cadastro", codigoCategoria));
		}
	}
	
	protected void atualizarQuantidadeAposVenda(Produto produto) {
		produtoRepository.save(produto);
	}
	/*	
	public Produto atualizar(Long codigo, Long codigoCategoria, Produto produto) {
		Produto pSalvar = checkIfExists(codigo, codigoCategoria, produto);
		validarProdutoDuplicado(codigoCategoria,produto);
		BeanUtils.copyProperties(produto,pSalvar,"codigo");
		return produtoRepository.save(pSalvar);
	}


	public void deletar(Long codigo) {
		produtoRepository.deleteById(codigo);
	}
	
	private Produto checkIfExists(Long codigo, Long codigoCategoria, Produto produto) {
		Optional<Produto> est = buscarPorCodigo(codigo, codigoCategoria);
		if(est.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		return est.get();
	}
*/
}