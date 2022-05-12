package com.advancia.stage.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@XmlRootElement

@Entity
@Table(name = "utente")
public class Utente implements Serializable {

    private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy="utentePizza", fetch = FetchType.LAZY)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<Pizza> pizzaUtente;
	
	//Getter && Setter

	public Utente() {}
	
	
	public Utente(int id, String username, String password, List<Pizza> pizza) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.pizzaUtente = pizza;
	}
	public String getId() {
		return Integer.toString(id);
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Pizza> getPizza() {
		return pizzaUtente;
	}
	public void setPizza(List<Pizza> pizze) {
		this.pizzaUtente = pizze;
	}
	
//	@Override
//	public String toString() {
//		return id+", "+username+", "+ password+ ", " + pizze;
//	}
	
}
