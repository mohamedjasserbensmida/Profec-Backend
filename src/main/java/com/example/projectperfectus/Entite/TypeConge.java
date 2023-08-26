package com.example.projectperfectus.Entite;

public enum TypeConge {
    PAYE("Paye"),
    NON_PAYE("Non paye"),
    MALADIE("Maladie");


    private String typeconge ;

    TypeConge (String typeconge){
        this.typeconge = typeconge;
    }
}
