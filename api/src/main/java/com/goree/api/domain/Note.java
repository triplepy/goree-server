package com.goree.api.domain;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Note {
    private int id;
    private Group group;
    private Member noteWriter;
    private String content;
    private Date createDate;

    public boolean is (Note other) {
        return this.id == other.id;
    }

//    public boolean equals(Object other) {
//        if (! (other instanceof Note))
//            return false;
//        
//        Note note = (Note) other;
//        return group.equals(note.getGroup())
//                && noteWriter.equals(note.getNoteWriter())
//                && content.equals(note.getContent())
//                && createDate.equals(note.getCreateDate());
//    }
}
