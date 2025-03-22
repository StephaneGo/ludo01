package fr.eni.ludotheque.dal;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.ludotheque.bo.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

}
