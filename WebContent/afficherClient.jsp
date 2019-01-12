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

	<p class="info"> ${ message } </p>
	
	<p>Client</p>
	<p>Nom: ${ client.nom } </p>
	<p>Prénom : ${ client.prenom }</p>
    <p>Adresse : ${ client.adresse }</p>
    <p>Numéro de téléphone : ${ client.telephone }</p>
    <p>Email : ${ client.email }</p>
</body>
</html>