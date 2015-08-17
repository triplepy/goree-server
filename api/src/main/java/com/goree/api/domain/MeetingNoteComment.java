package com.goree.api.domain;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MeetingNoteComment {
    private long id;
    private MeetingNote meetingNote;
    private Member writer;
    private String content;
    private Date createDate;

}
