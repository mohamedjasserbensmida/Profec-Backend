package com.example.projectperfectus.Entite;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departement {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id ;
  private String departementName ;
  @JsonBackReference
  @OneToMany(mappedBy = "departement")
  Collection<Employee> employees = new ArrayList<>();
}
