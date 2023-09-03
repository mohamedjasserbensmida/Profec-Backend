package com.example.ProfectusBackend.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "conges")
    public class Conge {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Enumerated(EnumType.STRING)
        private TypeConge typeConge;
        private String email;
        private String description;
        @Column(name = "date_creation")
        private Date dateCreation = new Date(System.currentTimeMillis());
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private User user;


    }


