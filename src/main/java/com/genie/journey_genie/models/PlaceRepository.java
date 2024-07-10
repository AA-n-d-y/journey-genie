// Java file for the Place repository

package com.genie.journey_genie.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer>{
    List<Place> findByPlaceNameAndLocation(String placeName, String location);
    List<Place> findByPlaceName(String placeName);
}
