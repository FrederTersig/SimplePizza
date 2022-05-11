package com.advancia.stage.dto;


import java.util.List;

import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Ingrediente;
import com.advancia.stage.model.Utente;

public class PizzaDTO {
	//Prendo in argomento id utente
	//ritorno lista pizze
	
	int pizzaId;
	String nome;

	//aggiunta
	List<IngredienteDTO> ingredienti;
	
	//Testing Criteria
	UtenteDTO utente;
	ImpastoDTO impasto;
	
	public PizzaDTO() {
		
	}
	
	public PizzaDTO(int id) {
		this.pizzaId = id;
	}
	
	
	public PizzaDTO(int pizzaId, String nome, UtenteDTO utente, ImpastoDTO impasto, List<IngredienteDTO> ingredienti) {
		this.utente = utente;
		this.pizzaId = pizzaId;
		this.nome = nome;
		this.impasto = impasto;
		this.ingredienti = ingredienti;
	}
	

	
	//FINE TESTING CRITERIA
	
	public PizzaDTO(int pizzaId, UtenteDTO utente, String nome) {
		this.utente = utente;
		this.pizzaId = pizzaId;
		this.nome = nome;
	}

	public PizzaDTO(UtenteDTO utente, int pizzaId, String nome, List<IngredienteDTO> ingredienti) {
		this.utente = utente;
		this.pizzaId = pizzaId;
		this.nome = nome;
		this.ingredienti = ingredienti;
	}
	
	public ImpastoDTO getImpasto() {
		return impasto;
	}
	
	public UtenteDTO getUtente() {
		return utente;
	}
	public String getNome() {
		return nome;
	}
	public int getPizzaId() {
		return pizzaId;
	}

	//aggiunta
	public List<IngredienteDTO> getIngredienti(){
		return ingredienti;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setId(int id) {
		this.pizzaId = id;
	}
	public void setImpasto(ImpastoDTO impasto) {
		this.impasto = impasto;
	}
	public void setIngredienti(List<IngredienteDTO> list) {
		this.ingredienti = list;
	}
	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}
	
}
