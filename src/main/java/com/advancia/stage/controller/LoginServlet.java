package com.advancia.stage.controller;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.advancia.stage.dao.ImpastoDAO;
import com.advancia.stage.dao.IngredienteDAO;
import com.advancia.stage.dao.PizzaDAO;
import com.advancia.stage.dao.UtenteDAO;
import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.dto.IngredienteDTO;
import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.dto.UtenteDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;



import com.advancia.stage.util.Gestore;
import com.advancia.stage.model.Impasto;
import com.advancia.stage.model.Ingrediente;
import com.advancia.stage.model.Pizza;
import com.advancia.stage.model.Utente;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public LoginServlet() {
        super();
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
		System.out.println("LoginServlet: doPost");
		
		String messaggio = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String destination = "";
		UtenteDTO ut = null;
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		System.out.println(username + " ---- " + password);
		//String q = "from Utente where username= :username AND password= :password";


		try {
			ut = UtenteDAO.authentication(username,password);
			System.out.println(ut);
		}catch (NoResultException e) {
			System.out.println("Nessun Risultato");
		}
		
		
		if(ut != null) { // Login Riuscito
				System.out.println("Login Riuscito");
				System.out.println("Username" + ut.getUsername());
				destination = "/home.jsp";
				
				
				//Prendo tutti gli ingredienti
				List<IngredienteDTO> ingList = IngredienteDAO.allIngredienti();
				System.out.println(ingList);

				//Prendo tutti gli impasti
				List<ImpastoDTO> impList = ImpastoDAO.allImpasti();
				System.out.println(impList);
				
				
				//prendo tutte le pizze create dall'utente
				//Testo pizza DTO nuovo
//				Map<String, Object> pizzaMap = new HashMap<String, Object>();
//				pizzaMap.put("utente_id", ut.getId());
				//List<PizzaDTO> pizList = PizzaDAO.pizzeFromUtente(ut.getId());
				//System.out.println(pizList);
				
				List<Pizza> pizList = PizzaDAO.pizzeCriteriaFromUtente(ut.getId());
				
//				// Stream --> Serve per riavere ciò che mi serve, filtra
//				// per riavere come risultato ogni pizza che ha tra gli ingredienti la mozzarella
//				pizList = pizList.stream().filter(pizza -> pizza.getIngredienti().stream().anyMatch(ingrediente -> ingrediente.getNome().equalsIgnoreCase("mozzarella")))
//				         .collect(Collectors.toList());
//				// Consumo forEach ecc --> 
//				// Modifica la lista da cui viene chiamato, settandone i campi (in questo caso fa il set del nome, ma se
//				// volessimo settare più campi si possono inserire più metodi usando le parentesi graffe
//				pizList.forEach(pizza -> pizza.setNome("some"));
//				
				
				// IMPORTANTE
				System.out.println(" GUARDA QUI ");


				
				request.setAttribute("utente", ut);
				request.setAttribute("ingList", ingList);
				request.setAttribute("impList", impList);
				request.setAttribute("pizList", pizList);
				
				System.out.println("### TEST ###");
				System.out.println("Utente , Ingredienti, impasto, pizza");
				System.out.println(ut);
				System.out.println(ingList);
				System.out.println(impList);
				System.out.println(pizList);
				
				
				System.out.println("### FINE TEST ###");				
				
				System.out.println("--> Procedo con /home.jsp");
		}else{ // Login Fallito
				System.out.println("Login Fallito");
				destination =  "/login.jsp";
				messaggio = "Login Fallito: Non esiste un utente con queste credenziali";
				request.setAttribute("messaggio", messaggio);
				
		}
		
		

		
		System.out.println("Fine di Login Servlet");
		System.out.println("Destinazione --> " + destination);
		System.out.println("Messaggio --> "+messaggio);
		//getServletContext().getRequestDispatcher(destination).forward(request,  response);
		
		
		try {
			System.out.println("Request Dispatcher --");
            RequestDispatcher requestDispatcher;
 
            // path is a string specifying the pathname to
            // the resource. If it is relative, it must be
            // relative against the current servlet
            requestDispatcher=request.getRequestDispatcher(destination);
            requestDispatcher.forward(request, response);
        }
        catch (ServletException servletException) {
        	System.out.println(servletException);
        }
        catch (IOException ioException) {
        	System.out.println(ioException);
        }
        catch (IllegalStateException illegalStateException) {
        	System.out.println(illegalStateException);
        }
		
		
		
		
		
	}

}
