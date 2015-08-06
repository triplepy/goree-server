package com.goree.api.controller;

import com.goree.api.domain.Group;
import com.goree.api.domain.Meeting;
import com.goree.api.domain.Member;
import com.goree.api.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/meeting")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public Meeting createMeeting(Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public Meeting findMeetingById(long meetingId) {
        return meetingService.findMeetingById(meetingId);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public List<Meeting> findMeetingsByGroupId(long groupId) {
        return meetingService.findMeetingsByGroupId(groupId);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public List<Meeting> findMeetingsByGroups(List<Group> groups) {
        return meetingService.findMeetingsByGroups(groups);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public List<Meeting> findMeetingsByMemberId(long memberId) {
        return meetingService.findMeetingsByMemberId(memberId);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public List<Meeting> commingUpMeetingsOfMember(Member member) {
        return meetingService.commingUpMeetingsOfMember(member);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public List<Meeting> doneMeetingsOfMember(Member member) {
        return meetingService.doneMeetingsOfMember(member);
    }
}
