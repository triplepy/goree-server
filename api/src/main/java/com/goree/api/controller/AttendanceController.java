package com.goree.api.controller;


import com.goree.api.domain.Attendance;
import com.goree.api.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    /**
     * @api {get} /attendance/find/member/:memberId/meeting/:meetingId Find attendance
     * @apiParam (Attendance) {number} [memberId] member's sequence number
     * {number} [meetingId] meeting's sequence number
     * @apiGroup Attendance
     * @apiDescription 미팅 ID값과 멤버 ID값을 이용해 미팅 참석여부를 가져온다.
     */
    @RequestMapping(value="/find/member/{memberId}/meeting/{meetingId}",method=RequestMethod.GET)
    public Attendance findAttendanceByMemberAndMeeting(@PathVariable long memberId,@PathVariable  long meetingId) {
        return attendanceService.findAttendanceByMemberAndMeeting(memberId, meetingId);
    }

    /**
     * @api {post} /attendance/map/member/:memberId/meeting/:meetingId/status/:status Map Meeting and Attendance
     * @apiName Map Meeting and Attendance
     * @apiGroup Attendance
     * @apiDescription 멤버 ID값과 미팅 ID값에 해당되는 참석여부 상태를 변경한다.
     */
    @RequestMapping(value="/map/member/{memberId}/meeting/{meetingId}/status/{status}", method=RequestMethod.POST )
    public void mapMeetingAndAttendance(@PathVariable long memberId,@PathVariable long meetingId,
                                        @PathVariable Attendance.Status status) {
        attendanceService.mapMeetingAndAttendance(memberId, meetingId, status);
    }
}
