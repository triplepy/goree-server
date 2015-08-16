package com.goree.api.service;


import com.goree.api.domain.MeetingNote;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MeetingNoteServiceTest extends TestWithDBUnit{
    @Autowired
    private MemberService memberService;

    @Autowired
    private MeetingNoteService meetingNoteService;

    @Autowired
    private MeetingService meetingService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/meeting_comment_test_setup.xml";
    }
    
    @Test
    public void writeMeetingNote() {
        long meetingId = 1;

        MeetingNote expected = new MeetingNote();
        expected.setMeeting(meetingService.findMeetingById(meetingId));
        expected.setWriter(memberService.findMemberAll().get(0));
        expected.setContent("writeMeetingNoteTest");
        MeetingNote actual = meetingNoteService.writeMeetingNote(expected);

        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getWriter().getId(), actual.getWriter().getId());
        Assert.assertEquals(expected.getMeeting().getId(), actual.getMeeting().getId());
        Assert.assertNotNull(actual.getId());
        Assert.assertNotNull(actual.getContent());
    }


    @Test
    public void findMeetingNoteById() {
        long meetingId = 1;
        long meetingNoteId = 1;

        MeetingNote expected = new MeetingNote();
        expected.setId(meetingNoteId);
        expected.setMeeting(meetingService.findMeetingById(meetingId));
        expected.setWriter(memberService.findMemberAll().get(0));
        expected.setContent("findMeetingNoteTest");
        MeetingNote actual = meetingNoteService.findMeetingNoteById(meetingNoteId);


        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getMeeting().getId(), actual.getMeeting().getId());
        Assert.assertEquals(expected.getMeeting().getTitle(), actual.getMeeting().getTitle());
        Assert.assertEquals(expected.getWriter().getId(), actual.getWriter().getId());
        Assert.assertEquals(expected.getWriter().getNickname(), actual.getWriter().getNickname());
        Assert.assertNotNull(actual.getCreatedDate());

    }

    @Test
    public void findLastMeetingNoteByMeetingId() {
        long meetingId = 1;
        long meetingNoteId = 2;

        MeetingNote expected = new MeetingNote();
        expected.setId(meetingNoteId);
        expected.setMeeting(meetingService.findMeetingById(meetingId));
        expected.setWriter(memberService.findMemberAll().get(0));
        expected.setContent("findLastMeetingNoteTest");
        MeetingNote actual = meetingNoteService.findLastMeetingNoteByMeetingId(meetingId);


        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getContent(), actual.getContent());
        Assert.assertEquals(expected.getMeeting().getId(), actual.getMeeting().getId());
        Assert.assertEquals(expected.getMeeting().getTitle(), actual.getMeeting().getTitle());
        Assert.assertEquals(expected.getWriter().getId(), actual.getWriter().getId());
        Assert.assertEquals(expected.getWriter().getNickname(), actual.getWriter().getNickname());
        Assert.assertNotNull(actual.getCreatedDate());


    }

}
