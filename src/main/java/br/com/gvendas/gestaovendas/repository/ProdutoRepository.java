package br.com.gvendas.gestaovendas.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gvendas.gestaovendas.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByCategoriaCodigo(Long codigoCategoria);
	
	Optional<Produto> findByCodigoAndCategoriaCodigo(
			Long codigo, Long codigoCategoria);
	
	Optional<Produto> findByCategoriaCodigoAndDescricao(
			Long codigoCategoria, String descricao);
	
	Optional<Produto> findByCategoriaCodigoAndDescricaoAndQuantidadeAndPrecoCustoAndPrecoVendaAndObservacao(
			Long codigoCategoria, String descricao, Integer quantidade, BigDecimal precoCusto, BigDecimal precoVenda, String observacao);
}


