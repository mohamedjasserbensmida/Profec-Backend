package com.example.ProfectusBackend.services;

import com.example.ProfectusBackend.entities.Conge;
import com.example.ProfectusBackend.entities.User;
import com.example.ProfectusBackend.repositories.CongeRepository;
import com.example.ProfectusBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongeServiceImpl {
    private CongeRepository congeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public CongeServiceImpl(CongeRepository congeRepository) {
        this.congeRepository = congeRepository;
    }


    public List<Conge> getPrincipalReclamations(Long id){
        return congeRepository.getPrincipalReclamation(id);
    }


    public List<Conge> getAllReclamations() {
        return congeRepository.findAll();
    }

    public Conge getReclamationById(Long id) {
        return congeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
    }

    public Conge createReclamation(Conge conge) {
        return congeRepository.save(conge);
    }

    public String updateReclamation(Conge conge) {
        Conge existingConge = congeRepository.findById(conge.getId()).get();
        existingConge.setTypeConge(conge.getTypeConge());
        existingConge.setDescription(conge.getDescription());
         congeRepository.save(existingConge);
         return "Reclamation Updated";
    }

    public String deleteReclamation(Long id) {
        Conge conge = getReclamationById(id);
        congeRepository.delete(conge);
        return "Reclamation Deleted";
    }


    public String addNewReclamation(Conge conge, Long id){
        User user=userRepository.findById(id).get();
        conge.setUser(user);
        conge.setEmail(user.getEmailUser());
        congeRepository.save(conge);
        return "Reclamation Saved";
    }

}

