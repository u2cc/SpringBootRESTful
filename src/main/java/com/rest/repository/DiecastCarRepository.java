package com.rest.repository;

import com.rest.entities.DiecastCar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DiecastCarRepository extends CrudRepository<DiecastCar, Long> {

    //using JPQL to achieve abstraction and database independence
    @Query(value = "SELECT d FROM DiecastCar d WHERE d.brand IN :brands ")
    List<DiecastCar> findCarByBrandList(@Param("brands") Collection<String> brands);
}
