package fr.eni.ludotheque.bll;

import java.util.List;

import fr.eni.ludotheque.bo.Client;

public interface ClientService {
	
	void ajouterClient(Client client);
	
	Client trouverClientParId(Integer id);
}
