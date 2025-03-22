package fr.eni.ludotheque.bll;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.exceptions.DataNotFound;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class JeuServiceTestDAL {

	@Autowired
	private JeuService jeuService;


	//@Test
	//@DisplayName("Test ajout jeu")
	//@Transactional
	public void testAjoutJeu() {
		// Arrange
		Jeu jeu = new Jeu("Welcome", "refWelcome", 10.2f);
		jeu.addGenre(new Genre(1, ""));
		jeu.addGenre(new Genre(2, ""));

		// Act
		jeuService.ajouterJeu(jeu);

		// Assert
		Jeu jeuDB = null;
		
		try {
			jeuDB = jeuService.trouverJeuParNoJeu(jeu.getNoJeu());
		}catch(DataNotFound dnf) {
			fail();
			return;
		}
		
		assertThat(jeuDB).isEqualTo(jeu);
		assertThat(jeuDB.getGenres().size()).isEqualTo(jeu.getGenres().size());
	}
	
	@Test
	@DisplayName("Test trouver les jeux et le nb d'exemplaires disponible")
	public void testTrouverJeuxDisponibles() {
		
		List<Jeu> jeux = jeuService.listeJeuxCatalogue();
		
		log.debug(jeux.toString());
	}


}
