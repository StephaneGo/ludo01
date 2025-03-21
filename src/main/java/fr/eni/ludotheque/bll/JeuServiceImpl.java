package fr.eni.ludotheque.bll;

import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Jeu;
import fr.eni.ludotheque.dal.JeuRepository;
import fr.eni.ludotheque.exceptions.DataNotFound;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class JeuServiceImpl implements JeuService{
	@NonNull
	private JeuRepository jeuRepository;
	
	
	@Override
	public void ajouterJeu(Jeu jeu) {
		
		jeuRepository.save(jeu);
		
		
		
	}


	@Override
	public Jeu trouverJeuParNoJeu(Integer noJeu) {
		Optional<Jeu> optJeu = jeuRepository.findById(noJeu);
		
		if(optJeu.isEmpty()) {
			throw new DataNotFound("Jeu", noJeu);
		}
		return optJeu.get();
		
	}

	

}
