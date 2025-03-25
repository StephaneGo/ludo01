package fr.eni.ludotheque.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDTO;
import fr.eni.ludotheque.exceptions.DataNotFound;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAllClients() {
        List<Client> clientList = clientService.trouverTousLesClients();
        return ResponseEntity.ok(clientList);
    }

    @GetMapping("/{noClient}")
    public ResponseEntity<?> findClientById(@PathVariable(name="noClient") Integer noClient) {
        Client client = null;
        try {
        	clientService.trouverClientParId(noClient);
        }catch(DataNotFound notFound) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("noClient inconnu");
        }
        return ResponseEntity.ok(client);
    }
    
    @PostMapping
    public ResponseEntity<Client> ajouterClient(@RequestBody ClientDTO client) {
        Client nouveauClient = clientService.ajouterClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauClient);
    }

    @PutMapping("/{noClient}")
    public ResponseEntity<Client> modifierClient(@PathVariable(name="noClient") Integer noClient) {
        clientService.supprimerClient(noClient);
        return ResponseEntity.ok(null);
    }


    @DeleteMapping("/{noClient}")
    public ResponseEntity<?> supprimerClient(@PathVariable(name="noClient") int noClient) {
        clientService.supprimerClient(noClient);
        return ResponseEntity.ok(null);
    }
}
