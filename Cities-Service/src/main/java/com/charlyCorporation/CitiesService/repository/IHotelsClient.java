package com.charlyCorporation.CitiesService.repository;


import com.charlyCorporation.CitiesService.dto.HotelsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Hotels-service")
public interface IHotelsClient {


    @GetMapping("/hotel/{id_ciudad}")
    public List<HotelsDTO> findHotelsInACity(@PathVariable Long id_ciudad);

}