package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.beans.Match;

public class MatchDaoImpl implements MatchDao {

	private DaoFactory daoFactory;

	public MatchDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Match> lister() {
		List<Match> matchs = new ArrayList<Match>();

		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnection();
			String strSql = "SELECT mt.ID, CONCAT(J.NOM, ' ', J.PRENOM) as Vainqueur, CONCAT(J1.NOM, ' ', J1.PRENOM) as Finaliste, J.SEXE as Sexe \r\n"
					+ "FROM match_tennis as mt \r\n" + "INNER JOIN JOUEUR J ON J.ID = mt.ID_VAINQUEUR \r\n"
					+ "INNER JOIN JOUEUR J1 ON J1.ID = mt.ID_FINALISTE \r\n" + "ORDER BY mt.ID;";

			statement = connexion.prepareStatement(strSql);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				matchs.add(new Match(rs.getInt("id"), rs.getString("Vainqueur"), rs.getString("Finaliste"),
						rs.getString("Sexe")));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return matchs;
	}

	@Override
	public List<Match> rechercher(String chaine) {
		List<Match> matchs = new ArrayList<Match>();
		Connection connexion = null;
		PreparedStatement statement = null;

		try {

			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement(
					"SELECT mt.ID, CONCAT(J.NOM, ' ', J.PRENOM) as Vainqueur, CONCAT(J1.NOM, ' ', J1.PRENOM) as Finaliste, J.SEXE as Sexe \r\n"
							+ "FROM match_tennis as mt \r\n" + "INNER JOIN JOUEUR J ON J.ID = mt.ID_VAINQUEUR \r\n"
							+ "INNER JOIN JOUEUR J1 ON J1.ID = mt.ID_FINALISTE \r\n"
							+ "WHERE J.NOM LIKE ? OR J.PRENOM LIKE ? OR J1.NOM LIKE ? OR J1.PRENOM LIKE ?\r\n"
							+ "ORDER BY ID;");

			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, chaine + "%");
			statement.setString(3, chaine + "%");
			statement.setString(4, chaine + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				matchs.add(new Match(rs.getInt("id"), rs.getString("Vainqueur"), rs.getString("Finaliste"),
						rs.getString("Sexe")));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);

		}
		return matchs;
	}

}
