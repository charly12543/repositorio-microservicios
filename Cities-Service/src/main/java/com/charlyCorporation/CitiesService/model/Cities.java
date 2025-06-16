package com.charlyCorporation.CitiesService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cities")
public class Cities {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ciudad;

    @NotBlank(message = "El campo nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El campo continente no puede estar vacio")
    private String continente;

    @NotBlank(message = "El campo pais no puede estar vacio")
    private String pais;

    @NotBlank(message = "El campo estado no puede estar vacio")
    private String estado;


}
