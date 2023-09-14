package com.example.ProfectusBackend.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Departement {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id ;
  private String departementName ;
  @JsonBackReference
  @OneToMany(mappedBy = "departement")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  Collection<User> users = new ArrayList<>();

}
