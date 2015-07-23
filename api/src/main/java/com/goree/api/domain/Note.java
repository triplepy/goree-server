package com.goree.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
public class Note {
    private int id;
    private Group group;
    private Member noteWriter;
    private String Content;
    private Date createDate;

    public boolean is (Note other) {
        return this.id == other.id;
    }

}
