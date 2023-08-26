package com.example.projectperfectus.Service;


import com.example.projectperfectus.Entite.Departement;
import com.example.projectperfectus.Repositorie.DepartementRepo;
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
}