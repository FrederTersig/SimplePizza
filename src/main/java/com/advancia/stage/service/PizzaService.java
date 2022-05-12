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

import com.advancia.stage.dao.PizzaDAO;
import com.advancia.stage.dto.PizzaDTO;
import com.advancia.stage.model.Pizza;

@Path("/pizza")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PizzaService {
	
	@PersistenceContext(unitName="Pizza",type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;
	
	
	
//	 @GET @Produces("application/json") 
//	 public Employees getEmployees() { 
//		 List empList = myDAO.getAllEmployees(); 
//		 log.info("size " + empList.size()); Employees employees = new Employees(); 
//		 employees.setEmployeeList(empList); 
//		 return employees; 
//		 } 
//	 @XmlRootElement(name = "Employees") 
//	 public class 
//	 Employees { 
//		 List employeeList; 
//setters and getters goes here 
//	 } 
//	@XmlRootElement() 
//	 class Emp { //fields here }
//	 }
	
	
    // URI:
    // /contextPath/servletPath/pizza
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPizza_JSON() {
//        List<Pizza> listaPizze = PizzaDAO.allPizze();
//        return listaPizze;
    	GenericEntity<List<PizzaDTO>> entity;
    	List<PizzaDTO> listaPizza = PizzaDAO.allPizze();
    	entity = new GenericEntity<List<PizzaDTO>> (listaPizza) {};
    	System.out.println(listaPizza);
    	return Response.ok(entity).build();
    }

    // URI:
    // /contextPath/servletPath/pizza/{id}
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pizza getPizza(@PathParam("id") int id) {
        return PizzaDAO.find(id);
    }

    // URI:
    // /contextPath/servletPath/pizza
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Pizza addPizza(Pizza piz) {
        return PizzaDAO.persist(piz);
    }

    // URI:
    // /contextPath/servletPath/pizza
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Pizza updatePizza(Pizza piz) {
        return PizzaDAO.update(piz);
    }

    //@DELETE
    //@Path("/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    //public void deletePizza(@PathParam("id") String id) {
    //	PizzaDAO.deletePizza(id);
    //}

}