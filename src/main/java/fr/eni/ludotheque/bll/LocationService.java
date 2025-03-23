package fr.eni.ludotheque.bll;

import java.util.List;

import fr.eni.ludotheque.bo.Facture;

public interface LocationService {
	
	void ajouterLocation(Integer noClient, String codebarre );
	
	Facture retourExemplaires( List<String> codebarres);
	
	void trouverLocationParExemplaireCodebarre(String codebarre);
}
