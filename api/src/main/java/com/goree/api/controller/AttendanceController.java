package com.goree.api.controller;


import com.goree.api.domain.Attendance;
import com.goree.api.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    public Attendance findAttendanceByMemberAndMeeting(int memberId, int meetingId) {
        return attendanceService.findAttendanceByMemberAndMeeting(memberId, meetingId);
    }

    public void mapMeetingAndAttendance(int memberId, int meetingId, Attendance.Status status) {
        attendanceService.mapMeetingAndAttendance(memberId, meetingId, status);
    }
}
