package com.sdz.dao;

import java.util.List;

import com.sdz.beans.Commande;

public interface CommandeDao {
	
	void creer( Commande commande ) throws DAOException;

	Commande trouver( Long id ) throws DAOException;
    
    List <Commande> lister() throws DAOException;
    
    void supprimer( Commande commande ) throws DAOException;
}
