package com.rest.services;

import com.rest.entities.DiecastCar;
import com.rest.repository.DiecastCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class DiecastCarService {

    @Autowired
    private DiecastCarRepository diecastCarRepository;

    public List<DiecastCar> list(){
        return (List<DiecastCar>) diecastCarRepository.findAll();
    }

    public List<DiecastCar> findByBrands(Collection<String> brands){
        return diecastCarRepository.findCarByBrandList(brands);
    }

    public boolean ifPresent(DiecastCar diecastCar){
        if(null!=diecastCar.getId()) {
            return diecastCarRepository.existsById(diecastCar.getId());
        }else{
            return false;
        }
    }

    public void deleteById(long id){
        diecastCarRepository.deleteById(id);
    }

    public Optional<DiecastCar> findById(Long id){
        return diecastCarRepository.findById(id);
    }
    public void updateDiecastCar(DiecastCar diecastCar){
        diecastCarRepository.save(diecastCar);
    }
    public void saveDieCastCar(DiecastCar diecastCar) {diecastCarRepository.save(diecastCar);}

}
