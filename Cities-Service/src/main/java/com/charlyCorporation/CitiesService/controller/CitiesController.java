package com.charlyCorporation.CitiesService.controller;

import com.charlyCorporation.CitiesService.dto.CitiesDTO;
import com.charlyCorporation.CitiesService.model.Cities;
import com.charlyCorporation.CitiesService.service.ICitiesService;
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
@RequestMapping("/city")
public class CitiesController {

    @Autowired
    private ICitiesService service;

    @Value("${server.port}")
    private int serverPort;


    @GetMapping("/list")
    public ResponseEntity<List<Cities>> getAllCities(){
        return ResponseEntity.ok(
                service.getAllCities());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCities(@Valid @RequestBody Cities city, BindingResult result){
        Map<String, String> errores = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors()
                    .forEach(er -> {
                        errores.put(er.getField(), "Error!, en el campo " + er.getField() +
                                " : " + er.getDefaultMessage());
                    });
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCity(city));
    }

    @GetMapping("/hotel/{id_ciudad}")
   public ResponseEntity<?> getALL(@PathVariable Long id_ciudad){
        Optional<CitiesDTO> dto = service.getCitiesAndHotels(id_ciudad);
        if(dto.isPresent()){
            dto.map(ResponseEntity::ok);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/varios")
    public ResponseEntity<?> saveVarios(@RequestBody List<Cities> c){
        System.out.println("Estamos en el puerto :" + serverPort);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveVariasCities(c));
    }


    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAllCities(@RequestBody List<Cities> city){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveVariasCities(city));
    }


    @GetMapping("/{nombre}/{pais}")
    public ResponseEntity<Optional<CitiesDTO>> getCity(@PathVariable String nombre,
                                                       @PathVariable String pais){
        Optional<CitiesDTO> dto = service.getForName(nombre,pais);
        if(dto.isPresent()) {
            return ResponseEntity.ok(service.getForName(nombre,pais));
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteForId(@PathVariable Long id){
        Optional<Cities> c = service.findById(id);
        if(c.isPresent()){
            service.deleteCity(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
