package com.rest.services;

import com.rest.Util;
import com.rest.entities.DiecastCar;
import com.rest.entities.DiecastCarAudit;
import com.rest.exception.DiecastCarNotFoundException;
import com.rest.repository.DiecastCarAuditRepository;
import com.rest.repository.DiecastCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class DiecastCarService {

    private static final String DELETE = "D";
    private static final String INSERT = "I";

    @Autowired
    private DiecastCarRepository diecastCarRepository;
    @Autowired
    private DiecastCarAuditRepository diecastCarAuditRepository;

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

    @Transactional
    public void deleteById(long id){
        DiecastCar diecastCar = diecastCarRepository.findById(id).orElseThrow(DiecastCarNotFoundException::new);
        diecastCarRepository.deleteById(id);
        DiecastCarAudit diecastCarAudit = Util.toDiecastCarAudit(diecastCar);
        diecastCarAudit.setAction(DELETE);
        diecastCarAuditRepository.save(diecastCarAudit);
    }

    public Optional<DiecastCar> findById(Long id){
        return diecastCarRepository.findById(id);
    }
    public void updateDiecastCar(DiecastCar diecastCar){
        diecastCarRepository.save(diecastCar);
    }

    @Transactional
    public void saveDieCastCar(DiecastCar diecastCar) {
        diecastCarRepository.save(diecastCar);
        DiecastCarAudit diecastCarAudit = Util.toDiecastCarAudit(diecastCar);
        diecastCarAudit.setAction(INSERT);
        diecastCarAuditRepository.save(diecastCarAudit);
    }

}
