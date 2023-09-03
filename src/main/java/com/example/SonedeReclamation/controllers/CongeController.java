package com.example.SonedeReclamation.controllers;

import com.example.SonedeReclamation.entities.Conge;
import com.example.SonedeReclamation.services.CongeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conges")
public class CongeController {
    private CongeServiceImpl reclamationService;

    @Autowired
    public CongeController(CongeServiceImpl reclamationService) {
        this.reclamationService = reclamationService;
    }


    @GetMapping("/getprincipalconge/{id}")
    public List<Conge> getPrincipalConges(@PathVariable(value = "id") Long id ){
        return reclamationService.getPrincipalReclamations(id);
    }

    @PostMapping("/addNewConge/{id}")
    public String addNewReclamation(@RequestBody Conge conge, @PathVariable ( value = "id") Long id){
        return reclamationService.addNewReclamation(conge,id);
    }



    @GetMapping("/getAll")
    public List<Conge> getAllReclamations() {
        return reclamationService.getAllReclamations();
    }

    @GetMapping("/{id}")
    public Conge getReclamationById(@PathVariable Long id) {
        return reclamationService.getReclamationById(id);
    }

    @PostMapping
    public Conge createReclamation(@RequestBody Conge conge) {
        return reclamationService.createReclamation(conge);
    }

    @PutMapping("/updateConge")
    public String updateReclamation(@RequestBody Conge conge) {
        return reclamationService.updateReclamation(conge);
    }

    @DeleteMapping("/deleteConge/{id}")
    public String deleteReclamation(@PathVariable (value = "id") Long id) {
        return reclamationService.deleteReclamation(id);
    }
}

