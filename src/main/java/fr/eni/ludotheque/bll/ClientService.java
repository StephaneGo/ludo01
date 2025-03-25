package fr.eni.ludotheque.bll;

import java.util.List;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDTO;

public interface ClientService {
	
	Client ajouterClient(ClientDTO client);
	
	Client trouverClientParId(Integer id);
	
	List<Client> trouverClientsParNom(String nom);
	
	Client modifierClient(Integer noClient, ClientDTO clientDto);
	
	void modifierAdresse(Adresse adresse);
}
