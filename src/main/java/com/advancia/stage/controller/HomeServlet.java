package com.advancia.stage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.advancia.stage.dao.ImpastoDAO;
import com.advancia.stage.dao.IngredienteDAO;
import com.advancia.stage.dao.PizzaDAO;
import com.advancia.stage.dao.UtenteDAO;
import com.advancia.stage.dto.UtenteDTO;
import com.advancia.stage.model.*;
import com.advancia.stage.util.Gestore;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HomeServlet: doPost");
		
		//Lista Ingredienti
		List<Ingrediente> arc = new ArrayList<Ingrediente>();
		
		
		//Attributi/Parametri presi
		String nome = request.getParameter("nome");
		int utenteId = Integer.parseInt( request.getParameter("utente"));
		int impastoTipo = Integer.parseInt( request.getParameter("impasto"));
		String listaCheck[] = request.getParameterValues("ingrediente");
		
		
		//Prendo utente
		UtenteDTO utente = UtenteDAO.find(utenteId);
		
		//Prendo Impasto
		Impasto imp = ImpastoDAO.find(impastoTipo);
		
		//Prendo Ingredienti
		for(int n=0; n<listaCheck.length; n++) {
			Ingrediente ing = IngredienteDAO.find(Integer.parseInt(listaCheck[n]));
			arc.add(ing);
		}
		
		//Creo nuovo oggetto Pizza
		Pizza x = new Pizza();
		x.setImpasto(imp);
		x.setIngredienti(arc);
		x.setNome(nome);
		//x.setUtente(utente);
		
		System.out.println("---- MOSTRO LA PIZZA CREATA ----");
		System.out.println(x);
		System.out.println("#######");
		
		PizzaDAO.persist(x);
		
		//Procedo con il caricamento degli altri dati
		
		
		//Prendo tutti gli ingredienti
		List<Ingrediente> ingList = IngredienteDAO.queryList(null);
		//System.out.println(ingList);

		
		//Prendo tutti gli impasti
		List<Impasto> impList = ImpastoDAO.queryList(null);
		//System.out.println(impList);

		//Prendo tutte le pizze dell'utente
		Map<String, Object> pizzaMap = new HashMap<String, Object>();
		pizzaMap.put("utente_id", utente.getId());
		List<Pizza> pizList = PizzaDAO.queryList(pizzaMap);
		//System.out.println(pizList);

		
		request.setAttribute("utente", utente);
		request.setAttribute("ingList", ingList);
		request.setAttribute("impList", impList);
		request.setAttribute("pizList", pizList);
		
		//Chiudo l'entityManager & l'entityManagerFactory
		System.out.println("--- CHECK ----");
		System.out.println(utente);
		System.out.println(ingList);
		System.out.println(impList);
		System.out.println(pizList);
		System.out.println("--- FINE CHECK ---");
		getServletContext().getRequestDispatcher("/home.jsp").forward(request,  response);
		
	}

}
