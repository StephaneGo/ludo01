package fr.eni.ludotheque.bo;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "LOCATIONS")
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer noLocation;
	@Basic(optional = false)
	@NonNull private LocalDateTime dateDebut;
	@Basic(optional = true)
	@NonNull private LocalDateTime dateRetour;
	@Basic(optional = false)
	@NonNull private float tarifJour;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="no_client")
	@NonNull private Client client;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name="no_exemplaire")
	@NonNull private Exemplaire exemplaireLoue;
	

}
