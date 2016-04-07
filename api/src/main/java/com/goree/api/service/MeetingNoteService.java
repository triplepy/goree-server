package com.goree.api.service;


import com.goree.api.domain.MeetingNote;
import com.goree.api.mapper.MeetingNoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class MeetingNoteService {
    @Autowired
    private MeetingNoteMapper meetingNoteMapper;

    public MeetingNote writeMeetingNote(MeetingNote meetingNote) {
        checkArgument(meetingNote != null);
        requireNonNull(meetingNote.getMeeting());
        requireNonNull(meetingNote.getWriter());
        checkArgument(isNotBlank(meetingNote.getContent()));

        meetingNoteMapper.insertMeetingNote(meetingNote);
        return findLastMeetingNoteByMeetingId(meetingNote.getMeeting().getId());
    }

    public MeetingNote findMeetingNoteById(long meetingNoteId) {
        return meetingNoteMapper.selectMeetingNoteById(meetingNoteId);
    }

    public MeetingNote findLastMeetingNoteByMeetingId(long meetingId) {
        return meetingNoteMapper.selectLastMeetingNoteByMeetingId(meetingId);
    }
}
