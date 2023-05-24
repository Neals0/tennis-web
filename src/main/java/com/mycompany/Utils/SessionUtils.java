package com.mycompany.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.mycompany.beans.User;

public class SessionUtils {

	public static boolean isUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		return (session != null && session.getAttribute("connectedUser") != null);
	}

    public static boolean isAdmin(HttpSession session) {
        User utilisateur = (User) session.getAttribute("connectedUser");
        return utilisateur != null && utilisateur.getProfil() == 1;
    }

}
