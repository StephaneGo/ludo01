package fr.eni.ludotheque.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.ludotheque.bll.ClientService;
import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.dto.AdresseDTO;
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
    public ResponseEntity<ApiResponse<List<Client>>> findAllClients() {
        List<Client> clientList = clientService.trouverTousLesClients();
        return ResponseEntity.ok(new ApiResponse(true, "ok", clientList));
    }

    @GetMapping("/{noClient}")
    public ResponseEntity<ApiResponse<Client>> findClientById(@PathVariable(name="noClient") Integer noClient) {
        Client client = null;
        try {
        	client = clientService.trouverClientParId(noClient);
        }catch(DataNotFound notFound) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Client : " + noClient + " non trouvé", null));
        }
        return ResponseEntity.ok(new ApiResponse(true, "ok", client));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<Client>> ajouterClient(@RequestBody ClientDTO client) {
        Client nouveauClient = clientService.ajouterClient(client);
        ApiResponse<Client> apiResponse = new ApiResponse(true, "ok", nouveauClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    
    @PutMapping("/{noClient}")
    public ResponseEntity<ApiResponse<Client>> modifierClient(@PathVariable(name="noClient") Integer noClient, @RequestBody ClientDTO clientDto) {
        Client client = clientService.modifierClient(noClient, clientDto);
        ApiResponse<Client> apiResponse = new ApiResponse(true, "ok", client);
        return ResponseEntity.ok(apiResponse);
    }


    @PatchMapping("/{noClient}/adresse")
    public ResponseEntity<ApiResponse<Client>> modifierAdresse(@PathVariable(name="noClient") Integer noClient, @RequestBody AdresseDTO adresseDto) {
        Client client = clientService.modifierAdresse(noClient, adresseDto);
        ApiResponse<Client> apiResponse = new ApiResponse(true, "ok", client);
        return ResponseEntity.ok(apiResponse);
    }


    @DeleteMapping("/{noClient}")
    public ResponseEntity<ApiResponse<?>> supprimerClient(@PathVariable(name="noClient") int noClient) {
        clientService.supprimerClient(noClient);
        ApiResponse<Client> apiResponse = new ApiResponse(true, "ok", null);
        return ResponseEntity.ok(null);
    }
}
