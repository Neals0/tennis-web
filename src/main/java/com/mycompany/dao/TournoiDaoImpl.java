package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.beans.Tournoi;

public class TournoiDaoImpl implements TournoiDao {

	private DaoFactory daoFactory;

	public TournoiDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Tournoi tournoi) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {

			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("INSERT INTO TOURNOI(NOM,CODE) VALUES (?,?)");

			statement.setString(1, tournoi.getNom());
			statement.setString(2, tournoi.getCode());

			statement.executeUpdate();

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

	}

	@Override
	public List<Tournoi> lister() {

		Connection connexion = null;
		PreparedStatement statement = null;

		List<Tournoi> tournois = new ArrayList<Tournoi>();
		
		try {

			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("SELECT * FROM tournoi");

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				tournois.add(new Tournoi(rs.getLong("id"), rs.getString("nom"), rs.getString("code")));
			}

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return tournois;
	}

	@Override
	public Tournoi lecture(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("SELECT * FROM tournoi WHERE id=?");

			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {

				return new Tournoi(

						rs.getLong("id"), rs.getString("nom"), rs.getString("code"));
			} else {
				return null;
			}

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public List<Tournoi> rechercher(String chaine) {
		List<Tournoi> tournois = new ArrayList<Tournoi>();
		Connection connexion = null;
		PreparedStatement statement = null;

		try {

			connexion = daoFactory.getConnection();
			statement = connexion
					.prepareStatement("SELECT * FROM tournoi WHERE nom LIKE ? OR code LIKE ? OR id LIKE ?");
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, chaine + "%");
			statement.setString(3, chaine + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				tournois.add(new Tournoi(rs.getLong("id"), rs.getString("code"), rs.getString("nom")));
			}
		} catch (Exception exception) {
			throw new RuntimeException(exception);

		}
		return tournois;
	}

	@Override
	public void supprimer(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("DELETE FROM tournoi WHERE id=?");
			statement.setLong(1, id);
			statement.executeUpdate();

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void modifier(Tournoi tournoi) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("UPDATE tournoi SET nom=?, code=? WHERE id=?");

			statement.setString(1, tournoi.getNom());
			statement.setString(2, tournoi.getCode());
			statement.setLong(3, tournoi.getId());

			statement.executeUpdate();

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
	
	
}
