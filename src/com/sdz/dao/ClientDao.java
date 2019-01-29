package com.sdz.dao;

import java.util.List;

import com.sdz.beans.Client;

public interface ClientDao {
	
	void creer( Client client ) throws DAOException;

    Client trouver( Long id ) throws DAOException;
    
    List <Client> lister() throws DAOException;
    
    void supprimer( Client client ) throws DAOException;
}
