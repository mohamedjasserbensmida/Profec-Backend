package com.example.ProfectusBackend.controllers;

import com.example.ProfectusBackend.entities.Conge;
import com.example.ProfectusBackend.services.CongeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conges")
public class CongeController {
    private CongeServiceImpl congeService;

    @Autowired
    public CongeController(CongeServiceImpl congeService) {
        this.congeService = congeService;
    }


    @GetMapping("/getprincipalconge/{id}")
    public List<Conge> getPrincipalConges(@PathVariable(value = "id") Long id ){
        return congeService.getPrincipalConges(id);
    }

    @PostMapping("/addNewConge/{id}")
    public String addNewConge(@RequestBody Conge conge, @PathVariable ( value = "id") Long id){
        return congeService.addNewConge(conge,id);
    }



    @GetMapping("/getAll")
    public List<Conge> getAllConges() {
        return congeService.getAllConges();
    }

    @GetMapping("/{id}")
    public Conge getCongeById(@PathVariable Long id) {
        return congeService.getCongeById(id);
    }

    @PostMapping
    public Conge createConge(@RequestBody Conge conge) {
        return congeService.createConge(conge);
    }

    @PutMapping("/updateConge")
    public String updateConge(@RequestBody Conge conge) {
        return congeService.updateConge(conge);
    }

    @DeleteMapping("/deleteConge/{id}")
    public String deleteConge(@PathVariable (value = "id") Long id) {
        return congeService.deleteConge(id);
    }
}

