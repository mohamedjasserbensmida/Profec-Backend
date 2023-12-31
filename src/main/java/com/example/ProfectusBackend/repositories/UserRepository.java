package com.example.ProfectusBackend.repositories;

import com.example.ProfectusBackend.entities.Role;
import com.example.ProfectusBackend.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Page<User> findAll(Specification<User> spec, Pageable pageable);
    List<User> findAll(Specification<User> spec, Sort sort);
    Optional<User> findByEmailUser(String email);

    List<User> findByRole(Role role);


}
