package com.example.projectperfectus.Repositorie;

import com.example.projectperfectus.Entite.Conge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CongeRepo extends JpaRepository<Conge, Long> {
}
