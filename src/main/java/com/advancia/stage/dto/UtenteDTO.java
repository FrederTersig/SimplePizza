package com.advancia.stage.dto;

import java.util.List;

import com.advancia.stage.model.Pizza;

public class UtenteDTO {

	//Prendo in argomento Username e Password
	//Ritorno info Utente (pizze che ha creato, impasti e ingredienti usati per le pizze)
	
	int utenteId;
	String username;
	String password;
	List<PizzaDTO> pizza;
	
	public UtenteDTO() {}
	
	public UtenteDTO(int utenteId, String username, String password) {
		this.utenteId = utenteId;
		this.username = username;
		this.password = password;
	}
	public UtenteDTO(int utenteId, String username, String password, List<PizzaDTO> pizza) {
		this.utenteId = utenteId;
		this.username = username;
		this.password = password;
		this.pizza = pizza;
	}
	
	public int getId() {
		return utenteId;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public List<PizzaDTO> getPizza() {
		return pizza;
	}
	
	public void setId(int id) {
		this.utenteId = id;
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setPizza(List<PizzaDTO> pizza) {
		this.pizza = pizza;
	}
	
}
