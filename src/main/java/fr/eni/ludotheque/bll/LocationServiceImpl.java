package fr.eni.ludotheque.bll;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Client;
import fr.eni.ludotheque.bo.Exemplaire;
import fr.eni.ludotheque.bo.Facture;
import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.ExemplaireRepository;
import fr.eni.ludotheque.dal.JeuRepository;
import fr.eni.ludotheque.dal.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
	@NonNull
	final private LocationRepository locationRepository;

	@NonNull
	final private JeuRepository jeuRepository;

	@NonNull
	final private ExemplaireRepository exemplaireRepository;

	
	@Override
	public void ajouterLocation(Integer noClient, String codebarre  ) {
		Exemplaire exemplaire = exemplaireRepository.findByCodebarreWithJeu(codebarre);
		Client client = new Client();
		client.setNoClient(noClient);
					
		Location location = new Location(LocalDateTime.now(),client, exemplaire );
		float tarifJour = jeuRepository.findTarifJourByNoJeu(exemplaire.getJeu().getNoJeu());
		location.setTarifJour(tarifJour);
		locationRepository.save(location);
		
	}

	@Override
	@Transactional
	public void retourExemplaires(List<String> codebarres) {
		Facture facture = new Facture(LocalDateTime.now());
		//facture
		Location location = null;
		float prix = 0;
		for(String codebarre : codebarres) {
			location = locationRepository.findLocationByCodebarreWithJeu(codebarre);
			location.setDateRetour(LocalDateTime.now());
			facture.addLocation(location);
			//TODO : save date retour 
			long nbJours = ChronoUnit.DAYS.between(location.getDateDebut(), location.getDateRetour());
			prix += (nbJours * location.getTarifJour());
		}
		facture.setPrix(prix);
		
	}

	@Override
	public void trouverLocationParExemplaireCodebarre(String codebarre) {
		// TODO Auto-generated method stub
		
	}

}
