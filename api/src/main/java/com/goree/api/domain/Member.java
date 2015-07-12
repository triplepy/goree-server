package com.goree.api.domain;

import lombok.Data;

@Data
public class Member {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private String facebook;
    private String twitter;
    private String kakaotalk;
    private int age;
    private Gender gender;
    private String phone;
    
    public enum Gender {
        M, F
    }
    
    
}
