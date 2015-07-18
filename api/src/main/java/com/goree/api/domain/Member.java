package com.goree.api.domain;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of={"id"})
public class Member {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private String nickname;
    private String facebook;
    private String twitter;
    private String kakaotalk;
    private String job;
    private int age;
    private Gender gender;
    private String phone;
    private List<Tag> tags;
    
    public enum Gender {
        M, F
    }
    
    
}
