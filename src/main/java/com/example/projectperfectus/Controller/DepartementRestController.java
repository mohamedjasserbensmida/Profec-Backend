package com.example.projectperfectus.Controller;

import com.example.projectperfectus.Entite.Departement;
import com.example.projectperfectus.Models.DepartementModel;
import com.example.projectperfectus.Service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class DepartementRestController {

    @Autowired
    DepartementService departementService;

    @PostMapping("/departements")
    void createDepartement(@RequestBody DepartementModel d){
        Departement depart = new Departement();
        depart.setDepartementName(d.getDepartementName());
        this.departementService.createDepartement(depart);
    }

    @GetMapping("/departements")
    List<Departement> getDepartements(){
        return this.departementService.findDepartments();
    }

    @GetMapping("departements/{id}")
    Departement getOneDepartement(@PathVariable long id){
     return this.departementService.findOneDepartment(id);
    }
}
