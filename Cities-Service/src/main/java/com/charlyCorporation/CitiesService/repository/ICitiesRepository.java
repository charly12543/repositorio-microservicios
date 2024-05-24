package com.charlyCorporation.CitiesService.repository;

import com.charlyCorporation.CitiesService.model.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICitiesRepository extends JpaRepository<Cities, Long> {
}
