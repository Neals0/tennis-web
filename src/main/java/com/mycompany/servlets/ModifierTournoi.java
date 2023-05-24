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

@WebServlet("/modifiertournoi")
public class ModifierTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDao tournoiDao;
	private Long idl;

    public ModifierTournoi() {
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
		
		String id = request.getParameter("id");
		idl = Long.parseLong(id);

		Tournoi tournoi = tournoiDao.lecture(idl);
		//System.out.println(tournoi.getNom());
		request.setAttribute("tournoi", tournoi);

		this.getServletContext().getRequestDispatcher("/WEB-INF/modifiertournoi.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (!SessionUtils.isUserLoggedIn(request)) {
			response.sendRedirect("/AppJoueur/login");
			return;
		}
		
		String nom = request.getParameter("txtNomTournoi");
		String code = request.getParameter("txtCodeTournoi");
		
		Tournoi tournoi1 = new Tournoi(idl, nom, code);
		
		tournoiDao.modifier(tournoi1);

		request.setAttribute("tournois", tournoiDao.lister());
		response.sendRedirect("/AppJoueur/listtournoi");
		
	}
	
}
