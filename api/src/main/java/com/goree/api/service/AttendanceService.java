package com.goree.api.service;


import com.goree.api.domain.Attendance;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.mapper.AttendanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.*;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MeetingService meetingService;

    /**
     *
     * @param memberId
     * @param meetingId
     * @return
     * @throws IllegalArgumentException 인자값으로 전달된 member와 meeting이 실제로 존재하지 않는 경우에 발생.
     */
    public Attendance findAttendanceByMemberAndMeeting(long memberId, long meetingId) throws IllegalArgumentException {
        Attendance result = attendanceMapper.selectAttendanceByMemberAndMeeting(memberId, meetingId);

        boolean userDoesNotResponse = result == null;
        if(userDoesNotResponse){
            assertMemberAndMeetingExists(memberId, meetingId);
            result = Attendance.noResponse(memberId, meetingId);
        }
        return result;
    }

    private void assertMemberAndMeetingExists(long memberId, long meetingId) throws IllegalArgumentException {
        Member member = memberService.findMemberById(memberId);
        if (member == null)
            throw new IllegalArgumentException("member not exists. (memberId : "+memberId+")");

        Meeting meeting = meetingService.findMeetingById(meetingId);
        if (meeting == null)
            throw new IllegalArgumentException("meeting not exists. (meetingId : "+meetingId+")");
    }

    public void mapMeetingAndAttendance(long memberId, long meetingId, Attendance.Status status) {
        checkArgument(status != null, "status is null.");

        Attendance.Status current = findAttendanceByMemberAndMeeting(memberId, meetingId).getStatus();
        if (current.equals(Attendance.Status.X)) {
            attendanceMapper.insertMeetingHasMember(memberId, meetingId, status);
        } else attendanceMapper.updateMeetingHasMember(memberId,meetingId,status);

    }
}
