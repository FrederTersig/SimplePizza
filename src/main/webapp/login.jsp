<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<?xml version = "1.0"?>
<html xmlns = "http://www.w3.org/1999/xhtml">
<!-- head del documento -->

<head>

<meta charset="ISO-8859-1">

<title>ProjectPizza: Login</title>

</head>

<!-- body  -->

<body>
	<h2>Login</h2>

	<form action="LoginServlet" method="post">

		<label for="username">Username</label> <input type="text"
			id="username" name="username" placeholder="username" required /><br>
		<label for="password">Password</label> <input type="password"
			id="password" name="password" placeholder="password" required /><br>
		<input type="submit" value="Login" />

	</form>
	
	
	<%
	String messaggio = (String) request.getAttribute("messaggio");
	if (messaggio != null) {
	%>
	<%-- Fine scriplet --%>
	<p>${messaggio}</p>
	
	<!--  fine codice html -->
	<%
	}
	%>
	<!-- ${pageContext.servletContext.contextPath}/LoginServlet -->

</body>

</html>