package com.goree.api.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Attendance {
    private Meeting meeting;
    private Member member;
    private Status status;


    public enum Status {
        Y, N, X
    }

    @Override
    public boolean equals(Object other) {
        if(!(other instanceof Attendance))
            return false;
        Attendance attendance = (Attendance) other;

        return attendance.getMember().getId() == getMember().getId() &&
                attendance.getMeeting().getId() == getMeeting().getId() &&
                attendance.getStatus() == getStatus();
    }

    public static Attendance noResponse(long memberId, long meetingId) {
        Attendance noResponse = new Attendance();
        Member member = new Member();
        member.setId(memberId);
        noResponse.setMember(member);
        Meeting meeting = new Meeting();
        meeting.setId(meetingId);
        noResponse.setMeeting(meeting);
        noResponse.setStatus(Status.X);
        return noResponse;
    }
}
