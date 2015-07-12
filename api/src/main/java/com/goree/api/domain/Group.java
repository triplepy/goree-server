package com.goree.api.domain;

import java.util.List;

import lombok.Data;

@Data
public class Group {
    private int id;
    private Member leader;
    private String name;
    private String description;
    private List<Member> members;
    
}
