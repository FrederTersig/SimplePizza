package com.advancia.stage.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Gestore {
	
	
	public static EntityManagerFactory createEMF() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("RESTFULpizza");
		return emf;
	}
	
	public static EntityManager createEM(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		return em;
	}
	
	public static void closing(EntityManagerFactory emf, EntityManager em) {
		//Chiudi em
		//Chiudi emf
		if (em.isOpen() && em != null) {
			em.close();
		}
		if (emf.isOpen() && emf != null) {
			emf.close();
		}
		
	}
	
	
}
