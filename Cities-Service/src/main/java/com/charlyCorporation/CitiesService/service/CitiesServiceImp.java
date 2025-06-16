package com.charlyCorporation.CitiesService.service;

import com.charlyCorporation.CitiesService.dto.CitiesDTO;
import com.charlyCorporation.CitiesService.dto.HotelsDTO;
import com.charlyCorporation.CitiesService.model.Cities;
import com.charlyCorporation.CitiesService.repository.ICitiesRepository;
import com.charlyCorporation.CitiesService.client.IHotelsClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CitiesServiceImp implements ICitiesService{

    @Autowired
    private ICitiesRepository repo;

    @Autowired
    private IHotelsClient client;


    @Override
    @CircuitBreaker(name="Hotels-service", fallbackMethod = "fallBackHotels")
    @Retry(name="Hoteles_service")
    @Transactional
    public Optional<CitiesDTO> getCitiesAndHotels(Long id_ciudad) {

        Optional<Cities> city = this.findById(id_ciudad);
        List<HotelsDTO> dto = client.findHotelsInACity(id_ciudad);

        CitiesDTO c = new CitiesDTO();
        c.setId_ciudad(city.get().getId_ciudad());
        c.setNombre(city.get().getNombre());
        c.setContinente(city.get().getContinente());
        c.setPais(city.get().getPais());
        c.setEstado(city.get().getEstado());
        c.setListaHoteles(dto);

        //LLamada al metodo donde puede ocurrir la excetion
        //createException();

        return Optional.of(c);
}
    public CitiesDTO fallBackHotels(Throwable throwable){
       return new CitiesDTO(9999999L,
                "fallido", "fallido","fallido","fallido",null);
    }

    public void createException(){
        throw  new IllegalArgumentException("Prueba Circuit breaker");
    }
    

    @Override
    @Transactional
    public Cities saveCity(Cities city) {
        Cities c = repo.save(city);
        return c;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cities> getAllCities() {
        List<Cities> lista = repo.findAll();
        return lista;
    }

    @Override
    @Transactional
    public Optional<Cities> findById(Long id) {
        Optional<Cities> city = repo.findById(id);
        return city;
    }

    @Override
    @Transactional
    public void deleteCity(Long id) {
        repo.deleteById(id);

    }

    @Override
    @Transactional
    public List<Cities> saveVariasCities(List<Cities> city) {
        List<Cities> listavarios = repo.saveAll(city);
        return listavarios;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cities> getCitiesForNameAndCountry(String nombre, String pais) {

        List<Cities> lista1 = this.getAllCities();
        for(Cities c:lista1){
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                if(c.getPais().equalsIgnoreCase(pais)){
                    return Optional.of(c);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CitiesDTO> getForName(String nombre, String pais) {

        Optional<Cities> c = this.getCitiesForNameAndCountry(nombre,pais);
        List<HotelsDTO> l = client.findHotelsInACity(c.get().getId_ciudad());

        CitiesDTO dto = new CitiesDTO();
        dto.setId_ciudad(c.get().getId_ciudad());
        dto.setNombre(c.get().getNombre());
        dto.setPais(c.get().getPais());
        dto.setContinente(c.get().getContinente());
        dto.setEstado(c.get().getEstado());
        dto.setListaHoteles(l);

        return Optional.ofNullable(dto);
    }


}
