package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.Utils.SessionUtils;
import com.mycompany.beans.Joueur;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;

@WebServlet("/ajouterjoueur")
public class AjouterJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;

	public AjouterJoueur() {
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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Joueur newJoueur1 = new Joueur();

		newJoueur1.setNom(request.getParameter("txtNom"));
		newJoueur1.setPrenom(request.getParameter("txtPrenom"));
		newJoueur1.setSexe(request.getParameter("opSexe"));

		joueurDao.ajouter(newJoueur1);

		request.setAttribute("joueurs", joueurDao.lister());
		response.sendRedirect("/AppJoueur/listjoueur");

	}

}
