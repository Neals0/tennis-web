package com.mycompany.dao;

import java.util.List;
import com.mycompany.beans.Joueur;

public interface JoueurDao {

	void ajouter (Joueur joueur);

	void supprimer (Long id);

	void modifier (Joueur joueur);

	List<Joueur> lister();

	List<Joueur> rechercher(String chaine);

	Joueur lecture(Long id);

}
