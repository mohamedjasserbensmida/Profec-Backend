package com.example.ProfectusBackend.services;


import com.example.ProfectusBackend.entities.Departement;
import com.example.ProfectusBackend.entities.User;

import java.util.List;

public interface DepartementService {
    void createDepartement(Departement d);
    List<Departement> findDepartments();

    Departement findOneDepartment(long id);

    void deleteDepartement(Long id);

    Departement updateDepartement(Departement d);

    List<Departement> getDepartements();



}
