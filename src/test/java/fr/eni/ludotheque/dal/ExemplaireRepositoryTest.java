package fr.eni.ludotheque.dal;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.bo.Jeu;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ExemplaireRepositoryTest {

	@Autowired
	private ExemplaireRepository exemplaireRepository;

	@Autowired
	private JeuRepository jeuRepository;

	
	@Test
	@DisplayName("test récupération exemplaires disponibles et louables par jeu")
	public void testRecuperationNbExemplairesDispoParJeu() {
		//Arrange
		Jeu jeu = jeuRepository.findByReference("refWelcome");
		
		//Act
		int nbExemplairesDispo = exemplaireRepository.nbExemplairesDisponibleByNoJeu(jeu.getNoJeu());
		
		//Assert
		assertThat(nbExemplairesDispo).isEqualTo(1);
		log.debug("nbExemplaires=" + nbExemplairesDispo);
	}
	
}
