package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.exceptions.DataNotFound;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
	@NonNull
	private ClientRepository clientRepository;
	
	@Override
	public void ajouterClient(Client client) {
		
		clientRepository.save(client);
	}

	@Override
	public Client trouverClientParId(Integer id) {
		
		Optional<Client> optClient = clientRepository.findById(id);
		if(optClient.isEmpty()) {
			throw new DataNotFound("Client", id);
		}
		return optClient.get();
	}

	@Override
	public List<Client> trouverClientsParNom(String nom) {
		
		return clientRepository.findByNomContaining(nom);
	}

	

}
