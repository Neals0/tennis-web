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
import com.mycompany.dao.MatchDao;
import com.mycompany.dao.MatchDaoImpl;

@WebServlet("/listmatch")
public class ListMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchDao matchDao;

	public ListMatch() {
		super();
	}

	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		matchDao = new MatchDaoImpl(daoFactory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}

		request.setAttribute("matchs", matchDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/listmatch.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}

		// La recherche
		if (request.getParameter("action1").equals("rechercher")) {

			String chaine = request.getParameter("txtSearch");
			request.setAttribute("matchs", matchDao.rechercher(chaine));

			this.getServletContext().getRequestDispatcher("/WEB-INF/listmatch.jsp").forward(request, response);

			// Deconnexion
		} else if (request.getParameter("action1").equals("deconnexion")) {

			session.setAttribute("connectedUser", null);
			response.sendRedirect("/AppJoueur/login");

			return;
		}
	}
}
