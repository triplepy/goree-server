package com.goree.api.domain;

import lombok.*;

import java.util.Date;


@Getter
@Setter
@ToString
public class NoteComment {
    private long id;
    private Note note;
    private Member writer;
    private String content;
    private Date createDate;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof NoteComment))
            return false;

        NoteComment noteComment = (NoteComment) other;

        return noteComment.getId() == getId() &&
               noteComment.getContent() == null ? getContent() == null : noteComment.getContent().equals(getContent());

    }
}
