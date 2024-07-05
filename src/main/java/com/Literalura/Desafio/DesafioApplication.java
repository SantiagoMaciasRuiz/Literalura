package com.Literalura.Desafio;

import com.Literalura.Desafio.principal.Principal;
import com.Literalura.Desafio.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioApplication implements CommandLineRunner {

	private final Principal principal;
	@Autowired
	public DesafioApplication(Principal principal) {
		this.principal = principal;
	}
	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
	public void run(String... args) throws Exception {
		principal.mostrarMenu();


	}
}
