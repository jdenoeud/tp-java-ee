<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Données client</title>
		 <link type="text/css" rel="stylesheet" href="inc/style.css" />
	</head>
<body>

	<div> <c:import url="inc/menu.jsp"/> </div>

	<p class="info"> ${ message } </p>
	
	<p>Client</p>
	<p>Nom: <c:out value="${ client.nom }" /> </p>
	<p>Prénom : <c:out value="${ client.prenom }" /></p>
    <p>Adresse : <c:out value="${ client.adresse }"/></p>
    <p>Numéro de téléphone : <c:out value="${ client.telephone }"/></p>
    <p>Email : <c:out value="${ client.email }"/></p>
</body>
</html>