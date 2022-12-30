package com.example.crudspringjava;

import com.example.crudspringjava.model.Curso;
import com.example.crudspringjava.repository.CursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringJavaApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase(CursoRepository cursoRepository) {
		return args -> {
			cursoRepository.deleteAll();

			Curso curso = new Curso();
			curso.setNome("Angular");
			curso.setCategoria("Front");
			cursoRepository.save(curso);
		};
	}
}
