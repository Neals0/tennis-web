package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mycompany.Utils.SessionUtils;
import com.mycompany.beans.Tournoi;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.TournoiDao;
import com.mycompany.dao.TournoiDaoImpl;

@WebServlet("/ajoutertournoi")
public class AjouterTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDao tournoiDao;

    public AjouterTournoi() {
    	super();
    }

	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		tournoiDao = new TournoiDaoImpl(daoFactory);
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
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutertournoi.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Tournoi newTournoi1 = new Tournoi();

		newTournoi1.setNom(request.getParameter("txtNomTournoi"));
		newTournoi1.setCode(request.getParameter("txtCodeTournoi"));

		tournoiDao.ajouter(newTournoi1);
		
		request.setAttribute("tournois", tournoiDao.lister());
		
		response.sendRedirect("/AppJoueur/listtournoi");
		
		//this.getServletContext().getRequestDispatcher("/WEB-INF/listtournoi.jsp").forward(request, response);
	}

}
