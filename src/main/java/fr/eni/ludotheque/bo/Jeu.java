package fr.eni.ludotheque.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="JEUX")
public class Jeu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="no_jeu")
	private Integer noJeu;
	
	@Column( length=50, nullable=false)
	@NonNull
	private String titre;
	
	@Column(length=13, nullable=false, unique=true)
	@NonNull private String reference;
	
	@Column(nullable=true)
	private int ageMin;
	
	@Column( nullable=true)
	private String description;

	private int duree;
	
	@Column(nullable=false)
	@NonNull
	private float tarifJour;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "JEUX_GENRES", 
		joinColumns = @JoinColumn(name="no_jeu"),
		inverseJoinColumns = @JoinColumn(name="no_genre"))
	private List<Genre> genres = new ArrayList<>();
	
	public void addGenre(Genre g) {
		genres.add(g);
	}
}
