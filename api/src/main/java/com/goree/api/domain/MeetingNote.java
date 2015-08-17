package com.goree.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MeetingNote {
    private long id;
    private Meeting meeting;
    private Member writer;
    private String content;
    private Date createdDate;
}
