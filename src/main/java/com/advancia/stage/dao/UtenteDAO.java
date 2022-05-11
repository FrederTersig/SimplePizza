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

import org.codehaus.jackson.map.JsonMappingException;

import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.dto.UtenteDTO;
import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.dto.IngredienteDTO;
import com.advancia.stage.model.Utente;
import com.advancia.stage.util.Gestore;
import com.advancia.stage.util.Utility;


public class UtenteDAO {

	
	public static UtenteDTO find(int id) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TypedQuery<UtenteDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.UtenteDTO(u.id, u.username, u.password) FROM Utente u WHERE u.id = :id",UtenteDTO.class);
		q.setParameter("id", id);
		UtenteDTO res = q.getSingleResult();
		Gestore.closing(emf, em);
		return res;
	}
	public static UtenteDTO authentication(String username, String password) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		TypedQuery<UtenteDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.UtenteDTO(u.id,u.username,u.password) FROM Utente u WHERE u.password = :password AND u.username = :username",UtenteDTO.class);
		q.setParameter("password", password);
		q.setParameter("username", username);
		UtenteDTO res = q.getSingleResult();
		Gestore.closing(emf, em);
		return res;
	}
	
//	TypedQuery<BookWithAuthorNames> q = em.createQuery(
//	        "SELECT new org.thoughts.on.java.model.BookWithAuthorNames(b.id, b.title, b.price, concat(a.firstName, ' ', a.lastName)) FROM Book b JOIN b.author a WHERE b.title LIKE :title",
//	        BookWithAuthorNames.class);
//	q.setParameter("title", "%Hibernate Tips%");
//	List<BookWithAuthorNames> books = q.getResultList();
//	 
//	for (BookWithAuthorNames b : books) {
//	    log.info(b);
//	}
	
	
	
	
	
	
	public static Utente querySingleResult( Map<String, Object> attr) {
		String q = "";
		Utente res = null;
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		try {
			Query query = em.createQuery(q);
			if(attr != null && !attr.isEmpty()) {
				setParamQuery(query, attr);
			}
			res = (Utente) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}
		Gestore.closing(emf, em);
		return res;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<UtenteDTO> allUtenti(){
		String q = "from Utente";
		List<UtenteDTO> list = new ArrayList<UtenteDTO>();
		List<Utente> res = new ArrayList<Utente>();
		
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		try {
			Query query = em.createQuery(q);
			res = (ArrayList<Utente>) query.getResultList();
//			CriteriaBuilder cb = em.getCriteriaBuilder();
//			CriteriaQuery<Utente> q = cb.createQuery(Utente.class);
//			Root<Utente> utenteInfo = q.from(Utente.class);
//			q.select(utenteInfo);
//			res = em.createQuery(q).getResultList();
//			

	    	
			list = Utility.transformToUtenteDTO(res);
			

			
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}finally {
//			Gestore.closing(emf, em);
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Utente> queryList(Map<String,Object> attr){
		String q = "from Utente";
		List<Utente> res = null;
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		try {
			Query query = em.createQuery(q);
			if(attr != null && !attr.isEmpty()) {
				setParamQuery(query, attr);
			}
			res = (ArrayList<Utente>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}
		Gestore.closing(emf, em);
		return res;
	}
	
	public static Utente persist(Utente e) {
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
	
	
	public static Utente update(Utente e) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Utente fusi = em.merge(e);
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
	List<Utente> list;
}
