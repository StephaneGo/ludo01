package fr.eni.ludotheque.dal;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import jakarta.transaction.Transactional;

@SpringBootTest
public class ClientRepositoryTest {

	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	@DisplayName("test création client et adresse CAS POSITIF")
	//@Transactional
	public void testCreationClient() {
		//Arrange
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client client = new Client("n1", "p1", "e1", "tel1", adresse);
		
		//Act
		clientRepository.save(client);
		
		//Assert
		assertThat(client.getNoClient()).isNotNull();
		Optional<Client> clientBD = clientRepository.findById(client.getNoClient());
		assertThat(clientBD.isPresent()).isTrue();
		if(clientBD.isPresent()) {
			assertThat(clientBD.get()).isEqualTo(client);
		}
		
	}
	
}
