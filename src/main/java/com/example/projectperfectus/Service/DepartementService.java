package com.example.projectperfectus.Service;

import com.example.projectperfectus.Entite.Departement;
import com.example.projectperfectus.Entite.Employee;

import java.util.List;

public interface DepartementService {
    void createDepartement(Departement d);
    List<Departement> findDepartments();

    Departement findOneDepartment(long id);
}
