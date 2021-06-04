package br.ueg.progiii.pizzaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import br.ueg.progiii.pizzaria.modelo.Pizzaria;
import br.ueg.progiii.pizzaria.repository.PizzariaRepository;

@Service
public class PizzariaService {

	@Autowired
	private PizzariaRepository pizzariaRepository;
	
	public Pizzaria adicionar(Pizzaria pizza) {
		
		Boolean existePizza = pizzariaRepository.exitePizza(pizza.getSabor());
		
		if(existePizza)//Caso seja um cardapio nao deve repetir o sabor dela TODO verificar
		{
			throw new IllegalStateException("Pizza de Sabor: "+pizza.getSabor()+" já está na lista");
		}
		Pizzaria pizzaRetorno = pizzariaRepository.save(pizza);
		
		return pizzaRetorno;	
	}
	
	public List<Pizzaria> listarTodos(){
		return pizzariaRepository.findAll();
	}
	
	public Pizzaria getPizza(Long idPizza) {
		Optional<Pizzaria> pizza= obterPizzaExistente(idPizza);
		return pizza.get();
	}
	
	public Optional<Pizzaria> obterPizzaExistente(Long idPizza) {
		Optional<Pizzaria> pizza= pizzariaRepository.findById(idPizza);
		if(!pizza.isPresent()) {
			throw new IllegalStateException("Não existe uma pizza com o ID: "+idPizza);
		}
		return pizza;
	}

	public List<Pizzaria> obterPizzaBorda(Boolean borda){
		List<Pizzaria> pizzas;
		if(borda==false)
			pizzas = pizzariaRepository.findByActiveFalse(borda);
		else
			pizzas = pizzariaRepository.findByActiveTrue(borda);
		
		if(CollectionUtils.isEmpty(pizzas))
		{
				throw new IllegalStateException("Não há pizzas com a borda especificada");	
		}
		
		return pizzas;
	}

	public Pizzaria remover(Long idPizza) {
		Optional<Pizzaria> pizzariaOptional = obterPizzaExistente(idPizza);
		Pizzaria pizza = pizzariaOptional.get();
		pizzariaRepository.delete(pizza);
		return pizza;
	}

	public Pizzaria alterar(Pizzaria pizzaria, Long idPizza) {
		Optional<Pizzaria> pizzariaOptional = obterPizzaExistente(idPizza);
		Pizzaria pizzaBD = pizzariaOptional.get();
		
		if(StringUtils.hasLength(pizzaria.getSabor())) {
			pizzaBD.setSabor(pizzaria.getSabor());
		}
		if(!(pizzaria.getPreco()==null)&&pizzaria.getPreco()>0) {
			pizzaBD.setPreco(pizzaria.getPreco());
		}else if(pizzaria.getPreco()==0) {
			throw new IllegalStateException("O valor da pizza deve ser maior que 0 caso deseje alterá-lo");
		}
		if(!(pizzaria.getBordaRecheada()==null))
		{
			pizzaBD.setBordaRecheada(pizzaria.getBordaRecheada());
		}
		
		pizzaBD = pizzariaRepository.save(pizzaBD);
		return pizzaBD;
	}
}
