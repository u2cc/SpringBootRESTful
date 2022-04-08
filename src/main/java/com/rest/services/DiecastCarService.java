package com.rest.services;

import com.rest.entities.DiecastCar;
import com.rest.repository.DiecastCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Component
public class DiecastCarService {

    @Autowired
    private DiecastCarRepository diecastCarRepository;

    public List<DiecastCar> list(){
        return (List<DiecastCar>) diecastCarRepository.findAll();
    }
}
