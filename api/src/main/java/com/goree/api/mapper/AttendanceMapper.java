package com.goree.api.mapper;


import com.goree.api.domain.Attendance;


public interface AttendanceMapper {

    Attendance selectAttendanceByMemberAndMeeting(int memberId, int meetingId);

    void insertMeetingHasMember(int memberId, int meetingId, Attendance.Status status);
}
