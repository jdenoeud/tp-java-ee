<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Affichage d'une commande</title>
        <link type="text/css" rel="stylesheet" href="inc/style.css" />
    </head>
    
    <body>
    
    	<div> <c:import url="inc/menu.jsp"/> </div>

        <%-- Affichage de la chaîne "message" transmise par la servlet --%>
        <p class="info">${ message }</p>
         <c:if test="${ !erreur }">
	        <p>Client</p>
                <p>Nom : <c:out value="${ commande.client.nom }"/></p>
                <p>Prénom : <c:out value="${ commande.client.prenom }"/></p>
                <p>Adresse : <c:out value="${ commande.client.adresse }"/></p>
                <p>Numéro de téléphone : <c:out value="${ commande.client.telephone }"/></p>
                <p>Email : <c:out value="${ commande.client.email }"/></p>
                <p>Commande</p>
                <p>Date  : <c:out value="${ commande.date }"/></p> 
                <p>Montant  : <c:out value="${ commande.montant }"/></p> 
                <p>Mode de paiement  : <c:out value="${ commande.modePaiement }"/></p> 
                <p>Statut du paiement  : <c:out value="${ commande.statutPaiement }"/></p> 
                <p>Mode de livraison  : <c:out value="${ commande.modeLivraison }"/></p> 
                <p>Statut de la livraison  : <c:out value="${ commande.statutLivraison }"/></p> 
        </c:if> 
    </body>
</html>