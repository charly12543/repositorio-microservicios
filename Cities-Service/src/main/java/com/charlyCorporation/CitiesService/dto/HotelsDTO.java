package com.charlyCorporation.CitiesService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelsDTO {

    private Long id;
    private String nombre;
    private int cant_estrellas;
    private Long id_ciudad;
}
