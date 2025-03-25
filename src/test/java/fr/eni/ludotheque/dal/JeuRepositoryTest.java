package fr.eni.ludotheque.dal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.exceptions.DataNotFound;
import jakarta.transaction.Transactional;

@SpringBootTest
public class JeuRepositoryTest {

	@Autowired
	private JeuRepository jeuRepository;
	
	@Test
	public void testTrouverJeuParNoJeu() {
		Jeu jeuDB = null;
		jeuDB = jeuRepository.findById(27).get();
		System.out.println(jeuDB);
		assertThat(jeuDB).isNotNull();
	}
	
	//@Test
	//@DisplayName("test création jeu et liens vers genres CAS POSITIF")
	//@Transactional
	public void testCreationJeu() {
		//Arrange
		Jeu jeu = new Jeu("SkyJo", "refSkyJo", 5.6f );
		jeu.setAgeMin(8);
		jeu.setDescription("Descr skyjo");
		jeu.addGenre(new Genre(1, ""));
		jeu.addGenre(new Genre(2, ""));
		
		//Act
		jeuRepository.save(jeu);
		
		//Assert
		assertThat(jeu.getNoJeu()).isNotNull();
		
	}
	
}
