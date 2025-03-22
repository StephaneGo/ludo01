package fr.eni.ludotheque.bll;

import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Location;
import fr.eni.ludotheque.dal.LocationRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService{
	@NonNull
	final private LocationRepository locationRepository;

	@Override
	public void ajouterLocation(Location location) {
		if(location.getNoLocation()!=null) {
			throw new IllegalStateException("no_location est non null : " + location.getNoLocation());
		}
		locationRepository.save(location);
		
	}

}
