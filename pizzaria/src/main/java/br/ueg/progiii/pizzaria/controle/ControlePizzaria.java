package br.ueg.progiii.pizzaria.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.progiii.pizzaria.modelo.Pizzaria;
import br.ueg.progiii.pizzaria.service.PizzariaService;

@RestController
@RequestMapping(path = "api/v1/pizzaria")
public class ControlePizzaria {
	
	@Autowired
	private PizzariaService pizzariaService;
	
	@PostMapping(path="add")
	public Pizzaria adicionar(@RequestBody Pizzaria pizza) {
		return pizzariaService.adicionar(pizza);
	}
	
	@GetMapping(path="id/{idPizza}")
	public Pizzaria buscaId(@PathVariable("idPizza") Long idPizza)
	{
		return pizzariaService.getPizza(idPizza);
	}
	
	@GetMapping(path="borda/{borda}")
	public List<Pizzaria> buscaBordaRecheio(@PathVariable("borda") Boolean borda) {
		
		return pizzariaService.obterPizzaBorda(borda);
	}
	
	@DeleteMapping(path = "id/{idPizza}")
	public Pizzaria remover(@PathVariable("idPizza") Long idPizza) {
		return pizzariaService.remover(idPizza);
	}
	
	@PatchMapping (path = "id/{idPizza}")
	public Pizzaria alterar(@RequestBody Pizzaria pizzaria, @PathVariable("idPizza") Long idPizza) {
		return pizzariaService.alterar(pizzaria, idPizza);
	}
	
	//@PostMapping
	@GetMapping
	public List<Pizzaria> listarTodos(){
		return pizzariaService.listarTodos();
	}
	
}
