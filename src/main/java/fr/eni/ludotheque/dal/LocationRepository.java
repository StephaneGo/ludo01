package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{
	@Query("SELECT l FROM Location l WHERE l.exemplaire.codebarre :codebarre")
	Location findLocationByCodebarreWithJeu(@Param("codebarre") String codebarre);
}
