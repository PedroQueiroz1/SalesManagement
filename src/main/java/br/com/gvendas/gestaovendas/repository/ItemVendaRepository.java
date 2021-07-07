package br.com.gvendas.gestaovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gvendas.gestaovendas.entity.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{
	
	@Query("select new br.com.gvendas.gestaovendas.entity.ItemVenda("
			+ "iv.codigo,iv.quantidade,iv.precoVendido,iv.produto,iv.venda)"
			+ " from ItemVenda iv"
			+ " where iv.venda.codigo = :codigoVenda")
	List<ItemVenda> findByVendaCodigo(Long codigoVenda);
}
