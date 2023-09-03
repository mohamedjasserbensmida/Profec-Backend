package com.example.SonedeReclamation.services;


import com.example.SonedeReclamation.entities.Departement;

import java.util.List;

public interface DepartementService {
    void createDepartement(Departement d);
    List<Departement> findDepartments();

    Departement findOneDepartment(long id);
}
