package com.mycompany.dao;

import java.util.List;
import com.mycompany.beans.Match;

public interface MatchDao {

	List<Match> lister();

	List<Match> rechercher(String chaine);
	
}
