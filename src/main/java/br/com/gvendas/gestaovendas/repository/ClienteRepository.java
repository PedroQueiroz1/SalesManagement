package br.com.gvendas.gestaovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gvendas.gestaovendas.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

	Cliente findByNome(String nome);
	
}
