package com.charlyCorporation.HotelsService.repository;

import com.charlyCorporation.HotelsService.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h WHERE h.id_ciudad = :id_ciudad")
    List<Hotel> findHotelsInACity(Long id_ciudad);
}
