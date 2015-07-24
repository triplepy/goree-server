package com.goree.api.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Meeting {
    private int id;
    private String title;
    private Group group;
    private Place place;
    private Member promoter;
    private Date date;
    private String description;
}
