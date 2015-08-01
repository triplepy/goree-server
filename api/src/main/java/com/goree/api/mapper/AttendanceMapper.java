package com.goree.api.mapper;


import com.goree.api.domain.Attendance;


public interface AttendanceMapper {

    Attendance selectAttendanceByMemberAndMeeting(long memberId, long meetingId);

    void insertMeetingHasMember(long memberId, long meetingId, Attendance.Status status);
}
