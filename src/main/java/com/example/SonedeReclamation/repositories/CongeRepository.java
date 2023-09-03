package com.example.SonedeReclamation.repositories;

import com.example.SonedeReclamation.entities.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conge, Long> {
    // Méthodes supplémentaires si nécessaire

    @Query(value = "select * from conges where user_id=?1",nativeQuery = true)
    List<Conge> getPrincipalReclamation(Long id);
}

