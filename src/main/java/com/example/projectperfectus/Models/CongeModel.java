package com.example.projectperfectus.Models;

import com.example.projectperfectus.Entite.TypeConge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CongeModel {
    private LocalDate startDate;
    private LocalDate endDate;
    private TypeConge typeConge;
    private String EmployeeId;
}
