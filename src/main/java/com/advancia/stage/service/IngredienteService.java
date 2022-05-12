package com.advancia.stage.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.advancia.stage.dao.IngredienteDAO;
import com.advancia.stage.dto.IngredienteDTO;
import com.advancia.stage.model.Ingrediente;

@Path("/ingrediente")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IngredienteService {
	
	@PersistenceContext(unitName="Ingrediente",type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
    // URI:
    // /contextPath/servletPath/Ingrediente
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngrediente_JSON() {
    	
    	GenericEntity<List<IngredienteDTO>> entity;
    	List<IngredienteDTO> listaIngrediente = IngredienteDAO.allIngredienti();
    	entity = new GenericEntity<List<IngredienteDTO>> (listaIngrediente) {};
    	
    	System.out.println(listaIngrediente);
    	
    	
//        List<IngredienteDTO> listaIngrediente = IngredienteDAO.allIngredienti();
        return Response.ok(entity).build();
    }

    // URI:
    // /contextPath/servletPath/Ingrediente/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Ingrediente getIngrediente(@PathParam("id") int id) {
        return IngredienteDAO.find(id);
    }

    // URI:
    // /contextPath/servletPath/pizza
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Ingrediente persist(Ingrediente ing) {
        return IngredienteDAO.persist(ing);
    }

    // URI:
    // /contextPath/servletPath/pizza
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Ingrediente update(Ingrediente ing) {
        return IngredienteDAO.update(ing);
    }

    //@DELETE
    //@Path("/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    //public void deleteIngrediente(@PathParam("id") String id) {
    //	IngredienteDAO.deleteIngrediente(id);
    //}

}