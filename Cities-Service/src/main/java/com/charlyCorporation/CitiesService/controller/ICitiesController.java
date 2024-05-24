package com.charlyCorporation.CitiesService.controller;

import com.charlyCorporation.CitiesService.dto.CitiesDTO;
import com.charlyCorporation.CitiesService.model.Cities;
import com.charlyCorporation.CitiesService.service.ICitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class ICitiesController {

    @Autowired
    private ICitiesService ser;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/list")
    public List<Cities> getAllCities(){
        return ser.getAllCities();
    }

    @PostMapping("/save")
    public String saveCities(@RequestBody Cities city){
        ser.saveCity(city);
        return "Registro guardado";
    }

    @GetMapping("/hotel/{id_ciudad}")
   public CitiesDTO getALL(@PathVariable Long id_ciudad){
        return ser.getCitiesAndHotels(id_ciudad);
    }


    @PostMapping("/varios")
    public String saveVarios(@RequestBody List<Cities> c){
        System.out.println("Estamos en el puerto :" + serverPort);
        ser.saveVariasCities(c);
        return "Regstros guardados";
    }

//    @GetMapping("/{nombre}/{pais}")
//    public Cities getCity(@PathVariable String nombre,
//                          @PathVariable String pais){
//        return ser.getCitiesForNameAndCountry(nombre,pais);
//    }

    @PostMapping("/saveAll")
    public String saveAllCities(@RequestBody List<Cities> city){
        ser.saveVariasCities(city);
        return "Registro guardado con exito";
    }




    @GetMapping("/{nombre}/{pais}")
    public CitiesDTO getCity(@PathVariable String nombre,
                          @PathVariable String pais){
        return ser.getForName(nombre,pais);
    }

}
