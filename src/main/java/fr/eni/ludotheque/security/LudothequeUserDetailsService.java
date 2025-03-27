package fr.eni.ludotheque.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Role;
import fr.eni.ludotheque.bo.Utilisateur;
import fr.eni.ludotheque.dal.UtilisateurRepository;

@Service
public class LudothequeUserDetailsService implements UserDetailsService {

	private UtilisateurRepository utilisateurRepository;

	public LudothequeUserDetailsService(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Utilisateur utilisateur = utilisateurRepository.findByLogin(username);
		if(utilisateur ==null) {
			throw new UsernameNotFoundException(username);
		}
	
		UserBuilder userBuilder = User.builder();
		userBuilder.username(username).password(utilisateur.getPassword());
		String[] tabRoles = utilisateur.getRoles().stream()
				.map(role->role.getLibelle())
				.toArray(String[]::new);
		userBuilder.roles(tabRoles);

		return userBuilder.build();
	}

}
