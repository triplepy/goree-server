package com.goree.api.service;


import com.goree.api.domain.MeetingNoteComment;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class MeetingNoteCommentServiceTest extends TestWithDBUnit{
    @Autowired
    private MemberService memberService;

    @Autowired
    private MeetingNoteService meetingNoteService;

    @Autowired
    private MeetingNoteCommentService meetingNoteCommentService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/meeting_note_comment_test_setup.xml";
    }

    @Test
    public void writeMeetingNoteComment() {
        long meetingNoteId = 1;

        MeetingNoteComment expected = new MeetingNoteComment();
        expected.setContent("writeMeetingNoteCommentTest");
        expected.setMeetingNote(meetingNoteService.findMeetingNoteById(meetingNoteId));
        expected.setWriter(memberService.findMemberAll().get(0));

        MeetingNoteComment actual = meetingNoteCommentService.writeMeetingNoteComment(expected);



        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getMeetingNote().getId(), actual.getMeetingNote().getId());
        Assert.assertEquals(expected.getWriter().getId(), actual.getWriter().getId());
        Assert.assertNotNull(actual.getCreateDate());
    }

    @Test
    public void findMeetingNoteCommentsByMeetingNoteId() {
        long meetingNoteId = 1;
        int expectedSize = 2;
        List<MeetingNoteComment> actual = meetingNoteCommentService.findMeetingNoteCommentsByMeetingNoteId(meetingNoteId);

        assertThat(actual, is(notNullValue()));
        assertThat(actual.size(), is(expectedSize));
        assertThat(actual.get(0).getMeetingNote().getId(), is(1L));
        assertThat(actual.get(1).getMeetingNote().getId(), is(1L));

        assertThat(actual.get(0).getContent(), is("findMeetingNoteCommentsByMeetingNoteId1"));
        assertThat(actual.get(0).getWriter().getId(),is(2L));


        assertThat(actual.get(1).getContent(), is("findMeetingNoteCommentsByMeetingNoteId0"));
        assertThat(actual.get(1).getWriter().getId(),is(1L));
    }

}
