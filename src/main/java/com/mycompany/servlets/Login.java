package com.mycompany.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mycompany.Utils.HashClass;
import com.mycompany.beans.User;
import com.mycompany.dao.DaoFactory;
import com.mycompany.dao.UserDaoImpl;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userdaoimpl;

	public Login() {
		super();
	}

	@Override
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		userdaoimpl = new UserDaoImpl(daoFactory);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("txtLogin");
		String password = request.getParameter("txtPassword");

		System.out.println(HashClass.sha1(password));

//		User connectedUser = userdaoimpl.isValidLogin(login, password);

		User connectedUser = null;

		try {
			connectedUser = userdaoimpl.isValidLogin(login, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		if (connectedUser != null) {

			HttpSession session = request.getSession(true);
			session.setAttribute("connectedUser", connectedUser);
			response.sendRedirect("/AppJoueur/listjoueur");

		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}

}
