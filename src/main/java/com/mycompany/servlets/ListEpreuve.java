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
import com.mycompany.dao.EpreuveDao;
import com.mycompany.dao.EpreuveDaoImpl;

@WebServlet("/listepreuve")
public class ListEpreuve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EpreuveDao epreuveDao;

	public ListEpreuve() {
		super();
	}

	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		epreuveDao = new EpreuveDaoImpl(daoFactory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		
		request.setAttribute("epreuves", epreuveDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listepreuve.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("connectedUser") == null) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		
		// La recherche
		if (request.getParameter("action1").equals("rechercher")) {
			
			String chaine = request.getParameter("txtSearch");
			request.setAttribute("epreuves", epreuveDao.rechercher(chaine));
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/listepreuve.jsp").forward(request, response);
			
			// Deconnexion
		} else if (request.getParameter("action1").equals("deconnexion")) {
			
			session.setAttribute("connectedUser", null);
			response.sendRedirect("/AppJoueur/login");
			
			return;
		}
	}

}