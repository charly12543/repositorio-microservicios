package com.charlyCorporation.HotelsService.service;


import com.charlyCorporation.HotelsService.model.Hotel;
import com.charlyCorporation.HotelsService.repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService{
    @Override
    public List<Hotel> saveAllHotels(List<Hotel>  hotel) {
        List<Hotel> lis = repo.saveAll(hotel);
        return lis;
    }

    @Override
    public List<Hotel> findHotelsInACity(Long id_ciudad) {
        List<Hotel> l = repo.findHotelsInACity(id_ciudad);
        return l;
    }

    @Autowired
    private IHotelRepository repo;



    @Override
    public void saveHotel(Hotel hotel) {
        repo.save(hotel);

    }

    @Override
    public List<Hotel> getALLHotel() {
        List<Hotel> lista = repo.findAll();
        return lista;
    }

    @Override
    public void deleteHotel(Long id) {
        repo.deleteById(id);

    }

    @Override
    public Hotel findById(Long id) {
        Hotel h = repo.findById(id).orElse(null);
        return h;
    }
}
