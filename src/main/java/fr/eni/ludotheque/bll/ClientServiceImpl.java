package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Adresse;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dal.AdresseRepository;
import fr.eni.ludotheque.dal.ClientRepository;
import fr.eni.ludotheque.dto.AdresseDTO;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;
import fr.eni.ludotheque.exceptions.EmailClientAlreadyExistException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService{
	@NonNull
	private ClientRepository clientRepository;
	
	@NonNull
	private AdresseRepository adresseRepository;
	
	@Override
	public Client ajouterClient(ClientDTO clientDto)  {
		
		Client client = new Client();
		Adresse adresse = new Adresse();
		BeanUtils.copyProperties(clientDto, client);
		BeanUtils.copyProperties(clientDto, adresse);
		client.setAdresse(adresse);
		try {
			clientRepository.save(client);
		}catch(DataIntegrityViolationException ex) {
			throw new EmailClientAlreadyExistException();
		}
		Client newClient = clientRepository.findById(client.getNoClient()).orElseThrow(()->new DataNotFound("Client", client.getNoClient()));
		
		return newClient;
	}

	@Override
	public Client trouverClientParId(Integer id)  {
		
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

	@Override
	public Client modifierClient(Integer noClient, ClientDTO clientDto) {
		Client client = clientRepository.findById(noClient).orElseThrow(()->new DataNotFound("Client", noClient));
		
		BeanUtils.copyProperties(clientDto, client);
		BeanUtils.copyProperties(clientDto, client.getAdresse());
		
		clientRepository.save(client);
		
		return client;
	}

	@Override
	public Client modifierAdresse(Integer noClient, AdresseDTO adresseDto) {
		Client client = clientRepository.findById(noClient).orElseThrow(()->new DataNotFound("Client", noClient));
		
		BeanUtils.copyProperties(adresseDto, client.getAdresse());
		
		adresseRepository.save(client.getAdresse());
		
		return client;
		
	}

	@Override
	public void supprimerClient(Integer noClient) {
		clientRepository.deleteById(noClient);
		
	}

	@Override
	public List<Client> trouverTousLesClients() {
		return clientRepository.findAll();
	}

	

}
