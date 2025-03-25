package fr.eni.ludotheque.bll;

//import static org.mockito.Mockito.doAnswer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.AdresseRepository;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
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
		ClientDTO clientDto = new ClientDTO("nX", "pX", "eX", "telX","rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client newClient = clientService.ajouterClient(clientDto);

		clientDto.setEmail("bob@free.fr");
		clientDto.setNom("nXX");
		clientDto.setPrenom("pXX");
		
		// Act
		clientService.modifierClient(newClient.getNoClient(),clientDto);

		// Assert
		Client client2 = clientService.trouverClientParId(newClient.getNoClient());
		assertThat(client2.getEmail()).isEqualTo(newClient.getEmail());
		assertThat(client2.getNom()).isEqualTo(newClient.getNom());
		assertThat(client2.getPrenom()).isEqualTo(newClient.getPrenom());
	}

	@Test
	@DisplayName("Test modification de l'adresse d'un client")
	@Transactional
	public void testModificationAdresseCasPositif() {
		// Arrange
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client client = new Client("nX", "pX", "eX", "telX", adresse);
		ClientDTO clientDto = new ClientDTO();
		BeanUtils.copyProperties(client, clientDto);
		BeanUtils.copyProperties(adresse, clientDto);

		Client newClient = clientService.ajouterClient(clientDto);
		
		AdresseDTO newAdresse = new AdresseDTO();
		newAdresse.setRue("rue des mouettes");
		newAdresse.setCodePostal("79000");
		newAdresse.setVille("Niort");
		
		// Act
		Client clientBase = clientService.modifierAdresse(newClient.getNoClient(), newAdresse);

		// Assert
		Optional<Adresse> newAdresseOpt = adresseRepository.findById(newClient.getAdresse().getNoAdresse());

		if (newAdresseOpt.isEmpty()) {
			fail();
		} else {
			assertThat(newClient.getAdresse()).isEqualTo(newAdresseOpt.get());
		}
		
	}

}
