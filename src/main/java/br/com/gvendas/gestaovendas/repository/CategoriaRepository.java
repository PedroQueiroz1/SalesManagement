package br.com.gvendas.gestaovendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gvendas.gestaovendas.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Categoria findByNome(String nome);
	
}
