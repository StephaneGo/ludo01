package fr.eni.ludotheque.bll;

//import static org.mockito.Mockito.doAnswer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;

@SpringBootTest
public class ClientServiceTest {
	
	@Autowired
	private ClientService clientService;
	
	@MockitoBean
	private ClientRepository clientRepository;
	
//	@Test
//	@DisplayName("Ajout d'un client cas positif")
	public void testAjouterClientCasPositif() {
		//Arrange
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client client = new Client("n1", "p1", "e1", "tel1", adresse);
		
		org.mockito.Mockito.doAnswer((invocation) -> {
			Client cli = invocation.getArgument(0);
			cli.setNoClient(999);
			return cli;
						}).when(clientRepository).save(client);
		
		//Act
		clientService.ajouterClient(client);

		//Assert
		assertThat(client.getNoClient()).isNotNull();
		assertThat(client.getNoClient()).isEqualTo(999);
		
	}
	
	@Test
	@DisplayName("Trouver un client par id cas id est connu")
	public void testTrouverClientParIdCasIdConnu() {
		//Arrange
		Integer idClientRecherche = 99;
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client clientATrouver = new Client("n1", "p1", "e1", "tel1", adresse);
		when(clientRepository.findById(idClientRecherche)).thenReturn(Optional.of(clientATrouver));
		
		//Act
		Client client = clientService.trouverClientParId(idClientRecherche);
		
		//Assert
		assertThat(client).isEqualTo(clientATrouver);
		
	}

}



