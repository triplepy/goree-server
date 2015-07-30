package com.goree.api.service;


import com.goree.api.domain.Attendance;
import com.goree.api.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    public Attendance findAttendanceByMemberAndMeeting(long memberId, long meetingId) {
        Attendance result = attendanceMapper.selectAttendanceByMemberAndMeeting(memberId, meetingId);
        if(result == null){
            result =  Attendance.noResponse(memberId, meetingId);
        }
        return result;
    }

    public void mapMeetingAndAttendance(long memberId, long meetingId, Attendance.Status status) {
        attendanceMapper.insertMeetingHasMember(memberId, meetingId, status);
    }
}
