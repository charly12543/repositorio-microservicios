package com.charlyCorporation.HotelsService.controller;


import com.charlyCorporation.HotelsService.model.Hotel;
import com.charlyCorporation.HotelsService.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    private IHotelService ser;


    @Value("${server.port}")
    private int serverPort;



    @PostMapping("/save")
    public String saveHotel(@RequestBody Hotel hotel){
        ser.saveHotel(hotel);
        return "Registro guardado";
    }

    @GetMapping("/list")
    public List<Hotel> getAllHotel(){

        System.out.println("Estoy en el puerto :" + serverPort);
        return ser.getALLHotel();
    }

    @GetMapping("/find/{id}")
    public Hotel findById(@PathVariable Long id){
        Hotel h = ser.findById(id);
        return h;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id){
        ser.deleteHotel(id);
        return "Registro eliminado";
    }

    @GetMapping("/{id_ciudad}")
    public List<Hotel> findHotelsInACity(@PathVariable Long id_ciudad){
        System.out.println("------- Estoy en el puerto: " + serverPort );
        return ser.findHotelsInACity(id_ciudad);

    }
    @PostMapping("/varios")
    public List<Hotel> saveVarios(@RequestBody List<Hotel> h){
        return ser.saveAllHotels(h);
    }



}
