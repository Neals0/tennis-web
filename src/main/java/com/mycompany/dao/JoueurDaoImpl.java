package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.beans.Joueur;

public class JoueurDaoImpl implements JoueurDao {

	private DaoFactory daoFactory;

	public JoueurDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void ajouter(Joueur joueur) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {

			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("INSERT INTO JOUEUR(NOM,PRENOM,SEXE) VALUES (?,?,?)");

			statement.setString(1, joueur.getNom());
			statement.setString(2, joueur.getPrenom());
			statement.setString(3, joueur.getSexe());

			statement.executeUpdate();

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public List<Joueur> lister() {

		Connection connexion = null;
		PreparedStatement statement = null;

		List<Joueur> joueurs = new ArrayList<>();

		try {

			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("SELECT * FROM joueur");

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				joueurs.add(new Joueur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe")));
			}

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
		return joueurs;
	}

	@Override
	public Joueur lecture(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("SELECT * FROM joueur WHERE id=?");

			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {

				return new Joueur(

						rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("sexe"));
			} else {
				return null;
			}

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public List<Joueur> rechercher(String chaine) {
		List<Joueur> joueurs = new ArrayList<Joueur>();
		Connection connexion = null;
		PreparedStatement statement = null;

		try {

			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("SELECT * FROM joueur WHERE nom LIKE ? OR prenom LIKE ? OR id LIKE ?");
			statement.setString(1, "%" + chaine + "%");
			statement.setString(2, chaine + "%");
			statement.setString(3, chaine + "%");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				joueurs.add(new Joueur(rs.getLong("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("sexe")));
			}
		} catch (Exception exception){
			throw new RuntimeException(exception);

		}
		return joueurs;
	}

	@Override
	public void supprimer(Long id) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("DELETE FROM joueur WHERE id=?");
			statement.setLong(1, id);
			statement.executeUpdate();

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public void modifier(Joueur joueur) {
		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.prepareStatement("UPDATE joueur SET nom=?, prenom=?, sexe=? WHERE id=?");

			statement.setString(1, joueur.getNom());
			statement.setString(2, joueur.getPrenom());
			statement.setString(3, joueur.getSexe());
			statement.setLong(4, joueur.getId());

			statement.executeUpdate();

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}
}
