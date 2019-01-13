package com.sdz.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdz.beans.Client;
import com.sdz.forms.CreationClientForm;

/**
 * Servlet implementation class CreationClient
 */

public class CreationClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE_FORM="/WEB-INF/creerClient.jsp";
	public static final String VUE_SUCCESS="/WEB-INF/afficherClient.jsp";
	
	public static final String ATT_CLIENT="client";
	public static final String ATT_FORM="form";
	
    public CreationClient() {
        super();
      
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Instanciation de l'objet formulaire client
		CreationClientForm form = new CreationClientForm();
		Client client = form.creerClient(request);
								
		request.setAttribute(ATT_CLIENT, client);
		request.setAttribute(ATT_FORM, form);
		
		if ( form.getErreurs().isEmpty()) {
			this.getServletContext().getRequestDispatcher( VUE_SUCCESS ).forward( request, response );
		} else {
			this.getServletContext().getRequestDispatcher( VUE_FORM ).forward( request, response );
		}
		
	}

}
