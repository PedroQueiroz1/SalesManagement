package br.com.gvendas.gestaovendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"br.com.gvendas.gestaovendas.entity"})
@EnableJpaRepositories(basePackages = {"br.com.gvendas.gestaovendas.repository"})
@ComponentScan(basePackages = {"br.com.gvendas.gestaovendas.service",
		"br.com.gvendas.gestaovendas.controller",
		"br.com.gvendas.gestaovendas.exception"})
@SpringBootApplication
public class GestaovendasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaovendasApplication.class, args);
	}

}