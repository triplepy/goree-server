package com.goree.api.mapper;


import com.goree.api.domain.MeetingNote;

public interface MeetingNoteMapper {
    void insertMeetingNote(MeetingNote meetingNote);

    MeetingNote selectMeetingNoteById(long meetingNoteId);

    MeetingNote selectLastMeetingNoteByMeetingId(long meetingId);
}
