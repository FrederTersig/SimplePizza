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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@XmlRootElement
@Entity
@Table(name = "pizza")
public class Pizza {


	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nome")
	private String nome;
	
	//Se imposto "LAZY", l'impasto non viene più caricato (e SOLO l'impasto)
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utentePizza;

	@ManyToOne
	@JoinColumn(name = "impasto_id")
	private Impasto impasto;

	@ManyToMany(targetEntity = Ingrediente.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "pizza_ingrediente",
	 joinColumns = {@JoinColumn(name = "pizza_id", referencedColumnName="id")},
	 inverseJoinColumns = {@JoinColumn(name =
	 "ingrediente_id", referencedColumnName="id")} )
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Ingrediente> ingrediente;

	public Pizza() {
	}

	public Pizza(int id, String nome, Utente utente, Impasto impasto, List<Ingrediente> ingrediente) {
		this.id = id;
		this.nome = nome;
		this.utentePizza = utente;
		this.impasto = impasto;
		this.ingrediente = ingrediente;
	}

	public String getId() {
		return Integer.toString(id);
	}

	public String getNome() {
		return nome;
	}

	public Utente getUtente() {
		return utentePizza;
	}

	public Impasto getImpasto() {
		return impasto;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setUtente(Utente utente) {
		this.utentePizza = utente;
	}

	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}

	public List<Ingrediente> getIngredienti() {
		return ingrediente;
	}

//	public String getNomeIngredienti(){
//		String lista = "";
//		for(int i=0; i<ingrediente.size(); i++) {
//			lista += ingrediente.get(i).getNome() + " ";
//		}
//		
//		return lista;
//	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingrediente = ingredienti;
	}

//	@Override
//	public String toString() {
//		return nome;
//	}

}
