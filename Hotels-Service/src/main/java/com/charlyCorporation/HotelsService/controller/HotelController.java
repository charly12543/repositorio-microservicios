package com.charlyCorporation.HotelsService.controller;


import com.charlyCorporation.HotelsService.model.Hotel;
import com.charlyCorporation.HotelsService.service.IHotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {


    @Autowired
    private IHotelService service;

    @Value("${server.port}")
    private int serverPort;


    @PostMapping("/save")
    public ResponseEntity<?> saveHotel(@Valid @RequestBody Hotel hotel,
                                                         BindingResult result){
        Map<String, String> errores = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors()
                    .forEach(er -> {
                        errores.put(er.getField(), "Error!, en el campo " + er.getField()
                        + " : " + er.getDefaultMessage());
                    });
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveHotel(hotel));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        System.out.println("Estoy en el puerto :" + serverPort);
        return ResponseEntity.ok(service.getALLHotel());
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Hotel> h = service.findById(id);
        return h.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id){
        Optional<Hotel> hotel = service.findById(id);
        if(hotel.isPresent()){
            service.deleteHotel(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id_ciudad}")
    public ResponseEntity<List<Hotel>> findHotelsInACity(@PathVariable Long id_ciudad){
        System.out.println("------- Estoy en el puerto: " + serverPort );
        return ResponseEntity.ok(service.findHotelsInACity(id_ciudad));
    }


    @PostMapping("/varios")
    public ResponseEntity<List<Hotel>> saveVarios(@RequestBody List<Hotel> h){
        return ResponseEntity.ok(service.saveAllHotels(h));
    }



}
