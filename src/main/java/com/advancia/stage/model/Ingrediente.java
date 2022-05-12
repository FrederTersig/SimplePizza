package com.advancia.stage.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;





@XmlRootElement
@Entity
@Table(name = "ingrediente")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nome")
	private String nome;
	

	@ManyToMany(mappedBy ="ingrediente")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Pizza> pizza;
	
	public Ingrediente() {
		
	}
	public Ingrediente(int id, String nome, List<Pizza> pizza) {
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
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public  List<Pizza> getPizze() {
		return pizza;
	}

	public void setPizze(List<Pizza> pizza) {
		this.pizza = pizza;
	}
	
//	@Override
//	public String toString() {
//		return nome;
//	}
}
