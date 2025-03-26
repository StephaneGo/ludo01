package fr.eni.ludotheque.bll;

import java.util.List;

import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dto.LocationDTO;

public interface LocationService {
	
	Location ajouterLocation(LocationDTO locationDto);
	
	Facture retourExemplaires( List<String> codebarres);
	
	void trouverLocationParExemplaireCodebarre(String codebarre);
}
