package br.ueg.progiii.pizzaria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ueg.progiii.pizzaria.modelo.Pizzaria;
import br.ueg.progiii.pizzaria.repository.PizzariaRepository;

@SpringBootApplication
public class PizzariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzariaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(PizzariaRepository pizzariaRepository ) {
		return args -> {
			Pizzaria pizza = new Pizzaria(null, "Peperonni", 50.00, true);
			pizzariaRepository.save(pizza);
		};
	}
}
