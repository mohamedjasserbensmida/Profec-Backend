package com.example.ProfectusBackend.entities;

import lombok.Data;

@Data
public class changePwd {
    private String oldpassword;
    private String password;
    private String password2;
}
