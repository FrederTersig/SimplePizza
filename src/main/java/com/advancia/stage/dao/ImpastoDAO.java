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
import com.advancia.stage.model.Impasto;
import com.advancia.stage.util.Gestore;
import com.advancia.stage.util.ListModel;

public class ImpastoDAO {

	public static Impasto find(int id) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		Impasto res = em.find(Impasto.class, id);
		Gestore.closing(emf, em);
		return res;
	}
	
	public static Impasto querySingleResult(String q, Map<String, Object> attr) {
		Impasto res = null;
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		try {
			Query query = em.createQuery(q);
			if(attr != null && !attr.isEmpty()) {
				setParamQuery(query, attr);
			}
			res = (Impasto) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}
		Gestore.closing(emf, em);
		return res;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Impasto> queryList(Map<String,Object> attr){
		
		String q = "from Impasto";
		List<Impasto> res = null;
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		try {
			Query query = em.createQuery(q);
			if(attr != null && !attr.isEmpty()) {
				setParamQuery(query, attr);
			}
			res = (ArrayList<Impasto>) query.getResultList();
		} catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}
		Gestore.closing(emf, em);
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public static List<ImpastoDTO> allImpasti(){
		
		String q = "from Impasto";
		List<ImpastoDTO> list = new ArrayList<ImpastoDTO>();
		List<Impasto> res = new ArrayList<Impasto>();
		
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
		//TypedQuery<ImpastoDTO> q = em.createQuery("SELECT new com.advancia.stage.dto.ImpastoDTO(i.id, i.nome, i.pizza) FROM Impasto i",ImpastoDTO.class);
			Query query = em.createQuery(q);
			res = (ArrayList<Impasto>) query.getResultList();
		
		//List<ImpastoDTO> list = new ArrayList<ImpastoDTO>();
		
			list = ListModel.transformToImpastoDTO(res);
		
		
		
		}catch (NoResultException e) {
			System.out.println("Nessun risultato per la query - " + e);
		} catch (Exception e) {
			System.out.println("Errore nell'esecuzione della query - " + e);
		}finally {
			Gestore.closing(emf, em);
		}
		return list;
	}
	
	public static Impasto persist(Impasto e) {
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
	
	
	public static Impasto update(Impasto e) {
		EntityManagerFactory emf = Gestore.createEMF();
		EntityManager em = Gestore.createEM(emf);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Impasto fusi = em.merge(e);
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