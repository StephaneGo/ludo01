package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ludotheque.bo.Facture;

public interface FactureRepository extends JpaRepository<Facture, Integer>{

}
