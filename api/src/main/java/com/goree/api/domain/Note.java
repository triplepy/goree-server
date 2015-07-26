package com.goree.api.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of={"id", "content"})
public class Note {
    private int id;
    private Group group;
    private Member noteWriter;
    private String content;
    private Date createDate;
}
