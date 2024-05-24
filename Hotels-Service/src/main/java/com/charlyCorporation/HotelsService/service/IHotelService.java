package com.charlyCorporation.HotelsService.service;

import com.charlyCorporation.HotelsService.model.Hotel;

import java.util.List;

public interface IHotelService {


    public void saveHotel(Hotel hotel);

    public List<Hotel> getALLHotel();

    public void deleteHotel(Long id);

   public Hotel findById(Long id);

   public List<Hotel> saveAllHotels(List<Hotel> hotel);

   public List<Hotel> findHotelsInACity(Long id_ciudad);
}
