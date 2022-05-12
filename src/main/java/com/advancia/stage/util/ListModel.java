package com.advancia.stage.util;

import java.util.ArrayList;
import java.util.List;

import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.dto.IngredienteDTO;
import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.dto.UtenteDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Ingrediente;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.model.Utente;

//public class Utility<E, F> {
public class ListModel{

	public static List<UtenteDTO> transformToUtenteDTO(List<Utente> listaUtente){
		List<UtenteDTO> listDTO = new ArrayList<UtenteDTO>();
		listaUtente.forEach(utente -> {
			UtenteDTO x = new UtenteDTO(); // creo utente per ogni utente di res
			x.setId(Integer.parseInt(utente.getId()));
			x.setUsername(utente.getUsername());
			x.setPassword(utente.getPassword());
			
			List<PizzaDTO> p = new ArrayList<PizzaDTO>();
			p = Manage.convertPizzaDTOList(utente.getPizza(), "utente");
    		x.setPizza(p);
    		listDTO.add(x); // Aggiungo x

    	});
		
		return listDTO;
	}
	
	public static List<PizzaDTO> transformToPizzaDTO(List<Pizza> listaPizza){
		List<PizzaDTO> listDTO = new ArrayList<PizzaDTO>();
		listDTO = Manage.convertPizzaDTOList(listaPizza,"");
		
//		//Una pizza ha un solo utente, un solo impasto, N ingredienti
//		listaPizza.forEach(pizza ->{
//				PizzaDTO x = new PizzaDTO();
//				// Setto i campi della pizza
//				x.setId(Integer.parseInt(pizza.getId())); // SET ID
//				x.setNome(pizza.getNome()); // SET NOME
//
//				// Setto ImpastoDTO
//				ImpastoDTO y = new ImpastoDTO();
//    			y.setNome(pizza.getImpasto().getNome());
//    			y.setId(Integer.parseInt(pizza.getImpasto().getId()));
//    			x.setImpasto(y); // SET IMPASTO
//    			
//    			// Setto UtenteDTO
//				UtenteDTO z = new UtenteDTO();
//				z.setUsername(pizza.getUtente().getUsername());
//				z.setPassword(pizza.getUtente().getPassword());
//				z.setId(Integer.parseInt(pizza.getUtente().getId()));
//				x.setUtente(z); // SET UTENTE 
//				
//				
//				//setto Ingrediente DTO
//				List<IngredienteDTO> u = new ArrayList<IngredienteDTO>();
//    			pizza.getIngredienti().forEach(ingrediente -> {
//    				IngredienteDTO v = new IngredienteDTO();
//    				v.setId(Integer.parseInt(ingrediente.getId()));
//    				v.setNome(ingrediente.getNome());
//    				u.add(v);
//    			});
//				
//				x.setIngredienti(u); // SET LISTA INGREDIENTI
//				
//				
//				listDTO.add(x);
//		});
//		
		
		
		
		return listDTO;
	}
	
	public static List<ImpastoDTO> transformToImpastoDTO(List<Impasto> listaImpasto){
		List<ImpastoDTO> listDTO = new ArrayList<ImpastoDTO>();
		listaImpasto.forEach(impasto ->{
			ImpastoDTO x = new ImpastoDTO();
			//Setto i campi
			x.setId(Integer.parseInt(impasto.getId()));
			x.setNome(impasto.getNome());
			//Setto la pizza
			List<PizzaDTO> p = new ArrayList<PizzaDTO>();
			
    		impasto.getPizze().forEach(pizza ->{
    			PizzaDTO y = new PizzaDTO();
    			y.setId(Integer.parseInt(pizza.getId())); // SET ID
				y.setNome(pizza.getNome()); // SET NOME
				//Mi manca utente e ingrediente
				UtenteDTO z = new UtenteDTO();
				z.setUsername(pizza.getUtente().getUsername());
				z.setPassword(pizza.getUtente().getPassword());
				z.setId(Integer.parseInt(pizza.getUtente().getId()));
				y.setUtente(z);
				
				//setto Ingrediente DTO
				List<IngredienteDTO> u = new ArrayList<IngredienteDTO>();
    			pizza.getIngredienti().forEach(ingrediente -> {
    				IngredienteDTO v = new IngredienteDTO();
    				v.setId(Integer.parseInt(ingrediente.getId()));
    				v.setNome(ingrediente.getNome());
    				u.add(v);
    			});
				
				y.setIngredienti(u); // SET LISTA INGREDIENTI
				p.add(y);
    			
    		});
    		x.setPizza(p);
			listDTO.add(x);
		});
		return listDTO;
	}
	
	public static List<IngredienteDTO> transformToIngredienteDTO(List<Ingrediente> listaIngrediente){
		List<IngredienteDTO> listDTO = new ArrayList<IngredienteDTO>();
		listaIngrediente.forEach(ingrediente ->{
			IngredienteDTO x = new IngredienteDTO();
			//Setto i campi
			x.setId(Integer.parseInt(ingrediente.getId()));
			x.setNome(ingrediente.getNome());
			//Setto la pizza
			List<PizzaDTO> p = new ArrayList<PizzaDTO>();
			
			ingrediente.getPizze().forEach(pizza ->{
				PizzaDTO y = new PizzaDTO();
				y.setId(Integer.parseInt(pizza.getId())); // SET ID
				y.setNome(pizza.getNome()); // SET NOME
				
				UtenteDTO z = new UtenteDTO();
				z.setUsername(pizza.getUtente().getUsername());
				z.setPassword(pizza.getUtente().getPassword());
				z.setId(Integer.parseInt(pizza.getUtente().getId()));
				y.setUtente(z);
				
				// Setto ImpastoDTO
				ImpastoDTO u = new ImpastoDTO();
    			u.setNome(pizza.getImpasto().getNome());
    			u.setId(Integer.parseInt(pizza.getImpasto().getId()));
    			y.setImpasto(u); // SET IMPASTO
    			
				p.add(y);
    		});
    		x.setPizza(p);
			listDTO.add(x);
		});
		
		return listDTO;
	}
	
//	// E = DTO; F = Model
//	public List<E> transform(List<F> listaUtente){
//		List<E> listDTO = new ArrayList<E>();
//		
//		
//		
//		return null;
//	}
//	
}
