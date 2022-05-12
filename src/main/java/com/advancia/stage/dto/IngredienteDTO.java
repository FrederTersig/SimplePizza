package com.advancia.stage.dto;

import java.util.List;

public class IngredienteDTO {
	
	int id;
	String nome;
	List<PizzaDTO> pizza;
	
	public IngredienteDTO() {
		
	}
	
	public IngredienteDTO(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public IngredienteDTO(int id, String nome, List<PizzaDTO> pizza) {
		this.id = id;
		this.nome = nome;
		this.pizza = pizza;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPizza(List<PizzaDTO> pizza) {
		this.pizza = pizza;
	}
	
	public List<PizzaDTO> getPizza() {
		return pizza;
	}
	
}
