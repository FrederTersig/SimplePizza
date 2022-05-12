package com.advancia.stage.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.advancia.stage.dao.UtenteDAO;
import com.advancia.stage.dto.UtenteDTO;
import com.advancia.stage.model.Utente;

@Path("/utente")
//@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UtenteService {
	//Richiami semplicemente il dao e ritorni ciò che ricevi
	
	
	@PersistenceContext(unitName="Utente",type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	
    // URI:
    // /contextPath/servletPath/Utente
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUtente_JSON() {

		List<UtenteDTO> listaUtente;
		GenericEntity<List<UtenteDTO>> entity;
		listaUtente = UtenteDAO.allUtenti();
		entity = new GenericEntity<List<UtenteDTO>> (listaUtente) {};
        Response risposta = Response.ok( entity ).build();
        return risposta;

    }

    // URI:
    // /contextPath/servletPath/Utente/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UtenteDTO getUtente(@PathParam("id") int id) {
    	//entityManager.find(Utente.class, id); //cerca l'utente
    	//String n = Integer.toString(id);
        return UtenteDAO.find(id);
    }

    // URI:
    // /contextPath/servletPath/utente
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Utente addUtente(Utente ut) {
    	//entityManager.persist(ut); // Crea l'utente
        return UtenteDAO.persist(ut);
    }

    // URI:
    // /contextPath/servletPath/utente
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Utente updateUtente(Utente ut) {
    	//entityManager.merge(ut); // Aggiorna l'utente
        return UtenteDAO.update(ut);
    }

    //@DELETE
    //@Path("/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    //public void deleteUtente(@PathParam("id") String id) {
    	/* Utente utente = getUtente(id); // Per cancellare utente
    	 * if(null != utente){
    	 * 		entityManager.remove(utente);
    	 * }
    	 * */
    //	UtenteDAO.deleteUtente(id);
    //}

}