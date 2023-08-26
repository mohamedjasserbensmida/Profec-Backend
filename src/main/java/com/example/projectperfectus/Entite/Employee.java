package com.example.projectperfectus.Entite;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeePhone;
    @ManyToOne
    Departement departement;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    List<Conge> conges = new ArrayList<>();
}
