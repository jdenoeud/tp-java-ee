package com.sdz.dao;

import static com.sdz.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdz.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdz.beans.Client;

public class ClientDaoImpl implements ClientDao {
	private static final String SQL_SELECT        = "SELECT id, nom, prenom, adresse, telephone, email, image FROM Client ORDER BY id";
	private static final String SQL_SELECT_PAR_ID = "SELECT id, nom, prenom, adresse, telephone, email, image FROM Client WHERE id = ?";
	private static final String SQL_INSERT        = "INSERT INTO Client (nom, prenom, adresse, telephone, email, image) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM Client WHERE id = ?";
    
	private DAOFactory daoFactory;
	
	ClientDaoImpl( DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public void creer(Client client) throws DAOException {
		 Connection connexion = null;
	     PreparedStatement preparedStatement = null;
	     ResultSet valeursAutoGenerees = null;

	     try {
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_INSERT, true, client.getNom(), client.getPrenom(), client.getAdresse(), client.getTelephone(),client.getEmail(),client.getImage() );
	         int statut = preparedStatement.executeUpdate();
	         if ( statut == 0 ) {
	             throw new DAOException( "Echec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	         }
	         valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	         if ( valeursAutoGenerees.next() ) {
	             client.setId( valeursAutoGenerees.getLong( 1 ) );
	         } else {
	             throw new DAOException( "Echec de la création du client, aucun ID auto-généré retourné." );
	         }
	     } catch ( SQLException e ) {
	         throw new DAOException( e );
	     } finally {
	         fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	     }

	}

	@Override
	public Client trouver(Long id) throws DAOException {
		return trouver(SQL_SELECT_PAR_ID, id);
	}

	@Override
	public List<Client> lister() throws DAOException {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Client> clients = new ArrayList<Client>();

        try {
        	//On lance la connexion
            connexion = daoFactory.getConnection();
            
            //Préparation  de la requête
            preparedStatement = connexion.prepareStatement( SQL_SELECT );
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
            if ( resultSet.next() ) {
                clients.add( map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return clients;
	}

	@Override
	public void supprimer(Client client) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	     try {
	         connexion = daoFactory.getConnection();
	         preparedStatement = initialisationRequetePreparee( connexion, SQL_DELETE_PAR_ID, true, client.getId());
	         int statut = preparedStatement.executeUpdate();
	         if ( statut == 0 ) {
	             throw new DAOException( "Echec de la suppression du client, aucune ligne supprimée." );
	         } else {
	        	 client.setId(null);
	         
	     } catch ( SQLException e ) {
	         throw new DAOException( e );
	     } finally {
	         fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	     }

	}
	
	private Client trouver( String sql, Object... objets) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Client client = null;

        try {
        	//On lance la connexion
            connexion = daoFactory.getConnection();
            
            //Préparation  de la requête
            preparedStatement = initialisationRequetePreparee( connexion, sql, false, objets );
            resultSet = preparedStatement.executeQuery();
            
            /* Parcours de la ligne de donnÃ©es retournÃ©e dans le ResultSet */
            if ( resultSet.next() ) {
                client = map( resultSet );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            fermeturesSilencieuses( resultSet, preparedStatement, connexion );
        }

        return client;
	}
	}
	
	
	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des clients (un ResultSet) et
	 * un bean Client.
	 */
	private static Client map( ResultSet resultSet ) throws SQLException {
	    Client client = new Client();
	    client.setId( resultSet.getLong( "id" ) );
	    client.setNom( resultSet.getString( "nom" ) );
	    client.setPrenom( resultSet.getString( "prenom" ) );
	    client.setAdresse( resultSet.getString( "adresse" ) );
	    client.setTelephone( resultSet.getString( "telephone" ) );
	    client.setEmail( resultSet.getString( "email" ) );
	    client.setImage( resultSet.getString( "image" ) );
	    return client;
	}

}
