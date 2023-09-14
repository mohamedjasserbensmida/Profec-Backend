package com.example.ProfectusBackend.controllers;


import com.example.ProfectusBackend.Model.DepartementModel;
import com.example.ProfectusBackend.entities.Departement;
import com.example.ProfectusBackend.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DepartementRestController {

    @Autowired
    DepartementService departementService;

    @PostMapping("/departements")
    void createDepartement(@RequestBody DepartementModel d){
        Departement depart = new Departement();
        depart.setDepartementName(d.getDepartementName());
        this.departementService.createDepartement(depart);
    }
    @DeleteMapping("departements/{id}")
    public void deleteDepartements(@PathVariable (value = "id") Long id){departementService.deleteDepartement(id);
    }
    @PostMapping("updateDepartements")
    public Departement updateClient(Departement d){return  departementService.updateDepartement(d); }

    @GetMapping("/departements")
    List<Departement> getDepartements(){
        return this.departementService.findDepartments();
    }

    @GetMapping("departements/{id}")
    Departement getOneDepartement(@PathVariable long id){
     return this.departementService.findOneDepartment(id);
    }
}
    