package com.sdz.forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdz.beans.Client;

public class CreationClientForm {
	private static final String CHAMP_NOM       = "nomClient";
    private static final String CHAMP_PRENOM    = "prenomClient";
    private static final String CHAMP_ADRESSE   = "adresseClient";
    private static final String CHAMP_TELEPHONE = "telephoneClient";
    private static final String CHAMP_EMAIL     = "emailClient";
    
    private String message;
    
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private Map<String, String> erreurs = new HashMap<String, String>();
    	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	//Méthode principale, contenant la logique de validation
	public Client creerClient( HttpServletRequest request) {
    	
    	//Récupération des paramètres de la requete
		String nom = request.getParameter( CHAMP_NOM );
	    String prenom = request.getParameter( CHAMP_PRENOM );
	    String adresse = request.getParameter( CHAMP_ADRESSE );
	    String telephone = request.getParameter( CHAMP_TELEPHONE );
	    String email = request.getParameter( CHAMP_EMAIL );
	    
	    //Création du client 
		Client client = new Client();
		
		// Vérification et initialisation des paramètres
		try {
	        validationNom( nom );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_NOM, e.getMessage() );
	    }
		client.setNom(nom);
		
		try {
	        validationPrenom( prenom );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_PRENOM, e.getMessage() );
	    }
		client.setPrenom(prenom);
		
		try {
	        validationAdresse( adresse );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_ADRESSE, e.getMessage() );
	    }
	    client.setAdresse( adresse );
	    
	    try {
	        validationTelephone( telephone );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_TELEPHONE, e.getMessage() );
	    }
	    client.setTelephone( telephone );
	    
	    try {
	        validationEmail( email );
	    } catch ( Exception e ) {
	        setErreur( CHAMP_EMAIL, e.getMessage() );
	    }
	    client.setEmail( email );
	    
	    if (erreurs.isEmpty()){
	    	message = "Succès de la création du client";
	    }else {
	    	message = "Echec de la création du client";
	    	
	    }
	    
    	return client;
    } 
	
	private void validationNom(String nom) throws Exception {
		if (nom!= null) {
			if ( nom.length() < 2) {
				throw new Exception("Le nom doit contenir au moins 2 caractères ");
			}
		} else {
			throw new Exception("Merci de saisir un nom");
		}
	}
	
	private void validationPrenom(String prenom) throws Exception {
		if (prenom.length()  < 2) {
			throw new Exception("Le prénom doit contenir au moins 2 caractères ");
		}
	}
	
	private void validationAdresse(String adresse) throws Exception {
		if (adresse != null) {
			if ( adresse.length() < 10) {
				throw new Exception("L'adresse doit contenir au moins 10 caractères ");
			}
		} else {
			throw new Exception("Merci de saisir une adresse");
		}
	}
	
	private void validationTelephone(String telephone) throws Exception {
		if (telephone != null) {
			if ( !telephone.matches("^[0-9]+$")) {
				throw new Exception("Le numéro de téléphone doit uniquement contenir des chiffres");
			} else  if (telephone.length()<4){
				throw new Exception("Le numéro de téléphone doit contenir au moins 4 chiffres");
			}
		} else {
			throw new Exception("Merci de saisir un numéro de téléphone");
		}
	}
	
	private void validationEmail(String email) throws Exception {
		if ( !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse email valide");
			} 
		} 
	
	private void setErreur(String champ, String value) {
		erreurs.put(champ, value);
	} 
	
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
	    String valeur = request.getParameter( nomChamp );
	    if ( valeur == null || valeur.trim().length() == 0 ) {
	        return null;
	    } else {
	        return valeur.trim();
	    }
	}
    
}
