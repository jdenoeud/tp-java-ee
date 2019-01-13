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
	public static final String VUE_FORM="/WEB-INF/creerClient.jsp";
	public static final String VUE_SUCCESS="/WEB-INF/afficherClient.jsp";
	
	public static final String ATT_CLIENT="client";
	public static final String ATT_MESSAGE="message";
	public static final String ATT_ERREUR="erreur";
	
	public static final String CHAMP_NOM       = "nomClient";
    public static final String CHAMP_PRENOM    = "prenomClient";
    public static final String CHAMP_ADRESSE   = "adresseClient";
    public static final String CHAMP_TELEPHONE = "telephoneClient";
    public static final String CHAMP_EMAIL     = "emailClient";
	

    public CreationClient() {
        super();
       
    }


    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération des paramètres de la requete
				String nom = request.getParameter( CHAMP_NOM );
			    String prenom = request.getParameter( CHAMP_PRENOM );
			    String adresse = request.getParameter( CHAMP_ADRESSE );
			    String telephone = request.getParameter( CHAMP_TELEPHONE );
			    String email = request.getParameter( CHAMP_EMAIL );
			        
		        String message ;
		        boolean erreur;
		        
		        //vérification des paramètres rentrés
		        if(nom.isEmpty() || adresse.isEmpty() || telephone.isEmpty()) {
		        	message = "Veuillez remplir tous les champs oligatoires!"
		        			+ " <br> <a href=\"creationClient\">Cliquez ici</a> pour accéder au formulaire de création d'un client. ";
		        	erreur = true;
		        } else {
		        	message = "Client créé avec succès";
		        	erreur = false;
		        }
				
				//Création du client et initialisation des paramètres
				Client client = new Client();
				client.setNom(nom);
			    client.setPrenom( prenom );
			    client.setAdresse( adresse );
			    client.setTelephone( telephone );
			    client.setEmail( email );
				
			    request.setAttribute(ATT_CLIENT, client);
			    request.setAttribute(ATT_MESSAGE, message);
			    request.setAttribute(ATT_ERREUR, erreur);
				
				this.getServletContext().getRequestDispatcher( VUE_SUCCESS ).forward( request, response );
		
	}

}
