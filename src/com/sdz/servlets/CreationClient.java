package com.sdz.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.beans.Client;

/**
 * Servlet implementation class CreationClient
 */

public class CreationClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CreationClient() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Récupération des paramètres de la requete
		String nom= request.getParameter("nomClient");
		String prenom = request.getParameter( "prenomClient" );
        String adresse = request.getParameter( "adresseClient" );
        String telephone = request.getParameter( "telephoneClient" );
        String email = request.getParameter( "emailClient" );
        String message ;
        
        //vérification des paramètres rentrés
        if(nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty()) {
        	message = "Veuillez remplir tous les champs oligatoires!"
        			+ " <br> <a href=\"creerClient.jsp\">Cliquez ici</a> pour accéder au formulaire de création d'un client. ";
        } else {
        	message = "Client créé avec succès";
        }
		
		//Création du client et initialisation des paramètres
		Client client = new Client();
		client.setNom(nom);
		client.setNom( nom );
	    client.setPrenom( prenom );
	    client.setAdresse( adresse );
	    client.setTelephone( telephone );
	    client.setEmail( email );
		
	    request.setAttribute("client", client);
	    request.setAttribute("message", message);
		
		this.getServletContext().getRequestDispatcher( "/afficherClient.jsp" ).forward( request, response );
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
