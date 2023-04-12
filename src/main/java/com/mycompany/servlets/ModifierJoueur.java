package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mycompany.Utils.SessionUtils;
import com.mycompany.beans.Joueur;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.JoueurDao;
import com.mycompany.dao.JoueurDaoImpl;

@WebServlet("/modifierjoueur")
public class ModifierJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;
	private Long idl;

	public ModifierJoueur() {
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
		
		String id = request.getParameter("id");
		idl = Long.parseLong(id);

		Joueur joueur = joueurDao.lecture(idl);
		//System.out.println(joueur.getNom());
		request.setAttribute("joueur", joueur);

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifierjoueur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		
		String nom = request.getParameter("txtNom");
		String prenom = request.getParameter("txtPrenom");
		String sexe = request.getParameter("opSexe");

		Joueur joueur1 = new Joueur(idl, nom, prenom, sexe);

		joueurDao.modifier(joueur1);

		request.setAttribute("joueurs", joueurDao.lister());
		response.sendRedirect("/AppJoueur/listjoueur");

	}
}
