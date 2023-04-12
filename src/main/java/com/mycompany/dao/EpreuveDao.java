package com.mycompany.dao;

import java.util.List;

import com.mycompany.beans.Epreuve;

public interface EpreuveDao {
	
	List<Epreuve> lister();

	List<Epreuve> rechercher(String chaine);

}
