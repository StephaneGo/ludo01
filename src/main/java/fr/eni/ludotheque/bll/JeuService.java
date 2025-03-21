package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Jeu;

public interface JeuService {
	
	void ajouterJeu(Jeu jeu);
	
	Jeu trouverJeuParNoJeu(Integer noJeu);
}
