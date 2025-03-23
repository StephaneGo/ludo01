package fr.eni.ludotheque.bll;

import java.util.List;

import fr.eni.ludotheque.bo.Client;

public interface LocationService {
	
	void ajouterLocation(Integer noClient, String codebarre );
	
	void retourExemplaires(List<String> codebarres);
	
	void trouverLocationParExemplaireCodebarre(String codebarre);
}
