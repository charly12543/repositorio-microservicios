package com.charlyCorporation.CitiesService.service;

import com.charlyCorporation.CitiesService.dto.CitiesDTO;
import com.charlyCorporation.CitiesService.model.Cities;

import java.util.List;
import java.util.Optional;

public interface ICitiesService {

     Cities saveCity (Cities city);
     List<Cities> getAllCities();
     Optional<Cities> findById(Long id);
     void deleteCity(Long id);

     Optional<CitiesDTO> getCitiesAndHotels(Long id_ciudad);
     List<Cities> saveVariasCities(List<Cities> city);
     Optional<Cities> getCitiesForNameAndCountry(String nombre, String pais);
     Optional<CitiesDTO> getForName(String nombre, String pais);

}
