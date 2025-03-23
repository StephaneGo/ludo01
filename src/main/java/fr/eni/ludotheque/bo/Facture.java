package fr.eni.ludotheque.bo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name="FACTURES")
public class Facture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noFacture;
	
	@NonNull
	@Basic(optional = true)
	private LocalDateTime datePaiement;
	
	@OneToMany
	@JoinColumn(name="no_facture")
	private List<Location> locations;
	
	
	//@Transient // attribut calculable  
	private float prix;
	
	public void addLocation(Location location) {
		this.locations.add(location);
	}
}
