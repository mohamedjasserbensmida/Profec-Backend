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


    public List<Conge> getPrincipalConges(Long id){
        return congeRepository.getPrincipalConge(id);
    }


    public List<Conge> getAllConges() {
        return congeRepository.findAll();
    }

    public Conge getCongeById(Long id) {
        return congeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réclamation non trouvée"));
    }

    public Conge createConge(Conge conge) {
        return congeRepository.save(conge);
    }

    public String updateConge(Conge conge) {
        Conge existingConge = congeRepository.findById(conge.getId()).get();
        existingConge.setTypeConge(conge.getTypeConge());
        existingConge.setDescription(conge.getDescription());
         congeRepository.save(existingConge);
         return "Conge Updated";
    }

    public String deleteConge(Long id) {
        Conge conge = getCongeById(id);
        congeRepository.delete(conge);
        return "Conge Deleted";
    }


    public String addNewConge(Conge conge, Long id){
        User user=userRepository.findById(id).get();
        conge.setUser(user);
        conge.setEmail(user.getEmailUser());
        congeRepository.save(conge);
        return "Conge Saved";
    }

}

