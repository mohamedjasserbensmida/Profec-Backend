package com.example.ProfectusBackend.repositories;

import com.example.ProfectusBackend.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DepartementRepo extends JpaRepository<Departement, Long> {
}
