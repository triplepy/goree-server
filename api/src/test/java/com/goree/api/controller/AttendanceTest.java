package com.goree.api.controller;


import com.goree.api.domain.Attendance;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AttendanceTest extends TestWithDBUnit{

    @Autowired
    private AttendanceController attendanceController;

    @Autowired
    private MemberController memberController;

    @Autowired
    private MeetingController meetingController;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/attendance_test_setup.xml";
    }

    @Test
    public void mapMeetingAndAttendance(){
        Attendance expected = new Attendance();

        Meeting meeting = meetingController.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberController.findMemberAll().get(2);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.N);



        attendanceController.mapMeetingAndAttendance(expected.getMember().getId(),expected.getMeeting().getId(), expected.getStatus());

        Attendance actual = attendanceController.findAttendanceByMemberAndMeeting(member.getId(), meeting.getId());


        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getMyAttendanceInfoIsN() {
        Attendance expected = new Attendance();

        Meeting meeting = meetingController.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberController.findMemberAll().get(0);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.N);

        Attendance actual = attendanceController.findAttendanceByMemberAndMeeting(member.getId(),meeting.getId());



        Assert.assertEquals(expected, actual);
    }


    @Test
    public void getMyAttendanceInfoIsY() {
        Attendance expected = new Attendance();

        Meeting meeting = meetingController.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberController.findMemberAll().get(1);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.Y);

        Attendance actual = attendanceController.findAttendanceByMemberAndMeeting(member.getId(),meeting.getId());


        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getMyAttendanceInfoIsX() {
        Attendance expected = new Attendance();

        Meeting meeting = meetingController.findMeetingById(1);
        expected.setMeeting(meeting);

        Member member = memberController.findMemberAll().get(2);
        expected.setMember(member);

        expected.setStatus(Attendance.Status.X);

        Attendance actual = attendanceController.findAttendanceByMemberAndMeeting(member.getId(),meeting.getId());


        Assert.assertEquals(expected,actual);
    }

}
