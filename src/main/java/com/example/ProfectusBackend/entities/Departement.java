package com.example.ProfectusBackend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

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
  Collection<User> users = new ArrayList<>();
}
