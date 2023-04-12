package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.beans.Epreuve;

public class EpreuveDaoImpl implements EpreuveDao {

	private DaoFactory daoFactory;

	public EpreuveDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Epreuve> lister() {
		List<Epreuve> epreuves = new ArrayList<Epreuve>();

		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daoFactory.getConnection();
			String strSql = "SELECT e.ID , CONCAT(j.PRENOM, ' ', j.NOM) as Joueur, e.TYPE_EPREUVE, e.ANNEE, t.NOM AS NOM_TOURNOI,\r\n"
					+ "       IF(m.ID_VAINQUEUR=j.ID, 'vainqueur', 'finaliste') AS STATUT\r\n"
					+ "FROM JOUEUR j\r\n"
					+ "JOIN MATCH_TENNIS m ON j.ID = m.ID_VAINQUEUR OR j.ID = m.ID_FINALISTE\r\n"
					+ "JOIN EPREUVE e ON m.ID_EPREUVE = e.ID\r\n"
					+ "JOIN TOURNOI t ON e.ID_TOURNOI = t.ID\r\n"
					+ "ORDER BY e.ANNEE DESC";
			
			statement = connexion.prepareStatement(strSql);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				epreuves.add(new Epreuve(rs.getLong("id"), rs.getString("joueur"),
						rs.getString("type_epreuve"), rs.getInt("annee"), rs.getString("nom_tournoi"),
						rs.getString("statut")));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return epreuves;
	}

	@Override
	public List<Epreuve> rechercher(String chaine) {
		List<Epreuve> epreuves = new ArrayList<Epreuve>();
		Connection connexion = null;
		PreparedStatement statement = null;
		try {
			connexion = daoFactory.getConnection();
			String strSql = "SELECT e.ID , CONCAT(j.PRENOM, ' ', j.NOM) as Joueur, e.TYPE_EPREUVE, e.ANNEE, t.NOM AS NOM_TOURNOI,\r\n"
					+ "       IF(m.ID_VAINQUEUR=j.ID, 'vainqueur', 'finaliste') AS STATUT\r\n"
					+ "FROM JOUEUR j\r\n"
					+ "JOIN MATCH_TENNIS m ON j.ID = m.ID_VAINQUEUR OR j.ID = m.ID_FINALISTE\r\n"
					+ "JOIN EPREUVE e ON m.ID_EPREUVE = e.ID\r\n"
					+ "JOIN TOURNOI t ON e.ID_TOURNOI = t.ID\r\n"
					+ "WHERE e.ANNEE LIKE ? OR e.TYPE_EPREUVE LIKE ? OR j.NOM LIKE ? OR j.PRENOM LIKE ?";
			
			statement = connexion.prepareStatement(strSql);
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, "%" + chaine + "%");
			statement.setString(3, "%" + chaine + "%");
			statement.setString(4, "%" + chaine + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				epreuves.add(new Epreuve(rs.getLong("id"), rs.getString("joueur"),
						rs.getString("type_epreuve"), rs.getInt("annee"), rs.getString("nom_tournoi"),
						rs.getString("statut")));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return epreuves;
	}

}