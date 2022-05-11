package com.advancia.stage.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;

import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.dto.UtenteDTO;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.util.Gestore;
import com.advancia.stage.util.Utility;

public class PizzaDAO {

	public static Pizza find(int id) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		Pizza res = em.find(Pizza.class, id);
		
		Gestore.closing(emf, em);
		return res;
	}
	
	public static Pizza querySingleResult(String q, Map<String, Object> attr) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		Pizza res = null;
		try {
			Query query = em.createQuery(q);
			if(attr != null && !attr.isEmpty()) {
				setParamQuery(query, attr);
			}
			res = (Pizza) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}
		Gestore.closing(emf, em);
		return res;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<PizzaDTO> allPizze(){
		String q = "from Pizza";
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		List<Pizza> res = null;
		List<PizzaDTO> list = new ArrayList<PizzaDTO>();
		try {
			Query query = em.createQuery(q);
			res = (ArrayList<Pizza>) query.getResultList();
			
			list = Utility.transformToPizzaDTO(res);
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		} finally {
			Gestore.closing(emf, em);
		}
		
		return list;
	}
	
	public static List<Pizza> pizzeCriteriaFromUtente(int id){
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//Crea la query
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pizza> q = cb.createQuery(Pizza.class);
		//Definisci la FROM
		Root<Pizza> pizzaInfo = q.from(Pizza.class);
		
		//Definisci la WHERE
		q.where(cb.equal(pizzaInfo.get("utente"), id));
		q.select(pizzaInfo);
		List<Pizza> utPizze = em.createQuery(q).getResultList();
		for(int i=0; i<utPizze.size(); i++) {
			System.out.println("---- Lista delle pizze dell'utente ----");
			System.out.println(utPizze.get(i).getId());
			System.out.println(utPizze.get(i).getNome());
			System.out.println(utPizze.get(i).getImpasto());
			System.out.println(utPizze.get(i).getIngredienti());
			//System.out.println(utPizze.get(i).getUtente());
			System.out.println("---- Fine ennesima pizza ----");
		}
		System.out.println("FINE");
//		
//		
//		TypedQuery<PizzaDTO> x = em.createQuery("SELECT p FROM Pizza p INNER JOIN p.ingrediente", PizzaDTO.class);
//		List<PizzaDTO> res = x.getResultList();
//		System.out.println("---------");
//		System.out.println(res);
//		
		Gestore.closing(emf, em);
		return utPizze;
	}
	// PROVA DEL METODO SOPRA
//	Join<Pizza, Ingrediente> ingr = pizzaInfo.join(Pizza_.ingrediente);
//	Join<Pizza, Utente> utx = pizzaInfo.join(Pizza_.utente);
//	Join<Pizza, Impasto> impx = pizzaInfo.join(Pizza_.impasto);
//	//Definisci la DTO projection
//	q.select(cb.construct(PizzaDTO.class,
//			pizzaInfo.get(Pizza_.id),
//			pizzaInfo.get(Pizza_.nome), 
//			pizzaInfo.get(Pizza_.utente), 
//			pizzaInfo.get(Pizza_.impasto),
//			pizzaInfo.get(Pizza_.ingrediente)
//			));
//	
//	//Definisci la WHERE
//	q.where(cb.equal(pizzaInfo.get("utente"), id));
	
	
	
	//System.out.println(em.createQuery(q).getResultList());
//	List<PizzaDTO> utPizze = em.createQuery(q).getResultList();
//	for(int i=0; i<utPizze.size(); i++) {
//		System.out.println("---- Lista delle pizze dell'utente ----");
//		System.out.println(utPizze.get(i).getPizzaId());
//		System.out.println(utPizze.get(i).getNome());
//		System.out.println(utPizze.get(i).getImpasto());
//		System.out.println(utPizze.get(i).getIngredienti());
//		System.out.println(utPizze.get(i).getUtente());
//		System.out.println("---- Fine ennesima pizza ----");
//	}
	
	
	// FINE PROVA
	
	

	public static List<PizzaDTO> pizzeFromUtente( int id){
		//String q = "from Pizza where utente_id = :utente_id";
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		List<PizzaDTO> res = null;
		tx.begin();

		//TypedQuery<PizzaDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.PizzaDTO(p.utente_id, p.id, p.nome, p.impasto_id, i.nome) FROM Pizza p JOIN p.impasto i WHERE p.utente_id = :id", PizzaDTO.class);
		//TypedQuery<PizzaDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.PizzaDTO(p.utente_id,p.id,p.nome,p.impasto_id, i.nome) FROM Pizza p JOIN fetch p.impasto i WHERE p.utente_id = :id", PizzaDTO.class);
		//TypedQuery<PizzaDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.PizzaDTO(p.utente.id, p.id, p.nome,p.impasto.id, i.nome) FROM Pizza p, Impasto i WHERE p.utente.id = :id ", PizzaDTO.class);
		//TypedQuery<PizzaDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.PizzaDTO(p.utente.id, p.id, p.nome,p.impasto.id, p.impasto.nome, p.ingrediente) FROM Pizza p WHERE p.utente.id = :id ", PizzaDTO.class);
		//Questa sotto è corretta togliendo p.ingrediente.nome
		//TypedQuery<PizzaDTO> q = em.createQuery( "SELECT new com.advancia.stage.dto.PizzaDTO(p.utente.id, p.id, p.nome, p.impasto.id, p.impasto.nome, p.ingrediente.nome) From Pizza p WHERE p.utente.id = :id", PizzaDTO.class);
		try {
		TypedQuery<PizzaDTO> q = em.createQuery( "SELECT new com.advancia.stage.dto.PizzaDTO(p.utente.id, p.id, p.nome, p.impasto.id, p.impasto.nome, i.nome) "
				+"from Pizza p JOIN p.ingrediente i "
				+"WHERE p.utente.id = :id", PizzaDTO.class);
		q.setParameter("id", id);
		res = q.getResultList();
		System.out.println(res);

		
		Gestore.closing(emf, em);
		}catch(Exception e){
			System.out.println(e);
		}
		return res;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public static List<Pizza> queryList( Map<String,Object> attr){
		String q = "from Pizza where utente_id = :utente_id";
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		List<Pizza> res = null;
		try {
			Query query = em.createQuery(q);
			if(attr != null && !attr.isEmpty()) {
				setParamQuery(query, attr);
			}
			res = (ArrayList<Pizza>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}
		Gestore.closing(emf, em);
		return res;
	}
	
	public static Pizza persist(Pizza e) {
		if(null != e) {
			EntityManagerFactory emf = Gestore.createEMF();
			EntityManager em = Gestore.createEM(emf);
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				em.merge(e);
				em.getTransaction().commit();
			}catch(RuntimeException re) {
				if(tx.isActive()) {
					tx.rollback();
				}
				System.out.println("persist failed" + re);
			}finally {
				Gestore.closing(emf, em);
			}
		}
		
		return e;
	}
	
	
	public static Pizza update(Pizza e) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Pizza fusi = em.merge(e);
		em.flush();
		em.clear();
		em.getTransaction().commit();
		Gestore.closing(emf, em);
		return fusi;
	}
	
	private static void setParamQuery(Query query, Map<String, Object> parameters) {
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
    }
	
}