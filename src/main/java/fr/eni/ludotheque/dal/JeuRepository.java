package fr.eni.ludotheque.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.eni.ludotheque.bo.Jeu;

public interface JeuRepository extends JpaRepository<Jeu, Integer>{
	
	@Query(nativeQuery = true, value = "select ex.no_jeu, j.titre, j.reference, j.description, j.tarif_jour, j.duree, j.age_min, COUNT(ex.no_jeu) as nb_exemplaires "
			+ " from jeux j left join exemplaires ex on j.no_jeu = ex.no_jeu "
			+ " where ex.louable = 1 "
			+ " group by ex.no_jeu, j.titre, j.reference, j.description, j.tarif_jour, j.duree, j.age_min ")
	List<Jeu> findAllJeuxAvecNbExemplaires();
	
	float findTarifJourByNoJeu(Integer noJeu);
}
