package fr.eni.ludotheque.bll;

import java.util.List;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;

public interface ClientService {
	
	Client ajouterClient(ClientDTO client);
	
	Client trouverClientParId(Integer id);
	
	List<Client> trouverClientsParNom(String nom);
	
	Client modifierClient(Integer noClient, ClientDTO clientDto);
	
	Client modifierAdresse(Integer noClient, AdresseDTO adresseDto);
	
	void supprimerClient(Integer noClient);

	List<Client> trouverTousLesClients();
}
