package com.mycompany.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	public static boolean isUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		return (session != null && session.getAttribute("connectedUser") != null);
	}
	
	// faire admin
}
