package com.charlyCorporation.CitiesService.service;

import com.charlyCorporation.CitiesService.dto.CitiesDTO;
import com.charlyCorporation.CitiesService.model.Cities;

import java.util.List;

public interface ICitiesService {

    public void saveCity (Cities city);

    public List<Cities> getAllCities();

    public Cities findById(Long id);

    public void deletCity(Long id);

    public CitiesDTO getCitiesAndHotels(Long id_ciudad);

    public List<Cities> saveVariasCities(List<Cities> city);
    
    public Cities getCitiesForNameAndCountry(String nombre, String pais);

    public CitiesDTO getForName(String nombre, String pais);

}
