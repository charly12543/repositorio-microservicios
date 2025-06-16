package com.charlyCorporation.CitiesService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitiesDTO {

    private Long id_ciudad;
    private String nombre;
    private String continente;
    private String pais;
    private String estado;
    private List<HotelsDTO> listaHoteles;
}
