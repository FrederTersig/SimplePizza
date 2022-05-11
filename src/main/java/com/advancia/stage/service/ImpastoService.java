package com.advancia.stage.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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

import com.advancia.stage.dao.ImpastoDAO;
import com.advancia.stage.dto.ImpastoDTO;
import com.advancia.stage.model.Impasto;

@Path("/impasto")
public class ImpastoService {
	
	@PersistenceContext(unitName="Impasto",type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
    // URI:
    // /contextPath/servletPath/Impasto
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImpasto_JSON() {
    	
    	GenericEntity<List<ImpastoDTO>> entity;
        List<ImpastoDTO> listaImpasto = ImpastoDAO.allImpasti();
        entity = new GenericEntity<List<ImpastoDTO>> (listaImpasto) {};
        
        System.out.println(listaImpasto);
        
        Response risposta = Response.ok(entity).build();
        
        return risposta;
    }

    // URI:
    // /contextPath/servletPath/Impasto/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Impasto getImpasto(@PathParam("id") int id) {
        return ImpastoDAO.find(id);
    }

    // URI:
    // /contextPath/servletPath/Impasto
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Impasto addIngrediente(Impasto imp) {
        return ImpastoDAO.persist(imp);
    }

    // URI:
    // /contextPath/servletPath/Impasto
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Impasto updateIngrediente(Impasto imp) {
        return ImpastoDAO.update(imp);
    }

    //@DELETE
    //@Path("/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    //public void deleteImpasto(@PathParam("id") String id) {
    //	ImpastoDAO.deleteImpasto(id);
    //}

}