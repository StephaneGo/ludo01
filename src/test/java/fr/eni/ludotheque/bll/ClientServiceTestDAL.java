package fr.eni.ludotheque.bll;

//import static org.mockito.Mockito.doAnswer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.AdresseRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
public class ClientServiceTestDAL {

	@Autowired
	private ClientService clientService;

	@Autowired
	private AdresseRepository adresseRepository;

	@Test
	@DisplayName("Test modification complète client")
	@Transactional
	public void testModifierClient() {
		// Arrange
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client client = new Client("nX", "pX", "eX", "telX", adresse);
		clientService.ajouterClient(client);

		client.setEmail("bob@free.fr");
		// Act
		clientService.modifierClient(client);

		// Assert
		Client client2 = clientService.trouverClientParId(client.getNoClient());
		assertThat(client2.getEmail()).isEqualTo(client.getEmail());
	}

	@Test
	@DisplayName("Test modification de l'adresse d'un client")
	@Transactional
	public void testModificationAdresseCasPositif() {
		// Arrange
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client client = new Client("nX", "pX", "eX", "telX", adresse);
		clientService.ajouterClient(client);
		adresse.setRue("rue des mouettes");
		adresse.setCodePostal("79000");
		adresse.setVille("Niort");
		
		// Act
		clientService.modifierAdresse(adresse);

		// Assert
		Optional<Adresse> newAdresseOpt = adresseRepository.findById(adresse.getNoAdresse());

		if (newAdresseOpt.isEmpty()) {
			fail();
		} else {
			assertThat(adresse).isEqualTo(newAdresseOpt.get());
		}

		
	}

}
