package fr.eni.ludotheque.bll;

//import static org.mockito.Mockito.doAnswer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.ClientDTO;

@SpringBootTest
public class ClientServiceTest {
	
	@Autowired
	private ClientService clientService;
	
	@MockitoBean
	private ClientRepository clientRepository;
	
	@Test
	@DisplayName("Ajout d'un client cas positif")
	public void testAjouterClientCasPositif() {
		//Arrange
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client client = new Client("n1", "p1", "e1", "tel1", adresse);
		ClientDTO clientDto = new ClientDTO("n1", "p1", "e1", "tel1", "rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		
		org.mockito.Mockito.doAnswer((invocation) -> {
			Client cli = invocation.getArgument(0);
			cli.setNoClient(999);
			return cli;
						}).when(clientRepository).save(client);
		
		//Act
		clientService.ajouterClient(clientDto);

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

	@Test
	@DisplayName("Trouver clients par nom contenant les caractères recherchés")
	public void testTrouverClientsParNomCasNomConnu() {
		//Arrange
		String nom = "UPI";
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client clientATrouver = new Client("DUPIEUX", "Quentin", "e1", "tel1", adresse);
		List<Client> listeClients = new ArrayList<>();
		listeClients.add(clientATrouver);
		when(clientRepository.findByNomContaining(nom)).thenReturn(listeClients);
		
		//Act
		List<Client> clients = clientService.trouverClientsParNom(nom);
		
		//Assert
		assertThat(clients.size()).isEqualTo(1);
		
	}
	
	/*
	@Test
	@DisplayName("Test modification complète client")
	//@Transactional
	public void testModifierClient() {
		//Arrange 
		Adresse adresse = new Adresse("rue des Cormorans", "44860", "Saint Aignan Grand Lieu");
		Client client = new Client("nX", "pX", "eX", "telX", adresse);
		org.mockito.Mockito.doAnswer((invocation) -> {
			Client cli = invocation.getArgument(0);
			cli.setNoClient(999);
			return cli;
						}).when(clientRepository).save(client);		
		clientService.ajouterClient(client);

		client.setEmail("bob@free.fr");
		//Act
		clientService.modifierClient(client);
		
		//Assert
		Client client2 = clientService.trouverClientParId(client.getNoClient());
		assertThat(client2.getEmail()).isEqualTo(client.getEmail());
	}
	*/
	
	@Test
	@DisplayName("Test modification de l'adresse d'un client")
	public void testModificationAdresseCasPositif() {
		//
		
		//Act
		//clientService.modifierAdresse(adresse);
		
	}
	
}



