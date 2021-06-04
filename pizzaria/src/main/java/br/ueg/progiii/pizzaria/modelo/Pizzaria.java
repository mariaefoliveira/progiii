package br.ueg.progiii.pizzaria.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Pizzaria")

@Table(
		name = "Pizzaria",
		uniqueConstraints = {
				@UniqueConstraint(name="uk_sabor_pizza_unico", columnNames = "sabor")
							}
	  )

public @Data class Pizzaria {
	
	//#Cardapio ou Lista de Pedidos?
	
	@Id
	@SequenceGenerator(name="pizzaria_sequence", sequenceName = "pizzaria_sequence", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pizzaria_sequence")
	private Long id;
	
	@Column(name = "sabor", nullable = false, length = 40, unique = true)	
	private String sabor;
	
	@Column(name = "preco", nullable = false)
	private Double preco;
	
	@Column(name = "bordaRecheada", nullable = false)
	private Boolean bordaRecheada;
	
}
