package fr.eni.ludotheque.bo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="UTILISATEURS")
public class Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noUtilisateur;
	@NonNull private String login;
	@NonNull private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UTILISATEURS_ROLES", 
		joinColumns = @JoinColumn(name="no_utilisateur"),
		inverseJoinColumns = @JoinColumn(name="no_role"))
	private List<Role> roles;
	
}
