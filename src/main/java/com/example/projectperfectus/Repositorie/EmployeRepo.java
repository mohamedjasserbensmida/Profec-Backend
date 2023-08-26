package com.example.projectperfectus.Repositorie;


import com.example.projectperfectus.Entite.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface EmployeRepo extends JpaRepository<Employee, Long> {
}
