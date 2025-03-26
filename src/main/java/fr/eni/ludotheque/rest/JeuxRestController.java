package fr.eni.ludotheque.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.ludotheque.bll.JeuService;
import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dto.LocationDTO;

@RestController
@RequestMapping("/api/jeux")
public class JeuxRestController {
    private final JeuService jeuService;

    public JeuxRestController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Jeu>>> listeJeuxCatalogue(@RequestParam(name="titre")String titre){
    	if(titre==null || "".equals(titre.trim())) {
    		titre = "TOUS";
    	}
    	List<Jeu> jeux =  jeuService.listeJeuxCatalogue(titre);
        return ResponseEntity.ok(new ApiResponse(true, "ok", jeux));
    }



}
