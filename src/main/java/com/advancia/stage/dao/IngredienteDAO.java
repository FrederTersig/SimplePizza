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

import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.dto.IngredienteDTO;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Ingrediente;
import com.advancia.stage.util.Gestore;
import com.advancia.stage.util.ListModel;

public class IngredienteDAO {

	public static Ingrediente find(int id) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		Ingrediente res = em.find(Ingrediente.class, id);
		Gestore.closing(emf, em);
		return res;
	}
	
	public static Ingrediente querySingleResult(String q, Map<String, Object> attr) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		Ingrediente res = null;
		try {
			Query query = em.createQuery(q);
			if(attr != null && !attr.isEmpty()) {
				setParamQuery(query, attr);
			}
			res = (Ingrediente) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}
		Gestore.closing(emf, em);
		return res;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<IngredienteDTO> allIngredienti(){
		String q = "from Ingrediente";
		List<IngredienteDTO> list = new ArrayList<IngredienteDTO>();
		List<Ingrediente> res = new ArrayList<Ingrediente>();
		
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();

//		TypedQuery<IngredienteDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.IngredienteDTO(i.id,i.nome) FROM Ingrediente i", IngredienteDTO.class);
		
		try {
			Query query = em.createQuery(q);
			res = (ArrayList<Ingrediente>) query.getResultList();
			
			list = ListModel.transformToIngredienteDTO(res);
			
		}catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}finally {
			Gestore.closing(emf, em);
		}

		return list;
	}
	
	public static List<IngredienteDTO> ingredientiPizza(int id){
		//String q = "from Ingrediente";
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();		
		
		//TypedQuery<IngredienteDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.IngredienteDTO(i.id,i.nome) FROM Ingrediente i JOIN pizza_ingrediente AS p_i ON i.id = p_i.ingrediente_id AND p_i.pizza_id = :id ", IngredienteDTO.class);
		TypedQuery<IngredienteDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.IngredienteDTO(i.id, i.nome) FROM Ingrediente i WHERE i.pizza.id = :id", IngredienteDTO.class);
		//String x = "SELECT new com.advancia.stage.dto.IngredienteDTO(i.id, i.nome) FROM Ingrediente i WHERE i.pizza.id = :id";
		
		q.setParameter("id", id);
		List<IngredienteDTO> res = q.getResultList();
		Gestore.closing(emf, em);
		return res;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public static List<Ingrediente> queryList(Map<String,Object> attr){
		String q = "from Ingrediente";
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		List<Ingrediente> res = null;
		try {
			Query query = em.createQuery(q);
			if(attr != null && !attr.isEmpty()) {
				setParamQuery(query, attr);
			}
			res = (ArrayList<Ingrediente>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}
		Gestore.closing(emf, em);
		return res;
	}
	
	public static Ingrediente persist(Ingrediente e) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(e);
			em.getTransaction().commit();
		}catch(RuntimeException re) {
			if(tx.isActive()) {
				tx.rollback();
			}
			System.out.println("persist failed" + re);
		}
		Gestore.closing(emf, em);
		return e;
	}
	
	
	public static Ingrediente update(Ingrediente e) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Ingrediente fusi = em.merge(e);
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