package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ludotheque.bo.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	Utilisateur findByLogin(String username);
}
