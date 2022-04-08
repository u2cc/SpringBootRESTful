package com.rest.repository;

import com.rest.entities.DiecastCar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiecastCarRepository extends CrudRepository<DiecastCar, Integer> {
}
