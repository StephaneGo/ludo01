package fr.eni.ludotheque.bo;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Exemplaire {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noExemplaire;
	
	@Column(length=13, nullable = false)
	@NonNull private String codebarre;
	
	@Basic(optional = false)
	@NonNull private boolean louable;
	
	@ManyToOne
	@JoinColumn(name="no_jeu", referencedColumnName = "no_jeu")
	private Jeu jeu;
	
}
