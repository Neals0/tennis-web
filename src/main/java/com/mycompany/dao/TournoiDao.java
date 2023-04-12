package com.mycompany.dao;

import java.util.List;
import com.mycompany.beans.Tournoi;

public interface TournoiDao {
	
	void ajouter (Tournoi tournoi);
	
	void supprimer (Long id);
	
	void modifier (Tournoi tournoi);

	List<Tournoi> lister();
	
	List<Tournoi> rechercher(String chaine);
	
	Tournoi lecture(Long id);
	
}
