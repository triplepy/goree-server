package com.goree.api.controller;


import com.goree.api.domain.Attendance;
import com.goree.api.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public Attendance findAttendanceByMemberAndMeeting(long memberId, long meetingId) {
        return attendanceService.findAttendanceByMemberAndMeeting(memberId, meetingId);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public void mapMeetingAndAttendance(long memberId, long meetingId, Attendance.Status status) {
        attendanceService.mapMeetingAndAttendance(memberId, meetingId, status);
    }
}
