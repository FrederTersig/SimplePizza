<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<?xml version = "1.0"?>
<html xmlns ="http://www.w3.org/1999/xhtml">
<%@ page import="java.util.*" %>
<%@ page import="com.advancia.stage.dto.*" %>
<%@ page import="com.advancia.stage.model.*" %>
<!-- head del documento -->
<head>
	<meta charset="ISO-8859-1">
	<title>ProjectPizza: Home</title>
</head>

<body>
	<h2>Crea la tua pizza</h2>
	<form action = "HomeServlet" method="post">
			<!-- Input nuovo contatto da inserire nella rubrica dell'account connesso -->

			<label for="nome">Nome</label>
			<input type = "text" id="nome" name = "nome" placeholder="nome" required/><br/>
			
			<label>
				<span> Impasto </span><br/>
				<% //Inizio Scriptlet 
					List<ImpastoDTO> impList = (List<ImpastoDTO>) request.getAttribute("impList");
					if (impList.size() != 0){
						//CICLO FOR DA SETTARE
						//Per ogni elemento nella lista, mi inserisci una nuova radio
						//button con il value corrispondente di quell'elemento
						for(int i=0; i< impList.size(); i++){

				%>
				 
				<input type = "radio" name = "impasto" value='<%= impList.get(i).getId() %>' /> <%= impList.get(i).getNome() %><br/>
						
				<%  //inizio Scriptlet
						}
					} // FINE IF INGLISTA
				%> <%-- Fine scriplet --%>
			</label>
			
			<label>
				<span>Ingredienti</span><br/>
				<%  //inizio Scriptlet
					List<IngredienteDTO> ingList = (List<IngredienteDTO>) request.getAttribute("ingList");
					if (ingList.size() != 0){
					// CICLO FOR 
						for(int i=0; i< ingList.size();i++){

				%> <%-- Fine scriplet --%>
				
				<input type = "checkbox" id="ingrediente" name = "ingrediente" value='<%= ingList.get(i).getId() %>' /> <%= ingList.get(i).getNome() %><br/>
				
				<%  //inizio Scriptlet
						}
					} // FINE IF INGLISTA
				%> <%-- Fine scriplet --%>
				
			</label>
			
			
			<input type = "hidden" name="utente" value='${utente.getId()}'/>
			
			<input type = "submit" value = "Salva Pizza" />
			
	</form>
	<!--  Shows Pizza = Parte tabellare -->
	
	<% // inizio Scriptlet
		List<Pizza> pizList = (List<Pizza>) request.getAttribute("pizList");
		if (pizList != null && pizList.size() != 0){
	
	%> <%-- Fine scriplet --%>
	<br/>
	<h2>Pizze Favorite</h2>
	<table>
		<!--  TABELLA -->
			<tr>
				<th>Nome Pizza</th>
				<th>Impasto</th>
				<th>Ingredienti</th>
			</tr>
	
	<!-- Ciclo pizza, Internamente ciclo ingredienti -->
	<% // inizio Scriptlet	
		//System.out.println("-->" + pizList.size());
			for(int i=0; i<pizList.size(); i++){
// 				System.out.println("Ciclo scriplet");
// 				System.out.println(pizList.get(i));
// 				System.out.println(pizList.get(i).getNome()); 
// 				System.out.println(pizList.get(i).getIngredienti()); 
// 				System.out.println(pizList.get(i).getImpasto()); 
// 				System.out.println("Fine Ciclo");

				
	%> <%-- Fine scriplet --%>
			<tr>
				<th> <%= pizList.get(i).getNome() %> </th>
				<th> <%= pizList.get(i).getImpasto().getNome() %> </th>
				<th> <%= pizList.get(i).getIngredienti() %> </th>
			</tr>
			
	<% // inizio Scriptlet	
		//System.out.println(" fine for");
			}
	
	%>  <%-- Fine scriplet --%>
			
	</table>
		
	<%  //inizio Scriptlet  
			
		}else{
			
	%> <%-- Fine scriplet --%>
	
	<p>Non hai nessuna pizza preferita!</p>
	<%  //inizio Scriptlet
			
		}
			
	%> <%-- Fine scriplet --%>
	
	<!-- Fine -->
</body>
</html>