package com.example.ProfectusBackend.services;


import com.example.ProfectusBackend.entities.Departement;
import com.example.ProfectusBackend.entities.User;
import com.example.ProfectusBackend.repositories.DepartementRepo;
import com.example.ProfectusBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartementServiceImp implements DepartementService {

    @Autowired
    DepartementRepo departementRepo;
    @Override
    public void createDepartement(Departement d) {
        departementRepo.save(d);
    }

    @Override
    public List<Departement> findDepartments() {
        return departementRepo.findAll();
    }

    @Override
    public Departement findOneDepartment(long id) {
        return departementRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteDepartement(Long id) {
         departementRepo.deleteById(id);
    }

    @Override
    public Departement updateDepartement(Departement d) {
        return departementRepo.save(d);
    }

    @Override
    public List<Departement> getDepartements() {
        return departementRepo.findAll();

    }


}
