package br.ueg.progiii.pizzaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ueg.progiii.pizzaria.modelo.Pizzaria;

@Repository
public interface PizzariaRepository extends JpaRepository<Pizzaria, Long> {
	List<Pizzaria> findBySabor(String sabor);
	
	@Query(""+
	"SELECT CASE WHEN COUNT(p) > 0 THEN "+
	"TRUE ELSE FALSE END " + 
	"FROM Pizzaria p "+
	"WHERE lower(p.sabor)=lower(?1)" //com a adicao de lower ele vai encontrar palavras escritas com letras minusculas tambem
	)
	Boolean exitePizza(String sabor); //Precisa do banco de dados para confirmar
	
	@Query("SELECT p FROM Pizzaria p WHERE p.bordaRecheada = true") //funcionando
	List<Pizzaria> findByActiveTrue(Boolean borda);
	@Query("SELECT p FROM Pizzaria p WHERE p.bordaRecheada = false")//funcionando :)
	List<Pizzaria> findByActiveFalse(Boolean borda);

}
