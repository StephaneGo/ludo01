package fr.eni.ludotheque.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.ClientDTO;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController {
    private final ClientService clientService;

    public ClientRestController(ClientService clientService) {
        this.clientService = clientService;
    }
    
    @PostMapping
    public ResponseEntity<Client> ajouterClient(@RequestBody ClientDTO client) {
        Client nouveauClient = clientService.ajouterClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(nouveauClient);
    }

    @PutMapping("/{noClient}")
    public ResponseEntity<Client> modifierClient(@PathVariable(name="noClient") int noClient, @RequestBody ClientDTO clientDto) {
        Client clientModifie = clientService.modifierClient(noClient, clientDto);
        return ResponseEntity.ok(clientModifie);
    }


	
}
