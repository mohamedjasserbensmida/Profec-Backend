package com.example.ProfectusBackend.interfaces;

import com.example.ProfectusBackend.entities.Departement;
import com.example.ProfectusBackend.entities.User;
import com.example.ProfectusBackend.entities.changePwd;
import com.example.ProfectusBackend.utils.PagingResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

public interface IUserService {
    List<User> retrieveAllUsers();

    User retrieveUser(Long id);

    User updateUser(User user);

    User addUser(User user);

    void removeUser(Long id);

    PagingResponse get3(Specification<User> spec, HttpHeaders headers, Sort sort);

    List<User> getAllUsers();

    String deleteUser(Long id);

    User getPrincipal(Long id);

    String editPrincipal(Long id, User user);

    String changepassword(Long id, changePwd pwd);


    List<User> findEmployees();


    long calculateDaysSinceAccountCreationForEmployee(Long userId);


}


