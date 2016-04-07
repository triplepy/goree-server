package com.goree.api.service;


import com.goree.api.domain.Attendance;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AttendanceServiceTest extends TestWithDBUnit{

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MeetingService meetingService;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/attendance_test_setup.xml";
    }

    @Test
    public void mapMeetingAndAttendance(){
        // given
        Attendance expected = new Attendance();

        Meeting meeting = meetingService.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberService.findMemberById(3L);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.N);

        long expectedMemberId = expected.getMember().getId();
        long expectedMeetingId = expected.getMeeting().getId();


        // when
        attendanceService.mapMeetingAndAttendance(expectedMemberId, expectedMeetingId, expected.getStatus());
        Attendance actual = attendanceService.findAttendanceByMemberAndMeeting(member.getId(), meeting.getId());


        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMyAttendanceInfoIsN() {
        Attendance expected = new Attendance();

        Meeting meeting = meetingService.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberService.findMemberById(1L);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.N);

        Attendance actual = attendanceService.findAttendanceByMemberAndMeeting(member.getId(),meeting.getId());



        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getMyAttendanceInfoIsY() {
        Attendance expected = new Attendance();

        Meeting meeting = meetingService.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberService.findMemberAll().get(1);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.Y);

        Attendance actual = attendanceService.findAttendanceByMemberAndMeeting(member.getId(),meeting.getId());


        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getMyAttendanceInfoIsX() {
        Attendance expected = new Attendance();

        Meeting meeting = meetingService.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberService.findMemberById(3L);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.X);

        Attendance actual = attendanceService.findAttendanceByMemberAndMeeting(member.getId(),meeting.getId());


        Assert.assertEquals(expected,actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findAttandanceByMemberAndMeeting_notExistsMember_notExistsMeeting() {
        // given
        long memberNotExists = 994940L;
        long meetingNotExists = 123345L;

        // when
        Attendance attendance = attendanceService.findAttendanceByMemberAndMeeting(memberNotExists, meetingNotExists);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapMeetingAndAttendance_notExistsMember_notExistsMeeting() {
        // given
        long memberNotExists = 994940L;
        long meetingNotExists = 123345L;

        // when
        attendanceService.mapMeetingAndAttendance(memberNotExists, meetingNotExists, Attendance.Status.N);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mapMeetingAndAttendance_nullStatus() {
        long memberId = 1L;
        long meetingId = 1L;

        attendanceService.mapMeetingAndAttendance(memberId, meetingId, null);
    }
}
