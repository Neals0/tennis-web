package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.Utils.SessionUtils;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;

@WebServlet("/supprimerjoueur")
public class SupprimerJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;

	public SupprimerJoueur() {
		super();
	}

	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		joueurDao = new JoueurDaoImpl(daoFactory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		
	    HttpSession session = request.getSession(false);
	    if (!SessionUtils.isAdmin(session)) {
	        response.sendRedirect(request.getContextPath() + "/Login");
	        return;
	    }
		
		

		String id = request.getParameter("id");
		long idl = Long.parseLong(id);
		joueurDao.supprimer(idl);

		request.setAttribute("joueurs", joueurDao.lister());
		response.sendRedirect("/AppJoueur/listjoueur");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
	}
}
