package com.advancia.stage.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;

@XmlRootElement
@Entity
@Table(name = "impasto")
public class Impasto implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome")
	private String nome;
	

	@OneToMany(mappedBy="impasto")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Pizza> pizza;
	
	public Impasto() {
		
	}
	
	public Impasto(int id, String nome, List<Pizza> pizze) {
		this.id = id;
		this.nome = nome;
		this.pizza = pizze;
	}
	
	public String getId() {
		return Integer.toString(id);
	}
	
	public String getNome() {
		return nome;
	}
	
	
	public List<Pizza> getPizze() {
		return pizza;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPizze(List<Pizza> pizze) {
		this.pizza = pizze;
	}
	
	@Override
	public String toString() {
		return nome;
	}

}
