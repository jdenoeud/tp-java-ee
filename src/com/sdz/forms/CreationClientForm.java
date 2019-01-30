package com.sdz.forms;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdz.beans.Client;
import com.sdz.dao.ClientDao;
import com.sdz.dao.DAOException;

public final class CreationClientForm {
	private static final String CHAMP_NOM       = "nomClient";
    private static final String CHAMP_PRENOM    = "prenomClient";
    private static final String CHAMP_ADRESSE   = "adresseClient";
    private static final String CHAMP_TELEPHONE = "telephoneClient";
    private static final String CHAMP_EMAIL     = "emailClient";
    
    private ClientDao clientDao;
    
  //Constructeur 
    public CreationClientForm ( ClientDao clientDao) {
    	this.clientDao = clientDao;
    }
    
    private String resultat;
 

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	private Map<String, String> erreurs = new HashMap<String, String>();
    	
	public Map<String, String> getErreurs() {
		return erreurs;
	}

	//Méthode principale, contenant la logique de validation et la creation d'un client en BDD
	public Client creerClient( HttpServletRequest request) {
    	
    	//Récupération des paramètres de la requete
		String nom = getValeurChamp( request, CHAMP_NOM );
	    String prenom = getValeurChamp( request, CHAMP_PRENOM );
	    String adresse = getValeurChamp( request, CHAMP_ADRESSE );
	    String telephone = getValeurChamp( request, CHAMP_TELEPHONE );
	    String email = getValeurChamp( request, CHAMP_EMAIL );
	    
	    //Création du client 
		Client client = new Client();
		
		 traiterNom( nom, client );
	        traiterPrenom( prenom, client );
	        traiterAdresse( adresse, client );
	        traiterTelephone( telephone, client );
	        traiterEmail( email, client );
	        traiterImage( client, request, chemin );
		
		try  {
			if (erreurs.isEmpty()) {
				//Creation du client en BDD
				clientDao.creer(client);
				//
				resultat = "Pas d'erreurs, creation du client";
			} else {
				resultat = "Erreurs empechant de rentrer le client en BDD";
			}
		} catch ( DAOException e ) {
			setErreur("imprevu", "Erreur lors de la création du client en BDD");
			resultat = "Erreur lors de l'enregistrement du clientr en bdd";
			e.printStackTrace(); //Pour sortir le message d'erreur de l'exception
		}
		
				
		  private void traiterNom( String nom, Client client ) {
		        try {
		            validationNom( nom );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_NOM, e.getMessage() );
		        }
		        client.setNom( nom );
		    }

		    private void traiterPrenom( String prenom, Client client ) {
		        try {
		            validationPrenom( prenom );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_PRENOM, e.getMessage() );
		        }
		        client.setPrenom( prenom );
		    }

		    private void traiterAdresse( String adresse, Client client ) {
		        try {
		            validationAdresse( adresse );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_ADRESSE, e.getMessage() );
		        }
		        client.setAdresse( adresse );
		    }

		    private void traiterTelephone( String telephone, Client client ) {
		        try {
		            validationTelephone( telephone );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_TELEPHONE, e.getMessage() );
		        }
		        client.setTelephone( telephone );
		    }

		    private void traiterEmail( String email, Client client ) {
		        try {
		            validationEmail( email );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_EMAIL, e.getMessage() );
		        }
		        client.setEmail( email );
		    }

		    private void traiterImage( Client client, HttpServletRequest request, String chemin ) {
		        String image = null;
		        try {
		            image = validationImage( request, chemin );
		        } catch ( FormValidationException e ) {
		            setErreur( CHAMP_IMAGE, e.getMessage() );
		        }
		        client.setImage( image );
		    }

		    private void validationNom( String nom ) throws FormValidationException {
		        if ( nom != null ) {
		            if ( nom.length() < 2 ) {
		                throw new FormValidationException( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
		            }
		        } else {
		            throw new FormValidationException( "Merci d'entrer un nom d'utilisateur." );
		        }
		    }

		    private void validationPrenom( String prenom ) throws FormValidationException {
		        if ( prenom != null && prenom.length() < 2 ) {
		            throw new FormValidationException( "Le prénom d'utilisateur doit contenir au moins 2 caractères." );
		        }
		    }

		    private void validationAdresse( String adresse ) throws FormValidationException {
		        if ( adresse != null ) {
		            if ( adresse.length() < 10 ) {
		                throw new FormValidationException( "L'adresse de livraison doit contenir au moins 10 caractères." );
		            }
		        } else {
		            throw new FormValidationException( "Merci d'entrer une adresse de livraison." );
		        }
		    }

		    private void validationTelephone( String telephone ) throws FormValidationException {
		        if ( telephone != null ) {
		            if ( !telephone.matches( "^\\d+$" ) ) {
		                throw new FormValidationException( "Le numéro de téléphone doit uniquement contenir des chiffres." );
		            } else if ( telephone.length() < 4 ) {
		                throw new FormValidationException( "Le numéro de téléphone doit contenir au moins 4 chiffres." );
		            }
		        } else {
		            throw new FormValidationException( "Merci d'entrer un numéro de téléphone." );
		        }
		    }

		    private void validationEmail( String email ) throws FormValidationException {
		        if ( email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
		            throw new FormValidationException( "Merci de saisir une adresse mail valide." );
		        }
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
