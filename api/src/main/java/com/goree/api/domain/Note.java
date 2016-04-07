package com.goree.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Note {
    private long id;
    private Group group;
    private Member noteWriter;
    private String content;
    private Date createDate;

    public boolean equals(Object otherObj) {
        if(!(otherObj instanceof Note))
            return false;

        Note other = (Note) otherObj;

        return getContent() == null ? other.getContent() == null : getContent().equals(other.getContent());

    }

}
