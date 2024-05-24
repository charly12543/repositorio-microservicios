package com.charlyCorporation.CitiesService.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cities {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ciudad;
    private String nombre;
    private String continente;
    private String pais;
    private String estado;


//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> listaHoteles;
}
