package fr.eni.ludotheque.bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="ADRESSES")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noAdresse;
	
	@Column(length = 100, nullable = false)
	@NonNull private String rue;
	
	@Column(length = 5, nullable = false)
	@NonNull private String codePostal;
	
	@Column(length = 100, nullable = false)
	@NonNull private String ville;
	
//	@OneToOne(mappedBy= "adresse")
//	private Client client;
//	
}
