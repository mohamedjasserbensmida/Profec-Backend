package com.example.projectperfectus.Service;

import com.example.projectperfectus.Entite.Conge;
import com.example.projectperfectus.Entite.Departement;

import java.util.List;

public interface CongeService {

    void createConge(Conge c);
    List<Conge> findConge();

    Conge findOneConge(long id);
}
