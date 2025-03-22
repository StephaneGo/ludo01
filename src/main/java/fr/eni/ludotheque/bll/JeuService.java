package fr.eni.ludotheque.bll;

import java.util.List;

import fr.eni.ludotheque.bo.Jeu;

public interface JeuService {
	
	void ajouterJeu(Jeu jeu);
	
	Jeu trouverJeuParNoJeu(Integer noJeu);
	
	List<Jeu> listeJeuxCatalogue();
}
