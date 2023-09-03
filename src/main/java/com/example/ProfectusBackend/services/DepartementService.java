package com.example.ProfectusBackend.services;


import com.example.ProfectusBackend.entities.Departement;

import java.util.List;

public interface DepartementService {
    void createDepartement(Departement d);
    List<Departement> findDepartments();

    Departement findOneDepartment(long id);
}
