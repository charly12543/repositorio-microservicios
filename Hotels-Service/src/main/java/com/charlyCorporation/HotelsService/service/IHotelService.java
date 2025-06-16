package com.charlyCorporation.HotelsService.service;

import com.charlyCorporation.HotelsService.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface IHotelService {

    Hotel saveHotel(Hotel hotel);
    List<Hotel> getALLHotel();
    void deleteHotel(Long id);
    Optional<Hotel> findById(Long id);

    List<Hotel> saveAllHotels(List<Hotel> hotel);
    List<Hotel> findHotelsInACity(Long id_ciudad);
}
