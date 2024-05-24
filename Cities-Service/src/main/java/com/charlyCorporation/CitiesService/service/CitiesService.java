package com.charlyCorporation.CitiesService.service;

import com.charlyCorporation.CitiesService.dto.CitiesDTO;
import com.charlyCorporation.CitiesService.dto.HotelsDTO;
import com.charlyCorporation.CitiesService.model.Cities;
import com.charlyCorporation.CitiesService.repository.ICitiesRepository;
import com.charlyCorporation.CitiesService.repository.IHotelsClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitiesService implements ICitiesService{

    @Autowired
    private ICitiesRepository repo;

    @Autowired
    private IHotelsClient client;


    @Override
    @CircuitBreaker(name="Hotels-service", fallbackMethod = "fallBackHotels")
    @Retry(name="Hoteles_service")
    public CitiesDTO getCitiesAndHotels(Long id_ciudad) {

        Cities city = this.findById(id_ciudad);
        List<HotelsDTO> dto = client.findHotelsInACity(id_ciudad);

        CitiesDTO c = new CitiesDTO();
        c.setId_ciudad(city.getId_ciudad());
        c.setNombre(city.getNombre());
        c.setContinente(city.getContinente());
        c.setPais(city.getPais());
        c.setEstado(city.getEstado());
        c.setListaHoteles(dto);

        //LLamada al metodo donde puede ocurrir la excetion
        //createException();

        return c;
    }

    public CitiesDTO fallBackHotels(Throwable throwable){
       return new CitiesDTO(9999999L,
                "fallido", "fallido","fallido","fallido",null);
    }

    public void createException(){
        throw  new IllegalArgumentException("Prueba Circuit breaker");
    }
    

    @Override
    public void saveCity(Cities city) {
        repo.save(city);
    }

    @Override
    public List<Cities> getAllCities() {
        List<Cities> lista = repo.findAll();
        return lista;
    }

    @Override
    public Cities findById(Long id) {
        Cities city = repo.findById(id).orElse(null);
        return city;
    }

    @Override
    public void deletCity(Long id) {
        repo.deleteById(id);

    }

    @Override
    public List<Cities> saveVariasCities(List<Cities> city) {
        List<Cities> listavarios = repo.saveAll(city);
        return listavarios;
    }

    @Override
    public Cities getCitiesForNameAndCountry(String nombre, String pais) {

        List<Cities> lista1 = this.getAllCities();
        for(Cities c:lista1){
            if (c.getNombre().equals(nombre)) {
                if(c.getPais().equals(pais)){
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    public CitiesDTO getForName(String nombre, String pais) {

        Cities c = this.getCitiesForNameAndCountry(nombre,pais);
        List<HotelsDTO> l = client.findHotelsInACity(c.getId_ciudad());

        CitiesDTO dto = new CitiesDTO();
        dto.setId_ciudad(c.getId_ciudad());
        dto.setNombre(c.getNombre());
        dto.setPais(c.getPais());
        dto.setContinente(c.getContinente());
        dto.setEstado(c.getEstado());
        dto.setListaHoteles(l);

        return dto;
    }


}
