package com.example.SonedeReclamation.repositories;

import com.example.SonedeReclamation.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DepartementRepo extends JpaRepository<Departement, Long> {
}
