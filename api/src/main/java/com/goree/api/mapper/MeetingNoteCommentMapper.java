package com.goree.api.mapper;


import com.goree.api.domain.MeetingNoteComment;

public interface MeetingNoteCommentMapper {

    void insertMeetingNoteComment(MeetingNoteComment meetingNoteComment);

    MeetingNoteComment selectMeetingNoteCommentLastOneByMeetingNoteId(long meetingNoteId);
}
