package com.goree.api.service;


import com.goree.api.domain.MeetingNoteComment;
import com.goree.api.mapper.MeetingNoteCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.*;
import static java.util.Objects.*;
import static org.apache.commons.lang3.StringUtils.*;

@Service
public class MeetingNoteCommentService {
    @Autowired
    private MeetingNoteCommentMapper meetingNoteCommentMapper;


    public MeetingNoteComment writeMeetingNoteComment(MeetingNoteComment meetingNoteComment) {
        checkArgument(meetingNoteComment != null);
        requireNonNull(meetingNoteComment.getMeetingNote(), "Meeting note must not be null");
        requireNonNull(meetingNoteComment.getWriter(), "Writer must be not null");
        checkArgument(isNotBlank(meetingNoteComment.getContent()));

        meetingNoteCommentMapper.insertMeetingNoteComment(meetingNoteComment);
        return findMeetingNoteCommentLastOneByMeetingNoteId(meetingNoteComment.getMeetingNote().getId());
    }

    public MeetingNoteComment findMeetingNoteCommentLastOneByMeetingNoteId(long meetingNoteId) {
        return meetingNoteCommentMapper.selectMeetingNoteCommentLastOneByMeetingNoteId(meetingNoteId);
    }

    public List<MeetingNoteComment> findMeetingNoteCommentsByMeetingNoteId(long meetingNoteId) {
        return meetingNoteCommentMapper.selectMeetingNoteCommentsByMeetingNoteId(meetingNoteId);
    }
}
