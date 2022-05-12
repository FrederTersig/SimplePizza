package com.advancia.stage.dto;

import java.util.List;

import com.advancia.stage.model.Pizza;

public class ImpastoDTO {
	
	int id;
	String nome;
	List<PizzaDTO> pizza;
	
	
	public ImpastoDTO() {}
	
	public ImpastoDTO(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public ImpastoDTO(int id, String nome, List<PizzaDTO> pizza) {
		this.id = id;
		this.nome = nome;
		this.pizza = pizza;
	}
	
	public String getId() {
		return Integer.toString(id);
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
	
	public List<PizzaDTO> getPizza(){
		return pizza;
	}
	
	public void setPizza(List<PizzaDTO> pizza) {
		this.pizza = pizza;
	}

}
