package br.com.gvendas.gestaovendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gvendas.gestaovendas.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{

	
	List<Venda> findByClienteCodigo(Long codigoCliente);
	
	List<Venda> findByCodigo(Long codigoVenda);
}
