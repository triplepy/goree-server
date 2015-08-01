package com.goree.api.controller;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    
    public Meeting createMeeting(Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

    public Meeting findMeetingById(long meetingId) {
        return meetingService.findMeetingById(meetingId);
    }

    public List<Meeting> findMeetingsByGroupId(long groupId) {
        return meetingService.findMeetingByGroupId(groupId);
    }

    public List<Meeting> findMeetingsByGroups(List<Group> groups) {
        return meetingService.findMeetingsByGroups(groups);
    }

    public List<Meeting> findMeetingsByMemberId(long memberId) {
        return meetingService.findMeetingsByMemberId(memberId);
    }

    public List<Meeting> commingUpMeetingsOfMember(Member member) {
        return meetingService.commingUpMeetingsOfMember(member);
    }

    public List<Meeting> doneMeetingsOfMember(Member member) {
        return meetingService.doneMeetingsOfMember(member);
    }
}
