package com.goree.api.mapper;


import com.goree.api.domain.MeetingNoteComment;

import java.util.List;

public interface MeetingNoteCommentMapper {

    void insertMeetingNoteComment(MeetingNoteComment meetingNoteComment);

    MeetingNoteComment selectMeetingNoteCommentLastOneByMeetingNoteId(long meetingNoteId);

    List<MeetingNoteComment> selectMeetingNoteCommentsByMeetingNoteId(long meetingNoteId);
}
