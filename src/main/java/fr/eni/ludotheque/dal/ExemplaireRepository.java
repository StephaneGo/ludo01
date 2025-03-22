package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.eni.ludotheque.bo.Exemplaire;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer>{

	@Query("SELECT COUNT(e) FROM Exemplaire e WHERE e.jeu.noJeu = :noJeu")
	long countExemplairesByNoJeu(Integer noJeu);
	
	
}
