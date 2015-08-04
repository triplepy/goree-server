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
        Attendance expected = new Attendance();

        Meeting meeting = meetingService.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberService.findMemberAll().get(2);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.N);



        attendanceService.mapMeetingAndAttendance(expected.getMember().getId(), expected.getMeeting().getId(), expected.getStatus());

        Attendance actual = attendanceService.findAttendanceByMemberAndMeeting(member.getId(), meeting.getId());


        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMyAttendanceInfoIsN() {
        Attendance expected = new Attendance();

        Meeting meeting = meetingService.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberService.findMemberAll().get(0);
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

        Member member = memberService.findMemberAll().get(2);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.X);

        Attendance actual = attendanceService.findAttendanceByMemberAndMeeting(member.getId(),meeting.getId());


        Assert.assertEquals(expected,actual);
    }

}
